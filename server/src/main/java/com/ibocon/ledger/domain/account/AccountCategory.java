package com.ibocon.ledger.domain.account;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.ibocon.ledger.domain.TranslatedName;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class AccountCategory {
    @NotNull
    @ManyToOne(
        targetEntity = TranslatedName.class, 
        fetch = FetchType.LAZY)
    private TranslatedName main;
    
    @Id
    @ManyToOne(
        targetEntity = TranslatedName.class, 
        fetch = FetchType.LAZY)
    private TranslatedName sub;
}