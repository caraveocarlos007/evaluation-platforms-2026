package com.caraveo.transaction.domain.ports;

import com.caraveo.transaction.domain.model.Transaction;
import org.springframework.data.domain.Page;

public interface TransactionRepositoryPort {

    Transaction save(Transaction transaction);

    Transaction updateStatus(
            Long id,
            String referencia,
            String estatus);

    Page<Transaction> findAll(
            int page,
            int size,
            String sortBy);
}