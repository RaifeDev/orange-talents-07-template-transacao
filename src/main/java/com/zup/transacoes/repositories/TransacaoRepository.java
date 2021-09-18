package com.zup.transacoes.repositories;

import com.zup.transacoes.models.EventoDeTransacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<EventoDeTransacao, String> {


    Page<EventoDeTransacao> findByCartao_CodigoCartao(String id, Pageable pageable);
}
