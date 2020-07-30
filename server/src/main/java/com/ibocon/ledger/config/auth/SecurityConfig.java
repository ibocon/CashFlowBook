package com.ibocon.ledger.config.auth;

import com.ibocon.ledger.config.auth.oauth.CookieOAuth2AuthorizationRequestRepository;
import com.ibocon.ledger.config.auth.oauth.MyOAuth2UserService;
import com.ibocon.ledger.config.auth.oauth.OAuth2AuthenticationFailureHandler;
import com.ibocon.ledger.config.auth.oauth.OAuth2AuthenticationSuccessHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
// @Secured, @RolesAllowed, @PreAuthorize, @PostAuthorize
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyOAuth2UserService myOauth2UserService;

    private final CookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
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
            .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll() //H2 Console 접속 시 필요
                .anyRequest().authenticated()
        .and()
            .oauth2Login()
                .authorizationEndpoint()
                    .baseUri("/oauth2/authorize")
                    .authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository)
                .and().redirectionEndpoint()
                    .baseUri("/oauth2/callback/*")
                .and().userInfoEndpoint()
                    .userService(myOauth2UserService)
                .and()
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    .failureHandler(oAuth2AuthenticationFailureHandler)
        ;
    }
}