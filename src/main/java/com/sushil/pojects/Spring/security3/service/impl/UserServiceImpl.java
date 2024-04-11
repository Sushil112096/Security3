package com.sushil.pojects.Spring.security3.service.impl;

import com.sushil.pojects.Spring.security3.repository.userrepository;
import com.sushil.pojects.Spring.security3.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final userrepository userrepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userrepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("No user presnt with this mail"));
            }
        };
    }


}
