package com.ibocon.ledger.util;

import java.util.Base64;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.SerializationUtils;

public class CookieUtils {

    public static final String OAUTH2_AUTHORIZATION_REQUEST = "oauth2_auth_request";
    public static final String OAUTH2_REDIRECT_URI = "redirect_uri";
    public static final int EXPIRE_SECONDS = 180;

    public static void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(EXPIRE_SECONDS);
        response.addCookie(cookie);
    }

    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie);
                }
            }
        }

        return Optional.empty();
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    public static String serialize(Object object) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(object));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(SerializationUtils.deserialize(
                        Base64.getUrlDecoder().decode(cookie.getValue())));
    }

    public static String getRedirectUrlFromCookie(HttpServletRequest request) {
        Optional<String> redirectUri = getCookie(request, OAUTH2_REDIRECT_URI).map(Cookie::getValue);

        if(redirectUri.isPresent()){
            return redirectUri.get();
        } else {
            return "/";
        }
    }

    public static void clearAuthenticationCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, CookieUtils.OAUTH2_AUTHORIZATION_REQUEST);
        CookieUtils.deleteCookie(request, response, CookieUtils.OAUTH2_REDIRECT_URI);
    }
}