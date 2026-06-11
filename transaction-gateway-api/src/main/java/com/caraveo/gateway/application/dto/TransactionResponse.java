package com.caraveo.gateway.application.dto;

public record TransactionResponse(

        Long id,
        String estatus,
        String referencia,
        String operacion) {
}