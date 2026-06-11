package com.caraveo.gateway.infrastructure.controller;

import org.springframework.web.bind.annotation.*;

import com.caraveo.gateway.application.dto.*;
import com.caraveo.gateway.application.usecase.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/gateway/transactions")
public class TransactionGatewayController {

    private final CreateTransactionUseCase createUseCase;
    private final UpdateTransactionUseCase updateUseCase;
    private final GetTransactionsUseCase getUseCase;

    public TransactionGatewayController(
            CreateTransactionUseCase createUseCase,
            UpdateTransactionUseCase updateUseCase,
            GetTransactionsUseCase getUseCase) {

        this.createUseCase = createUseCase;
        this.updateUseCase = updateUseCase;
        this.getUseCase = getUseCase;
    }

    // CREATE
    @PostMapping
    public TransactionResponse create(
            @Valid @RequestBody CreateTransactionRequest request) {

        return createUseCase.execute(request);
    }

    // PATCH CANCELAR
    @PatchMapping("/status")
    public TransactionResponse update(
            @RequestBody UpdateTransactionStatusRequest request) {

        return updateUseCase.execute(request);
    }

    // GET PAGINADO
    @GetMapping
    public PageResponse getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        return getUseCase.execute(page, size, sortBy);
    }
}