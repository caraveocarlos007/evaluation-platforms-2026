package com.caraveo.transaction.application.usecase;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.caraveo.transaction.infrastructure.persistence.entity.UserEntity;
import com.caraveo.transaction.infrastructure.persistence.repository.UserJpaRepository;

@Service
public class LoginUseCase {

    private final UserJpaRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginUseCase(UserJpaRepository userRepository,
                        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean login(String username, String password) {

        Optional<UserEntity> userOpt =
                userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            return false;
        }

        UserEntity user = userOpt.get();

        return passwordEncoder.matches(
                password,
                user.getPassword()
        );
    }
}