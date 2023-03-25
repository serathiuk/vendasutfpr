package com.serathiuk.vendas.assinantes;

import com.serathiuk.vendas.AppConfig;
import com.serathiuk.vendas.model.Venda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class InventarioListener {

    private static final Logger logger = LoggerFactory.getLogger(InventarioListener.class);

    @RabbitListener(queues = AppConfig.QUEUE_INVENTARIO)
    public void execute(Venda venda) {
        logger.info("Criando Inventario. Descriçäo: "+venda.getDescricao()+" Valor: "+venda.getValor()+" Quantidade: "+venda.getQuantidade());
    }
}
