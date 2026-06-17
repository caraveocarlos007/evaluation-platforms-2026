package com.caraveo.transaction.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caraveo.transaction.infrastructure.persistence.entity.UserEntity;

public interface UserJpaRepository
        extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(
            String username);
}