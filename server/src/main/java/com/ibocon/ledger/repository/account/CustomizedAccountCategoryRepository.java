package com.ibocon.ledger.repository.account;

import java.util.List;

public interface CustomizedAccountCategoryRepository {
    List<AccountCategory> findByPathStartingWith(LedgerPath path);
}
