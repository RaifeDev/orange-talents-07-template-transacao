package com.zup.transacoes.listeners;

import com.zup.transacoes.models.EventoDeTransacao;
import com.zup.transacoes.models.TransacaoRequest;
import com.zup.transacoes.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerDeTransacao {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(TransacaoRequest transacaoRequest){
        EventoDeTransacao transacao = transacaoRequest.toModel();
        transacaoRepository.save(transacao);
    }


}
