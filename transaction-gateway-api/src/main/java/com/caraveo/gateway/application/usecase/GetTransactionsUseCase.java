package com.caraveo.gateway.application.usecase;

import org.springframework.stereotype.Service;

import com.caraveo.gateway.application.dto.PageResponse;
import com.caraveo.gateway.infrastructure.client.TransactionApiClient;

@Service
public class GetTransactionsUseCase {

    private final TransactionApiClient client;

    public GetTransactionsUseCase(TransactionApiClient client) {
        this.client = client;
    }

    public PageResponse execute(int page, int size, String sortBy) {
        return client.getAll(page, size, sortBy);
    }
}