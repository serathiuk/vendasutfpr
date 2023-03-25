package com.serathiuk.vendas.assinantes;

import com.serathiuk.vendas.AppConfig;
import com.serathiuk.vendas.model.Venda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NfeListener {

    private static final Logger logger = LoggerFactory.getLogger(NfeListener.class);

    @RabbitListener(queues = AppConfig.QUEUE_NFE)
    public void execute(Venda venda) {
        logger.info("Criando NF-e. Descriçäo: "+venda.getDescricao()+" Valor: "+venda.getValor()+" Quantidade: "+venda.getQuantidade());
    }

}
