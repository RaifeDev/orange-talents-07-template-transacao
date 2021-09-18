package com.zup.transacoes.config;

import com.zup.transacoes.models.EventoDeTransacao;
import com.zup.transacoes.models.TransacaoRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class kafkaConfiguration {

    private final KafkaProperties kafkaProperties;


    public kafkaConfiguration(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    // propriedades do nosso consumidor.
    public Map<String, Object> consumerConfigurations(){
        Map<String, Object> properties = new HashMap<>();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getConsumer().getKeyDeserializer());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getConsumer().getValueDeserializer());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getConsumer().getAutoOffsetReset());

        return properties;
    }


    //configurar nosso consumidor
    @Bean
    public ConsumerFactory<String, TransacaoRequest> transactionConsumerFactory(){
        StringDeserializer stringDeserializer = new StringDeserializer();
        JsonDeserializer<TransacaoRequest> jsonDeserializer = new JsonDeserializer<>(TransacaoRequest.class, false);

        return new DefaultKafkaConsumerFactory<>(consumerConfigurations(), stringDeserializer, jsonDeserializer);
    }

    //Precisamos configurar nosso listener
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransacaoRequest> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, TransacaoRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(transactionConsumerFactory());

        return factory;
    }

}
