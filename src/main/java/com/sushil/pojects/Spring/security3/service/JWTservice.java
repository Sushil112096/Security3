package com.sushil.pojects.Spring.security3.service;

import com.sushil.pojects.Spring.security3.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

public interface JWTservice {
    String generateToken(UserDetails userDetails);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generaterefreshToken(Map<String, Object> extraClaims, UserDetails user);
}
