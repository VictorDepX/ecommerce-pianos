package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.DadosCartaoDTO;
import com.ecommerce.dto.PedidoRequestDTO;
import com.ecommerce.dto.PedidoResponseDTO;
import jakarta.ws.rs.core.Response;

public interface PedidoService {

    List<PedidoResponseDTO> buscarPorNome(String username);
    Response buscarPorId(Long id);
    PedidoResponseDTO salvar(PedidoRequestDTO dto);
    void deletar(Long id);
    Response pagarComPix(Long pedidoId);
    Response pagarComCartaoDebito(Long pedidoId, DadosCartaoDTO dadosCartao);
    Response pagarComCartaoCredito(Long pedidoId, DadosCartaoDTO dadosCartao);
    Response cancelarPedido(Long pedidoId);
}
