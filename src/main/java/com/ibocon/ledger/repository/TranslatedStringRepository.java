package com.ibocon.ledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslatedStringRepository extends JpaRepository<TranslatedString, Long> {
    TranslatedString findByKorean(String korean);
}
