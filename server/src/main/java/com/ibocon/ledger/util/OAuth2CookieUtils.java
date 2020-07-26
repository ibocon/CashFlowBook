package com.ibocon.ledger.util;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nimbusds.oauth2.sdk.util.StringUtils;

import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

public class OAuth2CookieUtils extends CookieUtils {
    private static final String OAUTH2_AUTHORIZATION_REQUEST = "oauth2_auth_request";
    private static final String OAUTH2_REDIRECT_URI = "redirect_uri";
    private static final int EXPIRE_SECONDS = 180;

    public static void addOauth2AuthRequestCookie(HttpServletRequest request, HttpServletResponse response, OAuth2AuthorizationRequest oAuth2AuthorizationRequest) {
        addCookie(response, OAUTH2_AUTHORIZATION_REQUEST, CookieUtils.serialize(oAuth2AuthorizationRequest), EXPIRE_SECONDS);
        addRedirectUrlCookie(request, response);
    }

    private static void addRedirectUrlCookie(HttpServletRequest request, HttpServletResponse response) {
        String redirectUri = request.getParameter(OAUTH2_REDIRECT_URI);
        if(StringUtils.isNotBlank(redirectUri)) {
            addCookie(response, OAUTH2_REDIRECT_URI, redirectUri, EXPIRE_SECONDS);
        }
    }

    public static OAuth2AuthorizationRequest getOAuth2AuthorizationRequest(HttpServletRequest request) {
        return getCookie(request, OAUTH2_AUTHORIZATION_REQUEST)
            .map(cookie -> CookieUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
            .orElse(null);
    }

    public static void deleteOauth2AuthRequestCookie(HttpServletRequest request, HttpServletResponse response) {
        deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST);
        deleteCookie(request, response, OAUTH2_REDIRECT_URI);
    }

    public static String getRedirectUrl(HttpServletRequest request) {
        Optional<String> redirectUri = getCookie(request, OAUTH2_REDIRECT_URI).map(Cookie::getValue);

        if(redirectUri.isPresent()){
            return redirectUri.get();
        } else {
            return "/";
        }
    }
}