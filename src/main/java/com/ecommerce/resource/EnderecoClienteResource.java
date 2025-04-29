package com.ecommerce.resource;

import com.ecommerce.dto.EnderecoClienteRequestDTO;
import com.ecommerce.service.EnderecoClienteService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

@Path("/enderecos-cliente")
public class EnderecoClienteResource {

    @Inject EnderecoClienteService service;

    @GET
    public Object listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Object buscarEndereco(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public void salvarEndereco(@Valid EnderecoClienteRequestDTO dto) {
        service.salvar(dto);
    }

    @PUT
    @Path("/{id}")
    public void atualizarEndereco(@PathParam("id") Long id, @Valid EnderecoClienteRequestDTO dto) {
        service.atualizar(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void deletarEndereco(@PathParam("id") Long id) {
        service.deletar(id);
    }
}
