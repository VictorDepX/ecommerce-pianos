package com.ecommerce.service;

import com.ecommerce.dto.PianoResponseDTO;
import com.ecommerce.dto.PianoDTO;

import java.util.List;

public interface PianoService {

    PianoResponseDTO create(PianoDTO piano);
    void update(long id, PianoDTO piano);
    void delete(long id);
    PianoResponseDTO buscarPorId(long id);
    PianoResponseDTO buscarPorFabricante(String fabricante);
    List<PianoResponseDTO> listarTodos();

}
