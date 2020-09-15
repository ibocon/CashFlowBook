package com.ibocon.ledger.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountCategoryRepository extends JpaRepository<AccountCategory, Long>, CustomizedAccountCategoryRepository {
    List<AccountCategory> findByPathStartingWith(LedgerPath path);
}
