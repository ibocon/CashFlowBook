package com.ibocon.ledger.security.oauth2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibocon.ledger.util.OAuth2CookieUtils;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.security.core.AuthenticationException;

@Component
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request, 
            HttpServletResponse response, 
            AuthenticationException exception) 
            throws IOException, ServletException {

        String redirectUrl = OAuth2CookieUtils.getRedirectUrl(request);
        redirectUrl = UriComponentsBuilder.fromUriString(redirectUrl).queryParam("error", exception.getLocalizedMessage()).build().toUriString();

        OAuth2CookieUtils.deleteOauth2AuthRequestCookie(request, response);
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}