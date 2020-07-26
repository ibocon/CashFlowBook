package com.ibocon.ledger.web.controller;

import java.net.URI;

import javax.validation.Valid;

import com.ibocon.ledger.web.exception.BadRequestException;
import com.ibocon.ledger.repository.user.LedgerUser;
import com.ibocon.ledger.web.dto.ApiResponse;
import com.ibocon.ledger.web.dto.AuthResponse;
import com.ibocon.ledger.web.dto.LoginRequest;
import com.ibocon.ledger.web.dto.SignUpRequest;
import com.ibocon.ledger.repository.user.LedgerUserRepository;
import com.ibocon.ledger.config.auth.jwt.JwtTokenProvider;
import com.ibocon.ledger.config.auth.oauth2.OAuth2Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final LedgerUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.create(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> postMethodName(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use");
        }

        LedgerUser user = new LedgerUser(signUpRequest.getEmail(), OAuth2Provider.local);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        user = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/user/me")
                        .buildAndExpand(user.getId()).toUri();
        
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
    
}