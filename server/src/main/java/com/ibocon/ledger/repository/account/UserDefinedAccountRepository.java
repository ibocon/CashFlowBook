package com.ibocon.ledger.repository.account;

import com.ibocon.ledger.repository.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDefinedAccountRepository extends CrudRepository<UserDefinedAccount, Long> {
    List<UserDefinedAccount> findByBelongTo(User user);
    List<UserDefinedAccount> findByBelongToAndAccountName(User user, String name);
}