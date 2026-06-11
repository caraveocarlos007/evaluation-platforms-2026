package com.caraveo.transaction.infrastructure.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.caraveo.transaction.application.dto.CreateTransactionRequest;
import com.caraveo.transaction.application.dto.TransactionResponse;
import com.caraveo.transaction.application.dto.UpdateTransactionStatusRequest;
import com.caraveo.transaction.application.usecase.CreateTransactionUseCase;
import com.caraveo.transaction.application.usecase.GetTransactionsUseCase;
import com.caraveo.transaction.application.usecase.UpdateTransactionStatusUseCase;
import com.caraveo.transaction.domain.model.Transaction;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * REST Controller for transaction management.
 *
 * Technical Assessment 2026
 * Hexagonal Architecture + Spring Boot
 */
@Tag(
        name = "Transactions",
        description = "Operations for transaction management"
)
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final CreateTransactionUseCase useCase;
    private final UpdateTransactionStatusUseCase updateUseCase;
    private final GetTransactionsUseCase getTransactionsUseCase;

    public TransactionController(
            CreateTransactionUseCase useCase,
            UpdateTransactionStatusUseCase updateUseCase,
            GetTransactionsUseCase getTransactionsUseCase) {

        this.useCase = useCase;
        this.updateUseCase = updateUseCase;
        this.getTransactionsUseCase = getTransactionsUseCase;
    }

    /**
     * Creates a new transaction.
     */
    @Operation(
            summary = "Create transaction",
            description = "Creates a transaction and generates a unique reference number"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse create(
            @Valid @RequestBody CreateTransactionRequest request) {

        return useCase.execute(request);
    }

    /**
     * Updates transaction status.
     */
    @Operation(
            summary = "Update transaction status",
            description = "Updates a transaction from Aprobada to Cancelada"
    )
    @PatchMapping("/status")
    public Transaction updateStatus(
            @RequestBody UpdateTransactionStatusRequest request) {

        return updateUseCase.execute(request);
    }

    /**
     * Retrieves paginated transactions.
     */
    @Operation(
            summary = "Get transactions",
            description = "Returns paginated transaction records"
    )
    @GetMapping
    public Page<Transaction> findAll(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy) {

        return getTransactionsUseCase.execute(
                page,
                size,
                sortBy);
    }
}