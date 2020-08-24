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
public class UserDefinedAccount {

    @Id
    @GeneratedValue
    private Long id;
 
    @ManyToOne
    @JoinColumn(nullable = false)
    private OfficialAccount officialLink;

    @ManyToOne
    @JoinColumn(nullable = false)
    final private User belongTo;

    @Column(nullable = false)
    private String accountName;

    @Builder
    public UserDefinedAccount(User belongTo, OfficialAccount officialLink, String accountName) {
        this.belongTo = belongTo; this.officialLink = officialLink; this.accountName = accountName;
    }
}