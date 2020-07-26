package com.ibocon.ledger.config.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibocon.ledger.util.OAuth2CookieUtils;

import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

@Component
public class CookieOAuth2AuthorizationRequestRepository
        implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest httpServletRequest) {
        return OAuth2CookieUtils.getOAuth2AuthorizationRequest(httpServletRequest);
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest httpServletRequest) {
        return loadAuthorizationRequest(httpServletRequest);
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest oAuth2AuthorizationRequest, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        if(oAuth2AuthorizationRequest == null) {
            OAuth2CookieUtils.deleteOauth2AuthRequestCookie(httpServletRequest, httpServletResponse);
            return;
        }

        OAuth2CookieUtils.addOauth2AuthRequestCookie(httpServletRequest, httpServletResponse, oAuth2AuthorizationRequest);
    }

}