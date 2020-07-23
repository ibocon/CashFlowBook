package com.ibocon.ledger.domain.account;

import java.util.List;
import com.ibocon.ledger.domain.user.LedgerUser;
import org.springframework.data.repository.CrudRepository;

public interface UserDefinedAccountRepository extends CrudRepository<UserDefinedAccount, Long> {
    List<UserDefinedAccount> findByBelongTo(LedgerUser user);
    List<UserDefinedAccount> findByBelongToAndAccountName(LedgerUser user, String name);
}