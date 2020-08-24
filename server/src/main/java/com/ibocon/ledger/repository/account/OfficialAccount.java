package com.ibocon.ledger.repository.account;

import com.ibocon.ledger.repository.TranslatedString;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(
        name = "ACCOUNT_NAME_UNIQUE",
        columnNames = {"ACCOUNT_NAME_ID"})})
public class OfficialAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AccountCategory category;

    @ManyToOne
    private TranslatedString accountName;

}