package com.caraveo.transaction.infrastructure.persistence.adapter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.caraveo.transaction.domain.model.Transaction;
import com.caraveo.transaction.domain.ports.TransactionRepositoryPort;
import com.caraveo.transaction.infrastructure.persistence.entity.TransactionEntity;
import com.caraveo.transaction.infrastructure.persistence.repository.TransactionJpaRepository;

@Component
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final TransactionJpaRepository repository;

    public TransactionRepositoryAdapter(TransactionJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction save(Transaction transaction) {

        TransactionEntity savedEntity =
                repository.save(toEntity(transaction));

        return toDomain(savedEntity);
    }

    @Transactional
    @Override
    public Transaction updateStatus(
            Long id,
            String referencia,
            String estatus) {

        repository.updateStatus(
                id,
                referencia,
                estatus);

        TransactionEntity entity =
                repository.findById(id)
                        .orElseThrow();

        return toDomain(entity);
    }

    @Override
    public Page<Transaction> findAll(
            int page,
            int size,
            String sortBy) {

        return repository.findAll(
                        org.springframework.data.domain.PageRequest.of(
                                page,
                                size,
                                org.springframework.data.domain.Sort.by(sortBy)))
                .map(this::toDomain);
    }

    private TransactionEntity toEntity(Transaction transaction) {

        TransactionEntity entity = new TransactionEntity();

        entity.setId(transaction.getId());
        entity.setOperacion(transaction.getOperacion());
        entity.setImporte(transaction.getImporte());
        entity.setCliente(transaction.getCliente());
        entity.setReferencia(transaction.getReferencia());
        entity.setEstatus(transaction.getEstatus());
        entity.setSecreto(transaction.getSecreto());

        return entity;
    }

    private Transaction toDomain(TransactionEntity entity) {

        return new Transaction(
                entity.getId(),
                entity.getOperacion(),
                entity.getImporte(),
                entity.getCliente(),
                entity.getReferencia(),
                entity.getEstatus(),
                entity.getSecreto()
        );
    }
}