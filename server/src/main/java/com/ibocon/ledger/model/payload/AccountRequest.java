package com.ibocon.ledger.model.payload;

import javax.validation.constraints.NotBlank;

import com.ibocon.ledger.domain.account.OfficialAccount;

import lombok.Data;

@Data
public class AccountRequest {

    @NotBlank
    OfficialAccount officialAccount;

    @NotBlank
    String accountName;
}