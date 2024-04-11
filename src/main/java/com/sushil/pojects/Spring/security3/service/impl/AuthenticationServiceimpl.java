package com.sushil.pojects.Spring.security3.service.impl;

import com.sushil.pojects.Spring.security3.dto.JwtAuthenticationResponse;
import com.sushil.pojects.Spring.security3.dto.RefreshTokenRequest;
import com.sushil.pojects.Spring.security3.dto.SigninRequest;
import com.sushil.pojects.Spring.security3.dto.SignupRequest;
import com.sushil.pojects.Spring.security3.entities.Role;
import com.sushil.pojects.Spring.security3.entities.User;
import com.sushil.pojects.Spring.security3.repository.userrepository;
import com.sushil.pojects.Spring.security3.service.AuthenticationService;
import com.sushil.pojects.Spring.security3.service.JWTservice;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceimpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final userrepository userrepository;
    private final JWTservice jwTservice;
    private final AuthenticationManager authenticationManager;

    public User Signup(SignupRequest request) {
        User u = new User();
        u.setEmail(request.getEmail());
        u.setFirstname(request.getFirstName());
        u.setLastname(request.getLastName());
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        u.setRole(Role.USER);
        return userrepository.save(u);
    }

    public JwtAuthenticationResponse Signin(SigninRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userrepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwTservice.generateToken(user);
        var refreshtoken = jwTservice.generaterefreshToken(new HashMap<>(), user);
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(jwt);
        response.setRefreshtoken(refreshtoken);
        return response;
    }

    public JwtAuthenticationResponse Refreshtoken(RefreshTokenRequest refreshTokenRequest) {
        String useremail = jwTservice.extractUsername(refreshTokenRequest.getToken());
        User user = userrepository.findByEmail(useremail).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (jwTservice.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwTservice.generateToken(user);
            JwtAuthenticationResponse response = new JwtAuthenticationResponse();
            response.setToken(jwt);
            response.setRefreshtoken(refreshTokenRequest.getToken());
            return response;
        }
        return null;
    }
}
