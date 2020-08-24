package com.ibocon.ledger.repository.account;

import com.ibocon.ledger.repository.TranslatedString;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class AccountCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TranslatedString parent;

    @ManyToOne
    private TranslatedString child;
}