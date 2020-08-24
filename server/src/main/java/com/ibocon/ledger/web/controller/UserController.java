package com.ibocon.ledger.web.controller;

import com.ibocon.ledger.security.CurrentUser;
import com.ibocon.ledger.repository.user.User;
import com.ibocon.ledger.repository.user.UserSerivce;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping(path="/user", produces="application/json")
public class UserController {

    private final UserSerivce userSerivce;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> profile(@CurrentUser User user) {
        if(userSerivce.isUserExist(user.getId())) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}