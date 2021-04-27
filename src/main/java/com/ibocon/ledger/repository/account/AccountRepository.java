package com.ibocon.ledger.repository.account;

import com.ibocon.ledger.repository.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByBelongTo(User user);
    List<Account> findByBelongToAndName(User user, String name);
}