package com.caraveo.transaction.infrastructure.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.caraveo.transaction.infrastructure.persistence.entity.UserEntity;
import com.caraveo.transaction.infrastructure.persistence.repository.UserJpaRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(
            UserJpaRepository repository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (repository.count() == 0) {

                UserEntity user = new UserEntity();

                user.setUsername("admin");

                user.setPassword(passwordEncoder.encode("1234"));

                user.setRol("ADMIN");

                repository.save(user);

                System.out.println("Usuario admin creado");
            }
        };
    }
}