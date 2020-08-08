package com.ibocon.ledger.config.auth.oauth;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibocon.ledger.Utilities;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        var redirectUriString = "http://localhost:8080/oauth2/redirect";
        if(Utilities.IsAuthorizedRedirectUri(redirectUriString)) {
            getRedirectStrategy().sendRedirect(request, response, redirectUriString);
            return;
        }

        throw new ServletException(redirectUriString + "는 허가되지 않은 redirect uri 입니다.");
    }
}