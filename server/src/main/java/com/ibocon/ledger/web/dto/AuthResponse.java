package com.ibocon.ledger.web.dto;

import lombok.RequiredArgsConstructor;
import lombok.Data;

@Data
@RequiredArgsConstructor
public class AuthResponse {
    private final String accessToken;
    private String tokenType = "Bearer";
}