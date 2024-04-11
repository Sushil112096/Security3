package com.sushil.pojects.Spring.security3.controller;

import com.sushil.pojects.Spring.security3.dto.JwtAuthenticationResponse;
import com.sushil.pojects.Spring.security3.dto.RefreshTokenRequest;
import com.sushil.pojects.Spring.security3.dto.SigninRequest;
import com.sushil.pojects.Spring.security3.dto.SignupRequest;
import com.sushil.pojects.Spring.security3.entities.User;
import com.sushil.pojects.Spring.security3.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class Authenticationcontroller {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(authenticationService.Signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok(authenticationService.Signin(signinRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.Refreshtoken(refreshTokenRequest));
    }

}
