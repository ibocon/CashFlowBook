package com.ibocon.ledger.web.dto;

import javax.validation.constraints.NotBlank;

import com.ibocon.ledger.repository.domain.account.OfficialAccount;

import lombok.Data;

@Data
public class AccountRequest {

    @NotBlank
    OfficialAccount officialAccount;

    @NotBlank
    String accountName;
}