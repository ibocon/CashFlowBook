package com.ibocon.ledger.repository;

import java.util.List;

import com.ibocon.ledger.model.Account;
import com.ibocon.ledger.model.User;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByUser(User user);
    List<Account> findByUserAndName(Long id, String name);
}