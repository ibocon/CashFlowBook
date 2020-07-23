package com.ibocon.ledger.controller;

import java.util.Optional;

import com.ibocon.ledger.annotation.CurrentUser;
import com.ibocon.ledger.model.LedgerUser;
import com.ibocon.ledger.repository.LedgerUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/user", produces="application/json")
public class UserController {

    @Autowired
    private LedgerUserRepository userRepository;

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