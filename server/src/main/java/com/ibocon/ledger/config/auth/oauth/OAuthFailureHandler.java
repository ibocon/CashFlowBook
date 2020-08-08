package com.ibocon.ledger.config.auth.oauth;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibocon.ledger.Utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

            var redirectUriString = "http://localhost:8080/oauth2/redirect";
            if(Utilities.IsAuthorizedRedirectUri(redirectUriString)) {
                var redirectUriWithError = UriComponentsBuilder.fromUriString(redirectUriString).queryParam("error", exception.getMessage()).build();
                getRedirectStrategy().sendRedirect(request, response, redirectUriWithError.toUriString());
            }
    
            throw new ServletException(redirectUriString + "는 허가되지 않은 redirect uri 입니다.");
    }
}