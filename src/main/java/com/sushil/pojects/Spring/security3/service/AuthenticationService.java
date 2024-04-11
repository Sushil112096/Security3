package com.sushil.pojects.Spring.security3.service;

import com.sushil.pojects.Spring.security3.dto.JwtAuthenticationResponse;
import com.sushil.pojects.Spring.security3.dto.RefreshTokenRequest;
import com.sushil.pojects.Spring.security3.dto.SigninRequest;
import com.sushil.pojects.Spring.security3.dto.SignupRequest;
import com.sushil.pojects.Spring.security3.entities.User;

public interface AuthenticationService {
    User Signup(SignupRequest request);

    JwtAuthenticationResponse Signin(SigninRequest request);

    JwtAuthenticationResponse Refreshtoken(RefreshTokenRequest refreshTokenRequest);
}
