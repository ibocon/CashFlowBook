package com.ibocon.ledger.controller;

import java.util.Optional;

import com.ibocon.ledger.model.User;
import com.ibocon.ledger.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(produces="application/json")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value="/users")
    public ResponseEntity<Iterable<User>> getUsers() {
        Iterable<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value="/user/{email}")
    public ResponseEntity<?> getUserByName(@PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value="/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/user/{id}")
    public ResponseEntity<?> deleteUserByName(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    
}