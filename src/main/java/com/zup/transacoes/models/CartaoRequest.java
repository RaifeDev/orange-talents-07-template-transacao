package com.zup.transacoes.models;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CartaoRequest {

    @NotBlank
    private String id;

    @NotBlank
    @Email
    private String email;

    @Deprecated
    public CartaoRequest(){}

    public CartaoRequest(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Cartao toModel() {
        return new Cartao(id, email);
    }
}
