package com.ibocon.ledger.config.auth.oauth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibocon.ledger.util.CookieUtils;
import com.nimbusds.oauth2.sdk.util.StringUtils;

import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

/*
 * Stateless 서버를 만들기 위해 Session 대신 Cookie에 인증에 필요한 정보를 저장한다.
 */
@Component
public class CookieOAuth2AuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        // request 쿠키 중 'oauth2_auth_request' 정보로 OAuth2AuthorizationRequest 객체를 만들어 반환한다.
        return CookieUtils.getCookie(request, CookieUtils.OAUTH2_AUTHORIZATION_REQUEST)
            .map(cookie -> CookieUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
            .orElse(null);
    }

    // Deprecated 되니, removeAuthorizationRequest(HttpServletRequest, HttpServletResponse)을 사용하자.
    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        return loadAuthorizationRequest(request);
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        
        if(authorizationRequest == null) {
            // OAuth2AuthorizationRequest 객체가 생성되지 않았다면, response 쿠키 정보를 삭제한다.
            CookieUtils.deleteCookie(request, response, CookieUtils.OAUTH2_AUTHORIZATION_REQUEST);
            CookieUtils.deleteCookie(request, response, CookieUtils.OAUTH2_REDIRECT_URI);
            return;
        }

        // 현재 OAuth2AuthorizationRequest 객체를 Response의 'oauth2_auth_request' Cookie에 저장한다.
        CookieUtils.addCookie(response, CookieUtils.OAUTH2_AUTHORIZATION_REQUEST, CookieUtils.serialize(authorizationRequest));
        addRedirectUrlToCookie(request, response);
    }

    // request의 'redirect_uri' 변수를 response의 'redirect_uri' cookie에 저장한다.
    private void addRedirectUrlToCookie(HttpServletRequest request, HttpServletResponse response) {
        String redirectUri = request.getParameter(CookieUtils.OAUTH2_REDIRECT_URI);
        if(StringUtils.isNotBlank(redirectUri)) {
            CookieUtils.addCookie(response, CookieUtils.OAUTH2_REDIRECT_URI, redirectUri);
        }
    }


}