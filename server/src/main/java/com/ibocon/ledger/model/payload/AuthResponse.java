package com.ibocon.ledger.model.payload;

import lombok.RequiredArgsConstructor;
import lombok.Data;

@Data
@RequiredArgsConstructor
public class AuthResponse {
    private final String accessToken;
    private String tokenType = "Bearer";
}