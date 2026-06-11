package com.caraveo.transaction.application.dto;

public record TransactionResponse(
        Long id,
        String estatus,
        String referencia,
        String operacion
) {
}