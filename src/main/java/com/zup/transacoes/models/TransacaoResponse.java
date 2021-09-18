package com.zup.transacoes.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoResponse {

    private String id;

    private BigDecimal valor;

    private LocalDateTime efetuadaEm;


    public TransacaoResponse(String id, BigDecimal valor, LocalDateTime efetuadaEm) {
        this.id = id;
        this.valor = valor;
        this.efetuadaEm = efetuadaEm;
    }


    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getEfetuadaEm() {
        return efetuadaEm;
    }

    public static Page<TransacaoResponse> toDtoResponse(Page<EventoDeTransacao> compras){
        return compras.map(compra -> new TransacaoResponse(compra.getId(), compra.getValor(), compra.getEfetivadaEm()));
    }

}
