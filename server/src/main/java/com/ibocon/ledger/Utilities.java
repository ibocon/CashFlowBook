package com.ibocon.ledger;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;

public final class Utilities {

    @Value("${ledger.redirectUri}")
    private static List<String> authorizedRedirectUris;
    public static final String PARAM_REDIRECT_URI = "redirect_uri";
    public static String getRedirectUri(HttpServletRequest request) {
        return request.getParameter(Utilities.PARAM_REDIRECT_URI);
    }
    public static Boolean IsAuthorizedRedirectUri(String redirectUriString) {
        var redirectUri = URI.create(redirectUriString);
        var isAuthorizedRedirectUri = authorizedRedirectUris.stream().anyMatch(authorizedRedirectUriString -> {
            var authorizedRedirectUri = URI.create(authorizedRedirectUriString);
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