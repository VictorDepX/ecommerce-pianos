
package com.ecommerce.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente extends Pessoa {

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @OneToMany
    private List<EnderecoCliente> enderecos;

    public List<EnderecoCliente> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoCliente> enderecos) {
        this.enderecos = enderecos;
    }


}
