package com.ibocon.ledger.web.dto;

import com.ibocon.ledger.repository.account.OfficialAccount;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccountRequest {

    @NotBlank
    OfficialAccount officialAccount;

    @NotBlank
    String accountName;
}