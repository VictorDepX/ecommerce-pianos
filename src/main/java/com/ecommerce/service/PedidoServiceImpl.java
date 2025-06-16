package com.ecommerce.service;

import com.ecommerce.dto.DadosCartaoDTO;
import com.ecommerce.dto.PedidoRequestDTO;
import com.ecommerce.dto.PedidoResponseDTO;
import com.ecommerce.enumerator.StatusPedido;
import com.ecommerce.model.*;
import com.ecommerce.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject PedidoRepository pedidoRepository;
    @Inject ClienteRepository clienteRepository;
    @Inject EnderecoPedidoRepository enderecoPedidoRepository;
    @Inject EnderecoClienteRepository enderecoClienteRepository;
    @Inject PianoRepository pianoRepository;
    @Inject ItemPedidoRepository itemPedidoRepository;
    @Inject JsonWebToken jwt;

    @Override
    public List<PedidoResponseDTO> buscarPorNome(String username) {
        List<Pedido> pedidos = pedidoRepository.buscarPorNome(username);

        return pedidos.stream()
            .map(PedidoResponseDTO::fromEntity)
            .toList();
    }

    @Override
    public Response buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
        return Response.ok(PedidoResponseDTO.fromEntity(pedido)).build();
    }

    @Override
    @Transactional
    public PedidoResponseDTO salvar(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(dto.status());

        String email = jwt.getName();
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado com e-mail: " + email);
        }

        pedido.setCliente(cliente);

        EnderecoCliente enderecoOrigem = enderecoClienteRepository.findById(dto.enderecoClienteId());
        EnderecoPedido enderecoCopia = new EnderecoPedido();

        enderecoCopia.setRua(enderecoOrigem.getRua());
        enderecoCopia.setNumero(enderecoOrigem.getNumero());
        enderecoCopia.setBairro(enderecoOrigem.getBairro());
        enderecoCopia.setCep(enderecoOrigem.getCep());
        enderecoCopia.setCidade(enderecoOrigem.getCidade());

        enderecoPedidoRepository.persist(enderecoCopia);
        pedido.setEnderecoPedido(enderecoCopia);

        List<ItemPedido> itens = dto.itens().stream().map(itemDto -> {
            ItemPedido item = new ItemPedido();
            Piano piano = pianoRepository.findById(itemDto.pianoId());
            item.setPiano(piano);
            item.setQuantidade(itemDto.quantidade());
            piano.setEstoque(piano.getEstoque() - itemDto.quantidade());
            item.setSubtotal(piano.getPreco() * itemDto.quantidade());
            item.setPedido(pedido);
            return item;
        }).collect(Collectors.toList());

        double total = itens.stream()
            .mapToDouble(ItemPedido::getSubtotal)
            .sum();

        pedido.setItens(itens);
        pedido.setTotal(total);

        pedidoRepository.persist(pedido);

        for (ItemPedido item : itens) {
            itemPedidoRepository.persist(item);
        }


        return PedidoResponseDTO.fromEntity(pedido);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Transactional
    public Response pagarComPix(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId);
        if (pedido == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Pedido não encontrado").build();
        }

        if (pedido.getStatus() != StatusPedido.PENDENTE) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Pedido não está aguardando pagamento").build();
        }

        String chavePix = "chave-pix-" + UUID.randomUUID();
        pedido.setStatus(StatusPedido.PROCESSANDO_PAGAMENTO);
        pedidoRepository.persist(pedido);

        return Response.ok("Chave PIX gerada: " + chavePix).build();
    }

    @Transactional
    public Response pagarComCartaoDebito(Long pedidoId, DadosCartaoDTO dadosCartao) {
        return processarPagamentoCartao(pedidoId, dadosCartao, "Débito");
    }

    @Transactional
    public Response pagarComCartaoCredito(Long pedidoId, DadosCartaoDTO dadosCartao) {
        return processarPagamentoCartao(pedidoId, dadosCartao, "Crédito");
    }

    public Response processarPagamentoCartao(Long pedidoId, DadosCartaoDTO dados, String tipo) {
        Pedido pedido = pedidoRepository.findById(pedidoId);
        if (pedido == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Pedido não encontrado").build();
        }

        if (pedido.getStatus() != StatusPedido.PENDENTE) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Pedido não está aguardando pagamento").build();
        }

        pedido.setStatus(StatusPedido.PROCESSANDO_PAGAMENTO);
        pedidoRepository.persist(pedido);

        return Response.ok("Pagamento via cartão " + tipo + " processado com sucesso").build();
    }

    @Transactional
    public Response cancelarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId);
        if (pedido == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Pedido não encontrado").build();
        }

        if (pedido.getStatus() != StatusPedido.PENDENTE) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Pedido não está em processamento de pagamento").build();
        }

        pedido.setStatus(StatusPedido.CANCELADO);
        pedidoRepository.persist(pedido);

        return Response.ok("Pedido cancelado com sucesso").build();
    }
}
