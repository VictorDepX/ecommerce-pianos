package com.ecommerce.model;
import jakarta.persistence.*;

@Entity
public class ItemPedido extends DefaultEntity {
    
    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    private Double subtotal;

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    @ManyToOne
    @JoinColumn(name = "piano_id")
    private Piano piano;

    public Piano getPiano() {
        return piano;
    }

    public void setPiano(Piano piano) {
        this.piano = piano;
    }

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
