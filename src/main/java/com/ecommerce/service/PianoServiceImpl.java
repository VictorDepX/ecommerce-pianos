package com.ecommerce.service;

import com.ecommerce.model.Piano;
import com.ecommerce.dto.PianoDTO;
import com.ecommerce.dto.PianoResponseDTO;
import com.ecommerce.repository.PianoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class PianoServiceImpl implements PianoService {
    
    @Inject
    PianoRepository pianoRepository;

    @Override
    @Transactional
    public PianoResponseDTO create(PianoDTO dto) {
        Piano novoPiano = new Piano();
        novoPiano.setModelo(dto.modelo());
        novoPiano.setFabricante(dto.fabricante());
        novoPiano.setNumerodeteclas(dto.numeroDeTeclas());
        novoPiano.setPossuipedais(dto.possuiPedais());
        novoPiano.setMaterial(dto.material());
        novoPiano.setTipo(dto.tipo());

        pianoRepository.persist(novoPiano);
        return PianoResponseDTO.valueOf(novoPiano);
    }

    @Override
    @Transactional
    public void update(long id, PianoDTO piano) {
        Piano edicaoPiano = pianoRepository.findById(id);
        if (piano == null) {
            throw new WebApplicationException("Piano não encontrado", Response.Status.NOT_FOUND);
        }
        edicaoPiano.setModelo(piano.modelo());
        edicaoPiano.setFabricante(piano.fabricante());
        edicaoPiano.setNumerodeteclas(piano.numeroDeTeclas());
        edicaoPiano.setPossuipedais(piano.possuiPedais());
        edicaoPiano.setMaterial(piano.material());
        edicaoPiano.setTipo(piano.tipo());    }

    @Override
    @Transactional
    public void delete(long id) {
        pianoRepository.deleteById(id);

    }

    @Override
    public List<PianoResponseDTO> listarTodos() {
        return pianoRepository.findAll().stream()
                .map(p -> PianoResponseDTO.valueOf(p)).toList();
    }

    @Override
    public PianoResponseDTO buscarPorId(long id) {
        return PianoResponseDTO.valueOf(pianoRepository.findById(id));
    }



    @Override
    public List<PianoResponseDTO> buscarPorFabricante(String fabricante) {
        return pianoRepository.findAll().stream().map(p -> PianoResponseDTO.valueOf(p)).toList();
    }
}