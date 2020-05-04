package com.ibocon.ledger.repository;

import java.util.Optional;

import com.ibocon.ledger.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}