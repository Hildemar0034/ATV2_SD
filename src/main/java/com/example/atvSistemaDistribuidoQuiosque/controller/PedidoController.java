package com.example.atvSistemaDistribuidoQuiosque.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PedidoController {

    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    private final SimpMessagingTemplate template;

    public PedidoController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/pedido")
    public void receberPedido(String pedido) {
        logger.info("📦 Pedido recebido: {}", pedido);
        template.convertAndSend("/topico/pedidos", pedido);
        logger.info("➡️ Pedido enviado para /topico/pedidos");
    }
}
