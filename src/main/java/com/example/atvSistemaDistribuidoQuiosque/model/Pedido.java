package com.example.atvSistemaDistribuidoQuiosque.model;

public class Pedido {
    private String clienteId;
    private String descricao;

    public Pedido(String clienteId, String descricao) {
        this.clienteId = clienteId;
        this.descricao = descricao;
    }

    public String getClienteId() { return clienteId; }
    public String getDescricao() { return descricao; }
}
