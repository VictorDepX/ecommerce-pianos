package com.ecommerce.resource;
import com.ecommerce.dto.ClienteRequestDTO;
import com.ecommerce.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

@Path("/clientes")
public class ClienteResource {
    @Inject ClienteService service;

    @GET
    public Object listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Object buscarClientes(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public void criarCliente(@Valid ClienteRequestDTO dto) {
        service.salvar(dto);
    }

    @PUT
    @Path("/{id}")
    public void atualizarCliente(@PathParam("id") Long id, @Valid ClienteRequestDTO dto) {
        service.atualizar(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void deletarCliente(@PathParam("id") Long id) {
        service.deletar(id);
    }
}