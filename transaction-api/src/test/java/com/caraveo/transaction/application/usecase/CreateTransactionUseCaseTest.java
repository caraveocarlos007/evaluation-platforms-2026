package com.caraveo.transaction.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.caraveo.transaction.application.dto.CreateTransactionRequest;
import com.caraveo.transaction.application.dto.TransactionResponse;
import com.caraveo.transaction.domain.model.Transaction;
import com.caraveo.transaction.domain.ports.TransactionRepositoryPort;

@ExtendWith(MockitoExtension.class)
class CreateTransactionUseCaseTest {

    @Mock
    private TransactionRepositoryPort repositoryPort;

    @InjectMocks
    private CreateTransactionUseCase useCase;

    @Test
    void shouldCreateTransactionSuccessfully() {

        CreateTransactionRequest request =
                new CreateTransactionRequest(
                        "venta",
                        BigDecimal.valueOf(100),
                        "Carlos",
                        "abc123");

        Transaction savedTransaction =
                new Transaction(
                        1L,
                        "venta",
                        BigDecimal.valueOf(100),
                        "Carlos",
                        "123456",
                        "Aprobada",
                        "abc123");

        when(repositoryPort.save(any(Transaction.class)))
                .thenReturn(savedTransaction);

        TransactionResponse response =
                useCase.execute(request);

        assertNotNull(response);

        assertEquals(1L, response.id());

        assertEquals(
                "Aprobada",
                response.estatus());

        assertEquals(
                "venta",
                response.operacion());

        verify(repositoryPort, times(1))
                .save(any(Transaction.class));
    }
}