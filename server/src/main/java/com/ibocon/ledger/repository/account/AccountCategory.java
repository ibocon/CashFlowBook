package com.ibocon.ledger.repository.account;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.ibocon.ledger.repository.TranslatedName;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class AccountCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(
        targetEntity = TranslatedName.class, 
        fetch = FetchType.LAZY)
    private TranslatedName main;
    
    @NotNull
    @ManyToOne(
        targetEntity = TranslatedName.class, 
        fetch = FetchType.LAZY)
    private TranslatedName sub;
}