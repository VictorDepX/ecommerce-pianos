
package com.ecommerce.repository;

import com.ecommerce.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public Cliente findByCpf(String cpf) {
        return find("cpf", cpf).firstResult();
    }

    public Cliente findByNome(String nome) {
        return find("nome", nome).firstResult();
    }

    public Cliente findByTelefone(String telefone) {
        return find("telefone", telefone).firstResult();
    }

    public Cliente findByEmail(String email) {
        return find("usuario.email", email).firstResult();
    }
}
