package com.ibocon.ledger.repository.user;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.ibocon.ledger.config.auth.oauth.OAuth2Provider;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name="USER", uniqueConstraints = {
    @UniqueConstraint(
        name = "EMAIL_UNIQUE",
        columnNames = {"EMAIL"})})
public class User implements OAuth2User {

    @Id 
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "ROLE", nullable = false)
    private final Role role;

    @Column(name = "EMAIL", nullable = false)
    final private String email;

    @Column(name = "NAME", nullable = true)
    private String name;
    @Override
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    @Column(name = "PROVIDER", nullable = false)
    @Enumerated(EnumType.STRING)
    final private OAuth2Provider provider;

    @Column(name = "IMAGE_URL", nullable = true)
    private String imageUrl;

    public void setImageUrl(final String url) {
        imageUrl = url;
    }

    @Transient
    private Map<String, Object> attributes;

    @Builder
    public User(Role role, String email, OAuth2Provider provider, String name, String imageUrl) {
        this.role = role; this.email = email; this.provider = provider; this.name = name; this.imageUrl = imageUrl;
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public User update(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;

        return this;
    }
    // @OneToMany(
    //     targetEntity = UserDefinedAccount.class, 
    //     cascade = CascadeType.ALL, 
    //     fetch = FetchType.LAZY
    // )
    // private List<UserDefinedAccount> userDefinedAccounts;
}