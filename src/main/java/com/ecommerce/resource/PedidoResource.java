package com.ecommerce.resource;

import com.ecommerce.dto.DadosCartaoDTO;
import com.ecommerce.dto.PedidoRequestDTO;
import com.ecommerce.service.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService service;

    private static final Logger LOG = Logger.getLogger(PedidoResource.class);

    @GET
    @Path("/cliente")
    @RolesAllowed("CLIENTE")
    public Response buscarPorNome(@QueryParam("nome") String nome) {
        LOG.info("Buscando pedidos pelo nome: " + nome);
        return Response.ok(service.buscarPorNome(nome)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("CLIENTE")
    public Response buscarPedido(@PathParam("id") Long id) {
        LOG.info("Buscando pedido pelo ID: " + id);
        return service.buscarPorId(id);
    }

    @POST
    @Transactional
    @RolesAllowed("CLIENTE")
    public Response salvarPedido(@Valid PedidoRequestDTO dto) {
        LOG.info("Salvando novo pedido");
        return Response.status(Response.Status.CREATED)
                       .entity(service.salvar(dto)).build();
    }

    @POST
    @Path("/{id}/pagamento/pix")
    @RolesAllowed("CLIENTE")
    public Response pagarComPix(@PathParam("id") Long pedidoId) {
        LOG.info("Processando pagamento via PIX para pedido ID: " + pedidoId);
        return service.pagarComPix(pedidoId);
    }

    @POST
    @Path("/{id}/pagamento/debito")
    @RolesAllowed("CLIENTE")
    public Response pagarComCartaoDebito(@PathParam("id") Long pedidoId, @Valid DadosCartaoDTO dadosCartao) {
        LOG.info("Processando pagamento com cartão de débito para pedido ID: " + pedidoId);
        return service.pagarComCartaoDebito(pedidoId, dadosCartao);
    }

    @POST
    @Path("/{id}/pagamento/credito")
    @RolesAllowed("CLIENTE")
    public Response pagarComCartaoCredito(@PathParam("id") Long pedidoId, @Valid DadosCartaoDTO dadosCartao) {
        LOG.info("Processando pagamento com cartão de crédito para pedido ID: " + pedidoId);
        return service.pagarComCartaoCredito(pedidoId, dadosCartao);
    }

    @PUT
    @Path("/{id}/cancelar")
    @RolesAllowed("CLIENTE")
    public Response cancelarPedido(@PathParam("id") Long pedidoId) {
        LOG.info("Cancelando pedido ID: " + pedidoId);
        return service.cancelarPedido(pedidoId);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed("CLIENTE")
    public Response deletarPedido(@PathParam("id") Long id) {
        LOG.info("Deletando pedido ID: " + id);
        service.deletar(id);
        return Response.noContent().build();
    }
}
