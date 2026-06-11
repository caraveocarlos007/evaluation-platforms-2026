package com.caraveo.transaction.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.caraveo.transaction.domain.model.Transaction;
import com.caraveo.transaction.domain.ports.TransactionRepositoryPort;

@ExtendWith(MockitoExtension.class)
class GetTransactionsUseCaseTest {

    @Mock
    private TransactionRepositoryPort repositoryPort;

    @InjectMocks
    private GetTransactionsUseCase useCase;

    @Test
    void shouldReturnPagedTransactions() {

        List<Transaction> transactions =
                List.of(
                        new Transaction(
                                1L,
                                "venta",
                                BigDecimal.valueOf(100),
                                "Carlos",
                                "123456",
                                "Aprobada",
                                "abc123"));

        Page<Transaction> page =
                new PageImpl<>(transactions);

        when(repositoryPort.findAll(
                0,
                10,
                "id"))
                .thenReturn(page);

        Page<Transaction> result =
                useCase.execute(
                        0,
                        10,
                        "id");

        assertEquals(
                1,
                result.getTotalElements());

        verify(repositoryPort, times(1))
                .findAll(
                        0,
                        10,
                        "id");
    }
}