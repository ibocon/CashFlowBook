package com.ibocon.ledger.config.auth.oauth;

import java.util.Optional;

import com.ibocon.ledger.config.auth.oauth.userinfo.OAuth2UserInfo;
import com.ibocon.ledger.config.auth.oauth.userinfo.OAuth2UserInfoFactory;
import com.ibocon.ledger.repository.user.LedgerUser;
import com.ibocon.ledger.repository.user.LedgerUserRepository;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyOAuth2UserService extends DefaultOAuth2UserService {

    private final LedgerUserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        try {
            OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(getRegistrationId(userRequest), oAuth2User.getAttributes());
            if(!validateEmail(oAuth2UserInfo)) {
                throw new OAuth2AuthenticationException(new OAuth2Error("400"), "Email validation failed.");
            }

            LedgerUser user = getUser(oAuth2UserInfo);
            if(user == null) {
                user = signupUser(userRequest, oAuth2UserInfo);
            }
            else {
                if(!validateProvider(userRequest, user.getOuthProvider())) {
                    throw new OAuth2AuthenticationException(new OAuth2Error("400"), "Looks like you're signed up with " +
                        user.getOuthProvider() + " account. Please use your " + getRegistrationId(userRequest) +
                        " account to login.");
                }
                user = updateUser(user, oAuth2UserInfo);
            }

            return user;
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private String getRegistrationId(OAuth2UserRequest userRequest) {
        return userRequest.getClientRegistration().getRegistrationId();
    }

    private Boolean validateEmail(OAuth2UserInfo oAuth2UserInfo) {
        return !StringUtils.isEmpty(oAuth2UserInfo.getEmail());
    }

    private Boolean validateProvider(OAuth2UserRequest userRequest, OAuth2Provider provider) {
        return provider.equals(OAuth2Provider.valueOf(getRegistrationId(userRequest)));
    }

    private LedgerUser getUser(OAuth2UserInfo oAuth2UserInfo) {
        Optional<LedgerUser> optional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        if(optional.isPresent()) {
            return optional.get();
        }
        else {
            return null;
        }
    }

    private LedgerUser signupUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        LedgerUser user = new LedgerUser(oAuth2UserInfo.getEmail(), OAuth2Provider.valueOf(getRegistrationId(oAuth2UserRequest)));
        return updateUser(user, oAuth2UserInfo);
    }

    private LedgerUser updateUser(LedgerUser user, OAuth2UserInfo oAuth2UserInfo) {
        user.setName(oAuth2UserInfo.getName());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(user);
    }
}