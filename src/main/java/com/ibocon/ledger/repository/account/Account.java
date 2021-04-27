package com.ibocon.ledger.repository.account;

import com.ibocon.ledger.repository.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Convert(converter = LedgerPathConverter.class)
    private final LedgerPath path;

    @ManyToOne
    @JoinColumn(nullable = false)
    private final User belongTo;

    @Column(nullable = false)
    private final String name;

    @Builder
    public Account(User belongTo, LedgerPath path, String name) {
        this.belongTo = belongTo; this.path = path; this.name = name;
    }
}