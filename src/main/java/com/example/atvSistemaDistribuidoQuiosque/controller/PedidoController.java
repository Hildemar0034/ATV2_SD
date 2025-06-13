package com.example.atvSistemaDistribuidoQuiosque.controller;

import com.example.atvSistemaDistribuidoQuiosque.model.Cliente;
import com.example.atvSistemaDistribuidoQuiosque.service.PedidoService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class PedidoController {

    @MessageMapping("/mensagem")
    @SendTo("/topico/respostas")
    public String processarMensagem(String mensagem) throws Exception {
        String[] partes = mensagem.split(":", 3);

        if (mensagem.equalsIgnoreCase("finalizar papo")) {
            PedidoService.finalizarPapo();
            return "Histórico salvo com sucesso!";
        }

        if (partes.length < 2) return "Formato inválido.";

        String tipo = partes[0].trim().toLowerCase();
        String id = partes[1].trim();
        String conteudo = partes.length == 3 ? partes[2].trim() : "";

        switch (tipo) {

             case "novo_cliente":
        String novoId = PedidoService.gerarIdAleatorio();
        PedidoService.registrarCliente(novoId, id);
        return "Cliente cadastrado: " + id + " (ID: " + novoId + ")";
        
    case "cliente":
        PedidoService.registrarCliente(id, conteudo);
        return "Cliente cadastrado: " + conteudo + " (ID: " + id + ")";
    case "pedido":
        PedidoService.registrarPedido(id, conteudo);
        Cliente cliente = PedidoService.getCliente(id);
        if (cliente != null) {
            return "Pedido de " + cliente.getNome() + ": " + conteudo;
        } else {
            return "Cliente não encontrado.";
        }
    default:
        return "Comando desconhecido.";
}

    }
}
