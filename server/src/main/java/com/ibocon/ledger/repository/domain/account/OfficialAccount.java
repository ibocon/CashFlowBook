package com.ibocon.ledger.repository.domain.account;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.ibocon.ledger.repository.domain.TranslatedName;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class OfficialAccount {

    @Id
    private Long code;

    @NotNull
    @ManyToOne(
        targetEntity = AccountCategory.class,
        fetch = FetchType.LAZY
    )
    private AccountCategory category;

    @NotNull
    @ManyToOne(
        targetEntity = TranslatedName.class,
        fetch = FetchType.LAZY
    )
    private TranslatedName accountName;
}