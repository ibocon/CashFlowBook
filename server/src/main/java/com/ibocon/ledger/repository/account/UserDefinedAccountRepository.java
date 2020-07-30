package com.ibocon.ledger.repository.account;

import java.util.List;

import com.ibocon.ledger.repository.user.User;

import org.springframework.data.repository.CrudRepository;

public interface UserDefinedAccountRepository extends CrudRepository<UserDefinedAccount, Long> {
    List<UserDefinedAccount> findByBelongTo(User user);
    List<UserDefinedAccount> findByBelongToAndAccountName(User user, String name);
}