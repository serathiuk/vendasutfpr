package com.serathiuk.vendas.controller;

import com.serathiuk.vendas.AppConfig;
import com.serathiuk.vendas.model.Venda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendaController {

    private static final Logger logger = LoggerFactory.getLogger(VendaController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping(value = "/venda/material")
    public ResponseEntity<?> criarVendaDeMaterial(@RequestBody Venda venda) {
        logger.info("Criando Venda de Material. Descriçäo: "+venda.getDescricao()+" Valor: "+venda.getValor()+" Quantidade: "+venda.getQuantidade());

        rabbitTemplate.convertAndSend(AppConfig.TOPICO_VENDA_MATERIAL, "", venda);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/venda/servico")
    public ResponseEntity<?> criarVendaDeServico(@RequestBody Venda venda) {
        logger.info("Criando Venda de Servico. Descriçäo: "+venda.getDescricao()+" Valor: "+venda.getValor()+" Quantidade: "+venda.getQuantidade());

        rabbitTemplate.convertAndSend(AppConfig.TOPICO_VENDA_SERVICO, "", venda);

        return ResponseEntity.ok().build();
    }

}
