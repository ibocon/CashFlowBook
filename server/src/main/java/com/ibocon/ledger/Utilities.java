package com.ibocon.ledger;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;

public final class Utilities {

    public static final String PARAM_REDIRECT_URI = "redirect_uri";
    private static List<String> authorizedRedirectUris = new ArrayList<String>(List.of("http://localhost:8080/oauth2/redirect"));
    
    public static Boolean IsAuthorizedRedirectUri(final String redirectUriString) {
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