package com.zup.transacoes.controllers;

import com.zup.transacoes.models.Cartao;
import com.zup.transacoes.models.EventoDeTransacao;
import com.zup.transacoes.models.TransacaoResponse;
import com.zup.transacoes.repositories.CartaoRepository;
import com.zup.transacoes.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/cartoes")
public class TransacaoController {

    private final TransacaoRepository transacaoRepository;

    private final CartaoRepository cartaoRepository;

    public TransacaoController(TransacaoRepository transacaoRepository, CartaoRepository cartaoRepository) {
        this.transacaoRepository = transacaoRepository;
        this.cartaoRepository = cartaoRepository;
    }


    @GetMapping("{idCartao}/transacoes")
    public ResponseEntity<Page<TransacaoResponse>> listarUltimasCompras(@PathVariable String idCartao,
                                                                        @PageableDefault(sort = "efetivadaEm", direction = Sort.Direction.DESC, size = 10) Pageable pageable){

        Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);

        if(possivelCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Page<EventoDeTransacao> compras = transacaoRepository.findByCartao_CodigoCartao(idCartao, pageable);
        return ResponseEntity.ok().body(TransacaoResponse.toDtoResponse(compras));
    }



}
