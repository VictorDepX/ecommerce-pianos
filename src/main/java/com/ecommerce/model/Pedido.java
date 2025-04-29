package com.ecommerce.model;
import com.ecommerce.enumerator.StatusPedido;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pedido extends DefaultEntity {
    
    private LocalDateTime dataCriacao;
    
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    private Double total;

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @ManyToOne
    @JoinColumn(name = "endereco_pedido_id")
    private EnderecoPedido enderecoPedido;

    public EnderecoPedido getEnderecoPedido() {
        return enderecoPedido;
    }

    public void setEnderecoPedido(EnderecoPedido enderecoPedido) {
        this.enderecoPedido = enderecoPedido;
    }

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "pagamento_id")
    // private Pagamento pagamento;

    // public Pagamento getPagamento() {
    //     return pagamento;
    // }

    // public void setPagamento(Pagamento pagamento) {
    //     this.pagamento = pagamento;
    // }

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens;

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}
