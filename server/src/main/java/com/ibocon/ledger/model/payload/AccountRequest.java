package com.ibocon.ledger.model.payload;

import javax.validation.constraints.NotBlank;

import com.ibocon.ledger.model.BaseAccount;

import lombok.Data;

@Data
public class AccountRequest {

    Long id;

    @NotBlank
    BaseAccount base;

    @NotBlank
    String name;
}