package com.ibocon.ledger.repository.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ibocon.ledger.repository.TranslatedString;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class AccountCategory {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private TranslatedString main;

    @ManyToOne
    private TranslatedString sub;
}