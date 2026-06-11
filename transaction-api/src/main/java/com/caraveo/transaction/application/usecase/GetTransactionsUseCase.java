package com.caraveo.transaction.application.usecase;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.caraveo.transaction.domain.model.Transaction;
import com.caraveo.transaction.domain.ports.TransactionRepositoryPort;

@Service
public class GetTransactionsUseCase {

    private final TransactionRepositoryPort repositoryPort;

    public GetTransactionsUseCase(
            TransactionRepositoryPort repositoryPort) {

        this.repositoryPort = repositoryPort;
    }

    public Page<Transaction> execute(
            int page,
            int size,
            String sortBy) {

        return repositoryPort.findAll(
                page,
                size,
                sortBy);
    }
}