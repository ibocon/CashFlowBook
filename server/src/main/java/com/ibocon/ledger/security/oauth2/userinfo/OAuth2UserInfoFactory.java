package com.ibocon.ledger.security.oauth2.userinfo;

import java.util.Map;

import com.ibocon.ledger.security.oauth2.OAuth2Provider;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) throws Exception {
        if(registrationId.equalsIgnoreCase(OAuth2Provider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new Exception("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}