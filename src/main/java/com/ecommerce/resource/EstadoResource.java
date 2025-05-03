package com.ecommerce.resource;

import com.ecommerce.dto.EstadoRequestDTO;
import com.ecommerce.service.EstadoServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {

    @Inject
    EstadoServiceImpl estadoService;

    @POST
    public Response incluir(EstadoRequestDTO dto){
        return Response.status(Response.Status.CREATED).entity(estadoService.salvar(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") long id, EstadoRequestDTO dto){
         estadoService.atualizar(id, dto);

         return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") long id){
        estadoService.deletar(id);
        return Response.noContent().build();
    }

    @GET
    public Response buscarTodos(){
        return Response.ok().entity(estadoService.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") long id){
        return Response.ok().entity(estadoService.buscarPorId(id)).build();
    }

    @GET
    @Path("/buscar/sigla")
    public Response buscarPorSigla(@QueryParam("value") String sigla) {
        return Response.ok().entity(estadoService.buscarPorSigla(sigla)).build();
    }
}