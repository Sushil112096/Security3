package com.sushil.pojects.Spring.security3.service.impl;

import com.sushil.pojects.Spring.security3.entities.User;
import com.sushil.pojects.Spring.security3.service.JWTservice;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.antlr.v4.runtime.Token;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTserviceimpl implements JWTservice {

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }
//    public String generaterefreshToken(HashMap<String,Object> claims,UserDetails userDetails) {
//        return Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
//    }

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode("1231231231231231231231231231231231231231231231231123123123123");
        return Keys.hmacShaKeyFor(key);
    }

    //extract the username from token
    public String extractUsername(String token) {
        return extractclaims(token, Claims::getSubject);
    }

    private <T> T extractclaims(String token, Function<Claims, T> claimsresolver) {
        final Claims claims = extractallclaims(token);
        return claimsresolver.apply(claims);

    }

    private Claims extractallclaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public String generaterefreshToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts.builder().setClaims(extraClaims).setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 604800000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private boolean isTokenExpired(String token) {
        return extractclaims(token, Claims::getExpiration).before(new Date());
    }


}
