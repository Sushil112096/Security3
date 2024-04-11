package com.sushil.pojects.Spring.security3;

import com.sushil.pojects.Spring.security3.entities.Role;
import com.sushil.pojects.Spring.security3.entities.User;
import com.sushil.pojects.Spring.security3.repository.userrepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Springsecurity3Application implements CommandLineRunner {
    @Autowired
    private userrepository userrepository;

    public static void main(String[] args) {
        SpringApplication.run(Springsecurity3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User adminaccount = userrepository.findByRole(Role.ADMIN);
        if (adminaccount == null) {
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setRole(Role.ADMIN);
            user.setFirstname("admin");
            user.setLastname("admin");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userrepository.save(user);
        }
    }
}
