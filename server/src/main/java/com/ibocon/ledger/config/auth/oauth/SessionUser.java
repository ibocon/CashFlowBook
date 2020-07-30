package com.ibocon.ledger.config.auth.oauth;

import java.io.Serializable;

import com.ibocon.ledger.repository.user.User;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String email;
    private String name;
    private String imageUrl;

    public SessionUser(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.imageUrl = user.getImageUrl();
    }
}