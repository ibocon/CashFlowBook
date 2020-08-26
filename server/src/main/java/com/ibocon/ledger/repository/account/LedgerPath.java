package com.ibocon.ledger.repository.account;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class LedgerPath {

    private Long rootAccountCategoryId;
    private Long accountCategoryId;
    private List<Long> AccountIds;

    @Builder
    public LedgerPath(Long rootAccountCategoryId, Long accountCategoryId, List<Long> AccountIds) {
        this.rootAccountCategoryId = rootAccountCategoryId;
        this.accountCategoryId = accountCategoryId;
        this.AccountIds = AccountIds;
    }
}
