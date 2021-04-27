package com.ibocon.ledger.security.jwt;

import com.ibocon.ledger.repository.user.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${ledger.jwt.secret}")
    private String secret;

    @Value("${ledger.jwt.expire}")
    private int expire;

    public String create(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        String jwt = Jwts.builder()
            .setSubject(Long.toString(user.getId()))
            .setIssuedAt(new Date())
            .setExpiration(getExpiryDate())
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
        
        return jwt;
    }

    public boolean validate(String token) {
        try {
            getClaims(token);
            return true;
        }
        catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } 
        catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } 
        catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } 
        catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } 
        catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }

        return false;
    }

    public Long getUserID(String token) {
        Claims claims = getClaims(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    private Jws<Claims> getClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    }

    private Date getExpiryDate() {
        Date now = new Date();
        return new Date(now.getTime() + expire);
    }
}