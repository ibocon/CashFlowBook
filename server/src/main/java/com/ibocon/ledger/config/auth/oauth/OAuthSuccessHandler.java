package com.ibocon.ledger.config.auth.oauth;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibocon.ledger.config.auth.jwt.JwtTokenProvider;
import com.ibocon.ledger.util.CookieUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Value("${ledger.redirectUris}")
    private List<String> authorizedRedirectUris;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        var redirectUri = getRedirectUrl(request, response, authentication);

        if(response.isCommitted()) {
            log.debug("response 가 이미 처리되어, " + redirectUri + "로 redirect 할 수 없습니다.");
            return;
        }

        // 사용자 인증을 위해 사용했던 정보 제거
        clearAuthenticationAttributes(request);
        CookieUtils.clearAuthenticationCookie(request, response);

        getRedirectStrategy().sendRedirect(request, response, redirectUri);
    }

    private String getRedirectUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException {
        var redirectUriString = CookieUtils.getRedirectUrlFromCookie(request);

        if(!IsAuthorizedRedirectUri(redirectUriString)) {
            throw new ServletException(redirectUriString + "는 허가되지 않은 redirect uri 입니다.");
        }

        // Json Web Token 을 만들어 전달한다.
        var token = jwtTokenProvider.create(authentication);
        return UriComponentsBuilder.fromUriString(redirectUriString).queryParam("token", token).build().toUriString();
    }

    private Boolean IsAuthorizedRedirectUri(String redirectUriString) {
        final var redirectUri = URI.create(redirectUriString);
        final var isAuthorizedRedirectUri = authorizedRedirectUris.stream().anyMatch(authorizedRedirectUriString -> {
            final var authorizedRedirectUri = URI.create(authorizedRedirectUriString);
            if(authorizedRedirectUri.getHost().equalsIgnoreCase(redirectUri.getHost()) 
            && authorizedRedirectUri.getPort() == redirectUri.getPort()) {
                return true;
            } else {
                return false;
            }
        });

        return isAuthorizedRedirectUri;
    }
}