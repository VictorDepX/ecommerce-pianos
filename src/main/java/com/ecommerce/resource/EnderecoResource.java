package com.ecommerce.resource;

import com.ecommerce.dto.EnderecoRequestDTO;
import com.ecommerce.service.EnderecoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

@Path("/enderecos")
public class EnderecoResource {

    @Inject EnderecoService service;

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
    public void salvarEndereco(@Valid EnderecoRequestDTO dto) {
        service.salvar(dto);
    }

    @PUT
    @Path("/{id}")
    public void atualizarEndereco(@PathParam("id") Long id, @Valid EnderecoRequestDTO dto) {
        service.atualizar(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void deletarEndereco(@PathParam("id") Long id) {
        service.deletar(id);
    }
}
