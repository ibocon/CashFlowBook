package com.ibocon.ledger.web.controller;

import java.net.URI;

import javax.validation.Valid;

import com.ibocon.ledger.web.exception.BadRequestException;
import com.ibocon.ledger.repository.user.Role;
import com.ibocon.ledger.repository.user.User;
import com.ibocon.ledger.web.dto.ApiResponse;
import com.ibocon.ledger.web.dto.AuthResponse;
import com.ibocon.ledger.web.dto.LoginRequest;
import com.ibocon.ledger.web.dto.SignUpRequest;
import com.ibocon.ledger.repository.user.UserRepository;
import com.ibocon.ledger.config.auth.jwt.JwtTokenProvider;
import com.ibocon.ledger.config.auth.oauth.OAuth2Provider;

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
}