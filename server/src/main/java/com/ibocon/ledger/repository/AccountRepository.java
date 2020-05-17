package com.ibocon.ledger.repository;

import java.util.List;

import com.ibocon.ledger.model.Account;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByUser(Long user);
    List<Account> findByUserAndName(Long user, String name);
}