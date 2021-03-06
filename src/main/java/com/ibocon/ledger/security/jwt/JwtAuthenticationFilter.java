package com.ibocon.ledger.security.jwt;

import com.ibocon.ledger.repository.user.UserSerivce;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserSerivce userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwt(request);

            if (StringUtils.hasText(jwt) && jwtTokenProvider.validate(jwt)) {
                Long userId = jwtTokenProvider.getUserID(jwt);
                var user = userService.getUserById(userId);
                if(user != null) {
                    var authentication = new OAuth2AuthenticationToken(user, user.getAuthorities(),  user.getProvider().toString());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }
        } catch (Exception ex) {
            log.error("security context 에 인증정보를 설정할 수 없습니다.", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwt(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}