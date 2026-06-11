package com.caraveo.transaction.application.usecase;

import org.springframework.stereotype.Service;

import com.caraveo.transaction.application.dto.UpdateTransactionStatusRequest;
import com.caraveo.transaction.domain.model.Transaction;
import com.caraveo.transaction.domain.ports.TransactionRepositoryPort;

@Service
public class UpdateTransactionStatusUseCase {

    private final TransactionRepositoryPort repositoryPort;

    public UpdateTransactionStatusUseCase(
            TransactionRepositoryPort repositoryPort) {

        this.repositoryPort = repositoryPort;
    }

    public Transaction execute(
            UpdateTransactionStatusRequest request) {

        return repositoryPort.updateStatus(
                request.id(),
                request.referencia(),
                request.estatus());
    }
}