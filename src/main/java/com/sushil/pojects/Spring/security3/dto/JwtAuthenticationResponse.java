package com.sushil.pojects.Spring.security3.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String refreshtoken;
}
