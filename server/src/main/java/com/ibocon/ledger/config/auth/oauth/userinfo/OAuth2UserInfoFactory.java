package com.ibocon.ledger.config.auth.oauth.userinfo;

import java.util.Map;

import com.ibocon.ledger.config.auth.oauth.OAuth2Provider;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) throws Exception {
        if(registrationId.equalsIgnoreCase(OAuth2Provider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new Exception("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}