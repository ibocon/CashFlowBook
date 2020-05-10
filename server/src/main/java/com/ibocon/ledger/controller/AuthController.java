package com.ibocon.ledger.controller;

import java.net.URI;

import javax.validation.Valid;

import com.ibocon.ledger.exception.BadRequestException;
import com.ibocon.ledger.model.User;
import com.ibocon.ledger.model.payload.ApiResponse;
import com.ibocon.ledger.model.payload.AuthResponse;
import com.ibocon.ledger.model.payload.LoginRequest;
import com.ibocon.ledger.model.payload.SignUpRequest;
import com.ibocon.ledger.repository.UserRepository;
import com.ibocon.ledger.security.jwt.JwtTokenProvider;
import com.ibocon.ledger.security.oauth2.OAuth2Provider;

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

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

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

        User user = new User(signUpRequest.getEmail(), OAuth2Provider.local);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        user = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/user/me")
                        .buildAndExpand(user.getId()).toUri();
        
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
    
}