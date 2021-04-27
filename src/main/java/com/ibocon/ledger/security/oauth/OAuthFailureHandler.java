package com.ibocon.ledger.security.oauth;

import com.ibocon.ledger.util.CookieUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        var redirectUriString = CookieUtils.getRedirectUrlFromCookie(request);
        var redirectUriWithErrorString = UriComponentsBuilder.fromUriString(redirectUriString).queryParam("error", exception.getMessage()).build().toUriString();
        
        // 사용자 인증을 위해 사용했던 정보 제거
        CookieUtils.clearAuthenticationCookie(request, response);

        getRedirectStrategy().sendRedirect(request, response, redirectUriWithErrorString);
    }
}