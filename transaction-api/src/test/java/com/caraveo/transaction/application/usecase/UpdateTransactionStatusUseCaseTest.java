package com.caraveo.transaction.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.caraveo.transaction.application.dto.UpdateTransactionStatusRequest;
import com.caraveo.transaction.domain.model.Transaction;
import com.caraveo.transaction.domain.ports.TransactionRepositoryPort;

@ExtendWith(MockitoExtension.class)
class UpdateTransactionStatusUseCaseTest {

    @Mock
    private TransactionRepositoryPort repositoryPort;

    @InjectMocks
    private UpdateTransactionStatusUseCase useCase;

    @Test
    void shouldUpdateTransactionStatusSuccessfully() {

        UpdateTransactionStatusRequest request =
                new UpdateTransactionStatusRequest(
                        1L,
                        "123456",
                        "Cancelada");

        Transaction transaction =
                new Transaction(
                        1L,
                        "venta",
                        BigDecimal.valueOf(100),
                        "Carlos",
                        "123456",
                        "Cancelada",
                        "abc123");

        when(repositoryPort.updateStatus(
                1L,
                "123456",
                "Cancelada"))
                .thenReturn(transaction);

        Transaction response =
                useCase.execute(request);

        assertNotNull(response);

        assertEquals(
                "Cancelada",
                response.getEstatus());

        verify(repositoryPort, times(1))
                .updateStatus(
                        1L,
                        "123456",
                        "Cancelada");
    }
}