package com.ibocon.ledger.config.auth.oauth;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ibocon.ledger.config.auth.oauth.userinfo.BaseOAuthUserInfo;
import com.ibocon.ledger.config.auth.oauth.userinfo.GoogleOAuthUserInfo;
import com.ibocon.ledger.repository.user.User;
import com.ibocon.ledger.repository.user.UserRepository;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.var;

@RequiredArgsConstructor
@Service
public class MyOAuth2UserService extends DefaultOAuth2UserService {

    private static final String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // DefaultOAuth2UserSerive 로 request 에서 OAuth2User 객체 생성
        var oAuth2User = super.loadUser(userRequest);

        try {
            var registrationId = userRequest.getClientRegistration().getRegistrationId();
            var provider = OAuth2Provider.valueOf(registrationId);

            var oAuth2UserInfo = getOAuth2UserInfo(provider, oAuth2User.getAttributes());
            
            if(!isValidEmail(oAuth2UserInfo.getEmail())) {
                throw new OAuth2AuthenticationException(new OAuth2Error("400"), 
                oAuth2UserInfo.getEmail() + "은 올바른 이메일 형식이 아닙니다.");
            }

            var user = saveOrUpdate(oAuth2UserInfo);

            if(!provider.equals(user.getProvider())) {
                throw new OAuth2AuthenticationException(new OAuth2Error("400"), 
                "로그인은 " + provider + "로 하셨지만, 등록은 " + user.getProvider() + "로 진행하셨습니다."
                + user.getProvider() + "로 다시 로그인해주시길 바랍니다");
            }

            return user;

        } catch (AuthenticationException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new InternalAuthenticationServiceException(exception.getMessage(), exception.getCause());
        }
    }

    private BaseOAuthUserInfo getOAuth2UserInfo(OAuth2Provider provider, Map<String, Object> attributes) throws Exception {
        try {
            switch(provider) {
                case google:
                    return new GoogleOAuthUserInfo(provider, attributes);
            }
            throw new UnsupportedOperationException(provider + "가 swich 문에 추가되지 않았습니다.");
        } catch(IllegalArgumentException exception) {
            throw new OAuth2AuthenticationException(new OAuth2Error("400"),
            provider + " 는 아직 지원하지 않는 OAuth2 제공자입니다.");
        }
    }

    private Boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private User saveOrUpdate(BaseOAuthUserInfo oAuth2UserInfo) {
        User user = userRepository.findByEmail(oAuth2UserInfo.getEmail())
                .map(entity -> entity.update(oAuth2UserInfo.getName(), oAuth2UserInfo.getImageUrl()))
                .orElse(oAuth2UserInfo.toEntity());

        return userRepository.save(user);
    }
}