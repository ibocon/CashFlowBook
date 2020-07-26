package com.ibocon.ledger.repository.account;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.ibocon.ledger.repository.user.LedgerUser;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor()
@Entity
public class UserDefinedAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(
        targetEntity = OfficialAccount.class,
        fetch = FetchType.LAZY
    )
    final private OfficialAccount officialAccount;

    @ManyToOne(
        targetEntity = LedgerUser.class,
        fetch = FetchType.LAZY
    )
    private LedgerUser belongTo;

    @NotNull
    final private String accountName;

}