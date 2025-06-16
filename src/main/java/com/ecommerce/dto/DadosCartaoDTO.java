package com.ecommerce.dto;

public record DadosCartaoDTO(
    String numeroCartao,
    String nomeTitular,
    String validade,
    String codigoSeguranca
) {}
