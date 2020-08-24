package com.ibocon.ledger.security.oauth.userinfo;

import java.util.Map;

import com.ibocon.ledger.security.oauth.OAuth2Provider;

public class GoogleOAuthUserInfo extends BaseOAuthUserInfo {

    public GoogleOAuthUserInfo(OAuth2Provider provider, Map<String, Object> attributes) {
        super(provider, attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }
}