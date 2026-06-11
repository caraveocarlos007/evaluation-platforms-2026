package com.caraveo.transaction.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.caraveo.transaction.infrastructure.persistence.entity.TransactionEntity;

public interface TransactionJpaRepository
        extends JpaRepository<TransactionEntity, Long> {

    @Modifying
    @Query("""
            UPDATE TransactionEntity t
               SET t.estatus = :estatus
             WHERE t.id = :id
               AND t.referencia = :referencia
           """)
    int updateStatus(
            @Param("id") Long id,
            @Param("referencia") String referencia,
            @Param("estatus") String estatus);
}