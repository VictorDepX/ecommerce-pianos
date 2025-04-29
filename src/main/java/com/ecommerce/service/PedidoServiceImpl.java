package com.ecommerce.service;

import com.ecommerce.dto.PedidoRequestDTO;
import com.ecommerce.dto.PedidoResponseDTO;
import com.ecommerce.model.*;
import com.ecommerce.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject PedidoRepository pedidoRepository;
    @Inject ClienteRepository clienteRepository;
    @Inject EnderecoPedidoRepository enderecoPedidoRepository;
    @Inject PianoRepository pianoRepository;
    @Inject ItemPedidoRepository itemPedidoRepository;

    @Override
    public Response listarTodos() {
        List<PedidoResponseDTO> pedidos = pedidoRepository.listAll()
            .stream()
            .map(PedidoResponseDTO::fromEntity)
            .collect(Collectors.toList());
        return Response.ok(pedidos).build();
    }

    @Override
    public Response buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
        return Response.ok(PedidoResponseDTO.fromEntity(pedido)).build();
    }

    @Override
    @Transactional
    public void salvar(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(dto.status());

        Cliente cliente = clienteRepository.findById(dto.clienteId());
        EnderecoPedido endereco = enderecoPedidoRepository.findById(dto.enderecoPedidoId());

        pedido.setCliente(cliente);
        pedido.setEnderecoPedido(endereco);

        List<ItemPedido> itens = dto.itens().stream().map(itemDto -> {
            ItemPedido item = new ItemPedido();
            Piano piano = pianoRepository.findById(itemDto.pianoId());
            item.setPiano(piano);
            item.setQuantidade(itemDto.quantidade());
            item.setSubtotal(piano.getPreco() * itemDto.quantidade());
            item.setPedido(pedido);
            return item;
        }).collect(Collectors.toList());

        double total = itens.stream()
            .mapToDouble(ItemPedido::getSubtotal)
            .sum();  // <<<< Soma depois que os itens foram montados!

        pedido.setItens(itens);
        pedido.setTotal(total);

        pedidoRepository.persist(pedido);

        for (ItemPedido item : itens) {
            itemPedidoRepository.persist(item);
        }
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }
}
