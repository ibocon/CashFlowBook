package com.ibocon.ledger.web.controller;

import java.util.Optional;

import com.ibocon.ledger.config.auth.CurrentUser;
import com.ibocon.ledger.repository.user.LedgerUser;
import com.ibocon.ledger.repository.user.LedgerUserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/user", produces="application/json")
public class UserController {

    private final LedgerUserRepository userRepository;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> profile(@CurrentUser LedgerUser user) {
        Optional<LedgerUser> userOptional = userRepository.findById(user.getId());
        if(userOptional.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}