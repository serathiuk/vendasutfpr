package com.serathiuk.vendas;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class AppConfig {


    public static final String TOPICO_VENDA_SERVICO = "ex.venda.servico";
    public static final String TOPICO_VENDA_MATERIAL = "ex.venda.material";
    public static final String QUEUE_PAGAMENTO = "q.vendas.pagamento";
    public static final String QUEUE_INVENTARIO = "q.vendas.inventario";
    public static final String QUEUE_NFE= "q.vendas.nfe";
    public static final String QUEUE_NFSE= "q.vendas.nfse";

    // Configura o Spring Boot para utilizar JSON como o formato de serializaçäo das mensagens
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    // Pré-requisito para o Spring Boot utilizar JSON para fazer as conversões das mensagens do RabbitMQ
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Faz a criaçäo das queues, exchanges e bindings caso não existam ainda no RabbitMQ
    @Bean
    public AmqpAdmin amqpAdmin(RabbitTemplate rabbitTemplate) {
        var ampq = new RabbitAdmin(rabbitTemplate);
        ampq.declareQueue(new Queue(QUEUE_PAGAMENTO));
        ampq.declareQueue(new Queue(QUEUE_INVENTARIO));
        ampq.declareQueue(new Queue(QUEUE_NFE));
        ampq.declareQueue(new Queue(QUEUE_NFSE));

        ampq.declareExchange(new FanoutExchange(TOPICO_VENDA_MATERIAL));
        ampq.declareExchange(new FanoutExchange(TOPICO_VENDA_SERVICO));

        createBinding(ampq, TOPICO_VENDA_MATERIAL, QUEUE_PAGAMENTO);
        createBinding(ampq, TOPICO_VENDA_MATERIAL, QUEUE_INVENTARIO);
        createBinding(ampq, TOPICO_VENDA_MATERIAL, QUEUE_NFE);
        createBinding(ampq, TOPICO_VENDA_SERVICO, QUEUE_PAGAMENTO);
        createBinding(ampq, TOPICO_VENDA_SERVICO, QUEUE_INVENTARIO);
        createBinding(ampq, TOPICO_VENDA_SERVICO, QUEUE_NFSE);
        return ampq;
    }

    // Método utilitário para criar os bindings.
    private void createBinding(RabbitAdmin admin, String exchange, String queueName) {
        admin.declareBinding(new Binding(queueName, Binding.DestinationType.QUEUE, exchange, "", new HashMap<>()));
    }

}
