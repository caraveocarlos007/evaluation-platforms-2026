package com.caraveo.transaction.application.dto;

public record UpdateTransactionStatusRequest(
        Long id,
        String referencia,
        String estatus
) {
}