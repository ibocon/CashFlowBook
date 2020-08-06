package com.ibocon.ledger.config.auth;

import com.ibocon.ledger.config.auth.oauth.MyOAuth2UserService;
import com.ibocon.ledger.config.auth.oauth.OAuthFailureHandler;
import com.ibocon.ledger.config.auth.oauth.OAuthSuccessHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
// https://docs.spring.io/spring-security/site/docs/5.4.0-M1/reference/html5/#enableglobalmethodsecurity
// @Secured, @RolesAllowed, @PreAuthorize, @PostAuthorize
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyOAuth2UserService myOauth2UserService;
    private final OAuthSuccessHandler oauthSuccessHandler;
    private final OAuthFailureHandler oauthFailureHandler;

    @Bean
    public HttpSessionOAuth2AuthorizationRequestRepository httpSessionOAuth2AuthorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public SimpleAuthorityMapper simpleAuthorityMapper() {
        return new SimpleAuthorityMapper();
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .cors()
        .and()
            // Cross-Site Request Forgery 크로스 사이트 요청 위조 공격 방어
            .csrf().disable()
            // H2 CConsole 접속에 필요
            .headers().frameOptions().disable()
        .and()
            .authorizeRequests(authorize -> authorize
                .antMatchers("/h2-console/**").permitAll() //H2 Console 접속 시 필요
                .anyRequest().authenticated()
            )
            // https://docs.spring.io/spring-security/site/docs/5.4.0-M1/reference/html5/#oauth2login-advanced
            .oauth2Login(oauth2 -> oauth2
                .authorizationEndpoint(authorization -> authorization
                    .baseUri("/oauth2/authorize")
                    .authorizationRequestRepository(httpSessionOAuth2AuthorizationRequestRepository())
                )
                .redirectionEndpoint(redirection -> redirection
                    .baseUri("/oauth2/callback/*")
                )
                .userInfoEndpoint(userInfo -> userInfo
                    .userAuthoritiesMapper(simpleAuthorityMapper())
                    .userService(myOauth2UserService)
                )
                .successHandler(oauthSuccessHandler)
                .failureHandler(oauthFailureHandler)
            )

        ;
    }
}