package com.caraveo.transaction.application.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateTransactionRequest(

        @NotBlank(message = "Operacion es requerida")
        @Pattern(
                regexp = "^[a-zA-Z ]+$",
                message = "Operacion solo permite letras")
        String operacion,

        @DecimalMin(
                value = "0.01",
                message = "Importe debe ser mayor a cero")
        BigDecimal importe,

        @NotBlank(message = "Cliente es requerido")
        @Pattern(
                regexp = "^[a-zA-Z ]+$",
                message = "Cliente solo permite letras")
        String cliente,

        @NotBlank(message = "Secreto es requerido")
        String secreto

) {
}