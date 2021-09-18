package com.zup.transacoes.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_cartoes")
public class Cartao {

    @Id
    private String codigoCartao;

    @NotBlank
    @Email
    private String email;


    @Deprecated
    public Cartao(){}


    public Cartao(String codigoCartao, String email) {
        this.codigoCartao = codigoCartao;
        this.email = email;
    }
}
