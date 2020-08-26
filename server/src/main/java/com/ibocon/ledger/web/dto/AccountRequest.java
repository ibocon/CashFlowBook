package com.ibocon.ledger.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccountRequest {

    @NotBlank
    String accountName;

}