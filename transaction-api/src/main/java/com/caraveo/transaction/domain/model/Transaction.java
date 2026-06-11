package com.caraveo.transaction.domain.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Transaction {

    private Long id;
    private String operacion;
    private BigDecimal importe;
    private String cliente;
    private String referencia;
    private String estatus;
    private String secreto;

    public Transaction() {
    }

    public Transaction(Long id,
                       String operacion,
                       BigDecimal importe,
                       String cliente,
                       String referencia,
                       String estatus,
                       String secreto) {
        this.id = id;
        this.operacion = operacion;
        this.importe = importe;
        this.cliente = cliente;
        this.referencia = referencia;
        this.estatus = estatus;
        this.secreto = secreto;
    }
}