package com.ibocon.ledger.security.oauth2;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibocon.ledger.security.jwt.JwtTokenProvider;
import com.ibocon.ledger.util.OAuth2CookieUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Value("${ledger.redirectUri}")
    private List<String> authorizedRedirectUri;

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, 
        HttpServletResponse response, 
        Authentication authentication)
        throws IOException, ServletException
    {
        String uri = getRedirectUrl(request, response, authentication);

        if(response.isCommitted()) {
            log.debug("Response has already been committed. Unable to redirect to " + uri);
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, uri);
    }

    private String getRedirectUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws ServletException {
        String uri = OAuth2CookieUtils.getRedirectUrl(request);

        if(!validateRedirectUri(URI.create(uri))) {
            throw new ServletException("Unauthorized redirect URI. : " + uri);
        }

        String token = jwtTokenProvider.create(authentication);

        return UriComponentsBuilder.fromUriString(uri).queryParam("token", token).build().toUriString();
    }

    private Boolean validateRedirectUri(URI uri) {
        return authorizedRedirectUri.stream().anyMatch(authorizedRedirectUri -> {
            URI authRedirectUri = URI.create(authorizedRedirectUri);
            if(authRedirectUri.getHost().equalsIgnoreCase(uri.getHost())
            && authRedirectUri.getPort() == uri.getPort()) {
                return true;
            }
            else {
                return false;
            }
        });
    }

    private void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        OAuth2CookieUtils.deleteOauth2AuthRequestCookie(request, response);
    }
}