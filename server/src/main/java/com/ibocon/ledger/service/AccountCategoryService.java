package com.ibocon.ledger.service;

import com.ibocon.ledger.repository.TranslatedStringRepository;
import com.ibocon.ledger.repository.account.AccountCategory;
import com.ibocon.ledger.repository.account.AccountCategoryRepository;
import com.ibocon.ledger.repository.account.LedgerPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountCategoryService {

    private final TranslatedStringRepository translatedStringRepository;
    private final AccountCategoryRepository accountCategoryRepository;

    @Transactional
    public List<AccountCategory> GetSubcategories(LedgerPath path) {
        var accountCategories = accountCategoryRepository.findByPathStartingWith(path);
        return accountCategories;
    }
}
