package com.caraveo.transaction.application.usecase;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.caraveo.transaction.application.dto.CreateTransactionRequest;
import com.caraveo.transaction.application.dto.TransactionResponse;
import com.caraveo.transaction.domain.model.Transaction;
import com.caraveo.transaction.domain.ports.TransactionRepositoryPort;

@Service
public class CreateTransactionUseCase {

    private final TransactionRepositoryPort repositoryPort;

    public CreateTransactionUseCase(
            TransactionRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public TransactionResponse execute(
            CreateTransactionRequest request) {

        String referencia =
                String.valueOf(
                        100000 +
                        new Random().nextInt(900000));

        Transaction transaction =
                new Transaction(
                        null,
                        request.operacion(),
                        request.importe(),
                        request.cliente(),
                        referencia,
                        "Aprobada",
                        request.secreto());

        Transaction saved =
                repositoryPort.save(transaction);

        return new TransactionResponse(
                saved.getId(),
                saved.getEstatus(),
                saved.getReferencia(),
                saved.getOperacion());
    }
}