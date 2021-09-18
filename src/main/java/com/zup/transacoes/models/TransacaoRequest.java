package com.zup.transacoes.models;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoRequest {


    @Id
    private String id;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    private EstabelecimentoRequest estabelecimento;

    @NotNull
    private CartaoRequest cartao;

    @NotNull
    private LocalDateTime efetivadaEm;


    @Deprecated
    public TransacaoRequest(){

    }

    public TransacaoRequest(String id, BigDecimal valor, EstabelecimentoRequest estabelecimento, CartaoRequest cartao, LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }


    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoRequest getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoRequest getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public EventoDeTransacao toModel() {
        Estabelecimento estabelecimento = this.estabelecimento.toModel();
        Cartao cartao = this.cartao.toModel();
        return new EventoDeTransacao(id, valor, estabelecimento, cartao, efetivadaEm);
    }
}
