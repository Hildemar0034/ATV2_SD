package com.example.atvSistemaDistribuidoQuiosque.service;

import com.example.atvSistemaDistribuidoQuiosque.model.Cliente;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PedidoService {
    private static Map<String, Cliente> clientes = new HashMap<>();
    private static List<String> historico = new ArrayList<>();

    public static void registrarCliente(String id, String nome) {
        clientes.put(id, new Cliente(id, nome));
        historico.add("Cliente cadastrado: " + nome + " (ID: " + id + ")");
    }

    public static Cliente getCliente(String id) {
        return clientes.get(id);
    }

    public static void registrarPedido(String id, String descricao) {
        Cliente cliente = clientes.get(id);
        if (cliente != null) {
            historico.add("Pedido de " + cliente.getNome() + ": " + descricao);
        }
    }

    public static void finalizarPapo() throws IOException {
        FileWriter writer = new FileWriter("historico_papo.txt");
        for (String linha : historico) {
            writer.write(linha + "\n");
        }
        writer.close();
        historico.clear();
    }

    public static String gerarIdAleatorio() {
        String id;
        do {
            id = String.format("%03d", new Random().nextInt(1000)); // ex: "042"
        } while (clientes.containsKey(id));
        return id;
    }
}
