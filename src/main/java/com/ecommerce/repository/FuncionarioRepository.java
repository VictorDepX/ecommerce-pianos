
package com.ecommerce.repository;

import com.ecommerce.model.Funcionario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepository<Funcionario> {

    public Funcionario findByCpf(String cpf) {
        return find("cpf", cpf).firstResult();
    }

    public Funcionario findByNome(String nome) {
        return find("nome", nome).firstResult();
    }

    public Funcionario findByTelefone(String telefone) {
        return find("telefone", telefone).firstResult();
    }

    public Funcionario findByDepartamento(String departamento) {
        return find("departamento", departamento).firstResult();
    }

    public Funcionario findByEmail(String email) {
        return find("usuario.email", email).firstResult();
    }
}
