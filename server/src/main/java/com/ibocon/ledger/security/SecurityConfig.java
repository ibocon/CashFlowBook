package com.ibocon.ledger.security;

import com.ibocon.ledger.security.jwt.JwtAuthenticationFilter;
import com.ibocon.ledger.security.oauth.CookieOAuth2AuthorizationRequestRepository;
import com.ibocon.ledger.security.oauth.MyOAuth2UserService;
import com.ibocon.ledger.security.oauth.OAuthFailureHandler;
import com.ibocon.ledger.security.oauth.OAuthSuccessHandler;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;

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
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository;
    private final MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            // Cross-Origin Resource Sharing (CORS)
            .cors()
        .and()
            // Stateless 서버
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            // OAuth2 로그인 이외 Disable
            .formLogin().disable().httpBasic().disable()
            // Cross-Site Request Forgery
            .csrf().disable()
            // H2 CConsole 접속에 필요
            .headers().frameOptions().disable()
        .and()
            .exceptionHandling( exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(myAuthenticationEntryPoint)
            )
            .authorizeRequests(authorize -> authorize
                .antMatchers("/h2-console/**").permitAll() //H2 Console 접속에 필요
                .anyRequest().authenticated()
            )
            // https://docs.spring.io/spring-security/site/docs/5.4.0-M1/reference/html5/#oauth2login-advanced
            .oauth2Login(oauth2 -> oauth2
                .authorizationEndpoint(authorization -> authorization
                    .baseUri("/oauth2/authorize")
                    .authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository)
                )
                .redirectionEndpoint(redirection -> redirection
                    .baseUri("/oauth2/callback/*")
                )
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(myOauth2UserService)
                )
                .successHandler(oauthSuccessHandler)
                .failureHandler(oauthFailureHandler)
            )
        ;

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, OAuth2LoginAuthenticationFilter.class);
    }
}