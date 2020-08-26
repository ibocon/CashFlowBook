package com.ibocon.ledger.repository.account;

import com.ibocon.ledger.repository.TranslatedString;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class RootAccountCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private final AccountingStandard standard;

    @ManyToOne
    private final TranslatedString document;

    private final boolean isDebit;

    @ManyToOne
    private final TranslatedString name;

}