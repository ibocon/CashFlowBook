package com.ibocon.ledger.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountCategoryRepository extends JpaRepository<AccountCategory, Long> {
    List<AccountCategory> findByPathContains(LedgerPath path);
}
