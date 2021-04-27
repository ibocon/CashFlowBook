package com.ibocon.ledger.security.oauth.userinfo;

import com.ibocon.ledger.repository.user.Role;
import com.ibocon.ledger.repository.user.User;
import com.ibocon.ledger.security.oauth.OAuth2Provider;

import java.util.Map;

public abstract class BaseOAuthUserInfo {

    protected OAuth2Provider provider;
    protected Map<String, Object> attributes;

    public BaseOAuthUserInfo(OAuth2Provider provider, Map<String, Object> attributes) {
        this.provider = provider;
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();
    public abstract String getName();
    public abstract String getEmail();
    public abstract String getImageUrl();

    public User toEntity() {
        return User.builder()
        .role(Role.USER)
        .email(getEmail())
        .provider(provider)
        .name(getName())
        .imageUrl(getImageUrl())
        .build();        
    }
}
