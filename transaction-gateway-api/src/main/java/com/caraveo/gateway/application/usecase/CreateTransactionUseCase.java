package com.caraveo.gateway.application.usecase;

import org.springframework.stereotype.Service;

import com.caraveo.gateway.application.dto.CreateTransactionRequest;
import com.caraveo.gateway.application.dto.TransactionResponse;
import com.caraveo.gateway.infrastructure.client.TransactionApiClient;

@Service
public class CreateTransactionUseCase {

    private final TransactionApiClient client;

    public CreateTransactionUseCase(TransactionApiClient client) {
        this.client = client;
    }

    public TransactionResponse execute(CreateTransactionRequest request) {
        return client.create(request);
    }
}