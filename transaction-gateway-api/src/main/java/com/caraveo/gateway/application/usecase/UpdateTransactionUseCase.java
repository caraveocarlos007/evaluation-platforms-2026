package com.caraveo.gateway.application.usecase;

import org.springframework.stereotype.Service;

import com.caraveo.gateway.application.dto.TransactionResponse;
import com.caraveo.gateway.application.dto.UpdateTransactionStatusRequest;
import com.caraveo.gateway.infrastructure.client.TransactionApiClient;

@Service
public class UpdateTransactionUseCase {

    private final TransactionApiClient client;

    public UpdateTransactionUseCase(TransactionApiClient client) {
        this.client = client;
    }

    public TransactionResponse execute(UpdateTransactionStatusRequest request) {
        return client.updateStatus(request);
    }
}