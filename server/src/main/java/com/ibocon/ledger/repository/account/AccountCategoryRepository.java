package com.ibocon.ledger.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountCategoryRepository extends JpaRepository<AccountCategory, Long> {

    List<AccountCategory> findByPathStartingWith(LedgerPath path);
}
