package com.ecommerce.resource;
import com.ecommerce.dto.FuncionarioRequestDTO;
import com.ecommerce.service.FuncionarioService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

@Path("/funcionarios")
public class FuncionarioResource {
    @Inject FuncionarioService service;

    @GET
    public Object listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Object buscarFuncionarios(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public void criarFuncionario(@Valid FuncionarioRequestDTO dto) {
        service.salvar(dto);
    }

    @PUT
    @Path("/{id}")
    public void atualizarFuncionario(@PathParam("id") Long id, @Valid FuncionarioRequestDTO dto) {
        service.atualizar(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void deletarFuncionario(@PathParam("id") Long id) {
        service.deletar(id);
    }
}