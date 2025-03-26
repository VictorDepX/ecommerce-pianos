package com.ecommerce.service;

import com.ecommerce.model.Piano;
import com.ecommerce.dto.PianoRequestDTO;
import com.ecommerce.dto.PianoResponseDTO;
import com.ecommerce.repository.PianoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PianoService {
    
    @Inject
    PianoRepository pianoRepository;

    public List<PianoResponseDTO> listarTodos() {
        return pianoRepository.listAll().stream()
                .map(PianoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public PianoResponseDTO buscarPorId(Long id) {
        Piano piano = pianoRepository.findById(id);
        if (piano == null) {
            throw new WebApplicationException("Piano não encontrado", Response.Status.NOT_FOUND);
        }
        return PianoResponseDTO.fromEntity(piano);
    }

    @Transactional
    public PianoResponseDTO criarPiano(PianoRequestDTO dto) {
        Piano piano = dto.toEntity();
        pianoRepository.persist(piano);
        return PianoResponseDTO.fromEntity(piano);
    }

    @Transactional
    public PianoResponseDTO atualizarPiano(Long id, PianoRequestDTO dto) {
        Piano piano = pianoRepository.findById(id);
        if (piano == null) {
            throw new WebApplicationException("Piano não encontrado", Response.Status.NOT_FOUND);
        }
        piano.modelo = dto.modelo();
        piano.fabricante = dto.fabricante();
        piano.numeroDeTeclas = dto.numeroDeTeclas();
        piano.possuiPedais = dto.possuiPedais();
        piano.material = dto.material();
        piano.tipo = dto.tipo();


        return PianoResponseDTO.fromEntity(piano);
    }

    @Transactional
    public void deletarPiano(Long id) {
        Piano piano = pianoRepository.findById(id);
        if (piano != null) {
            pianoRepository.delete(piano);
        } else {
            throw new WebApplicationException("Piano não encontrado", Response.Status.NOT_FOUND);
        }
    }

    public List<PianoResponseDTO> buscarPorFabricante(String fabricante) {
        return pianoRepository.buscarPorFabricante(fabricante).stream()
                .map(PianoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
