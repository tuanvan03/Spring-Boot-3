package com.example.identity.Configuration;

import com.example.identity.Enums.EnumRoles;
import com.example.identity.Repository.UserRepository;
import com.example.identity.entity.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationConfig {
    // Should user constructor, instead of autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner (UserRepository userRepository) {
        return args -> {
            // If it is the first time starting project
            if (userRepository.findByUsername("admin").isEmpty()) {
                // Set role ADMIN
                var roles = new HashSet<String>();
                roles.add(EnumRoles.ADMIN.name());

                // Create Admin
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                       // .roles(roles)
                        .build();

                // Save user
                userRepository.save(user);
                log.warn("Admin has been created with default username and password");
            }
        };
    }
}
