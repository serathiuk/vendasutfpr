package com.serathiuk.vendas.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Venda implements Serializable {
    private String descricao;
    private BigDecimal valor;
    private BigDecimal quantidade;

    public Venda() {}

    public Venda(String descricao, BigDecimal valor, BigDecimal quantidade) {
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }
}
