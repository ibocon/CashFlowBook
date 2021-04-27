package com.ibocon.ledger.repository.account;

import com.ibocon.ledger.repository.TranslatedString;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RootAccountCategoryRepository extends JpaRepository<RootAccountCategory, Long> {
    List<RootAccountCategory> findByDocument(TranslatedString documentString);
}
