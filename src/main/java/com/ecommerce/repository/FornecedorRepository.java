package com.ecommerce.repository;

import com.ecommerce.model.Fornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor> {

    public Fornecedor buscarPorNome(String nome) {
        return find("nome", nome).firstResult();
    }

    public Fornecedor buscarPorCnpj(String cnpj) {
        return find("cnpj", cnpj).firstResult();
    }

    public Fornecedor buscarPorTelefone(String telefone) {
        return find("telefone", telefone).firstResult();
    }
}
