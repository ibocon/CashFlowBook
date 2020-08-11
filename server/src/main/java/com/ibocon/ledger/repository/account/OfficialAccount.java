package com.ibocon.ledger.repository.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ibocon.ledger.repository.TranslatedString;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(
        name = "CODE_UNIQUE",
        columnNames = {"CODE"})})
public class OfficialAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long code;

    @ManyToOne
    private AccountCategory category;

    @ManyToOne
    private TranslatedString accountName;

}