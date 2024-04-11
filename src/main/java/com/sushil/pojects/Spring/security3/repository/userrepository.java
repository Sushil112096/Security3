package com.sushil.pojects.Spring.security3.repository;

import com.sushil.pojects.Spring.security3.entities.Role;
import com.sushil.pojects.Spring.security3.entities.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userrepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);

    public User findByRole(Role role);
}
