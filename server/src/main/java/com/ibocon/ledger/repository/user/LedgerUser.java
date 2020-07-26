package com.ibocon.ledger.repository.user;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibocon.ledger.repository.account.UserDefinedAccount;
import com.ibocon.ledger.config.auth.oauth2.OAuth2Provider;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor()
@Entity
public class LedgerUser implements UserDetails, OAuth2User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    final private String email;

    private String username;
    public void setName(String name) {
        username = name;
    }

    @JsonIgnore
    private String passWord;
    
    public void setPassword(String password) {
        // TODO 비밀번호를 암호화 하는 과정 필요
        passWord = password;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    final private OAuth2Provider outhProvider;

    private String imageUrl;
    public void setImageUrl(String url) {
        imageUrl = url;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public String getPassword() {
        // TODO 비밀번호를 복호화?
        return passWord;
    }

    @Transient
    private Map<String, Object> attributes;

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @OneToMany(
        targetEntity = UserDefinedAccount.class, 
        cascade = CascadeType.ALL, 
        fetch = FetchType.EAGER
    )
    private List<UserDefinedAccount> userDefinedAccounts;
}