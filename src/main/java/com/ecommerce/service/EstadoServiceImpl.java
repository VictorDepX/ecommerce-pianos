
package com.ecommerce.service;
import com.ecommerce.dto.EstadoResponseDTO;
import com.ecommerce.dto.EstadoRequestDTO;
import com.ecommerce.model.Estado;
import com.ecommerce.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService{
    @Inject
    EstadoRepository estadoRepository;

    @Override
    @Transactional
    public EstadoResponseDTO salvar(EstadoRequestDTO dto) {
        Estado estado = new Estado();

        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());

        estadoRepository.persist(estado);

        return EstadoResponseDTO.fromEntity(estado);
    }

    @Override
    @Transactional
    public void atualizar(Long id, EstadoRequestDTO dto) {
        Estado estado = estadoRepository.findById(id);

        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());
    }

    @Override
    @Transactional
    public void deletar(long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public EstadoResponseDTO buscarPorId(long id) {
        return EstadoResponseDTO.fromEntity(estadoRepository.findById(id));
    }

    @Override
    public List<EstadoResponseDTO> listarTodos() {
        return estadoRepository.findAll().stream().map(EstadoResponseDTO::fromEntity).toList();
    }

    @Override
    public EstadoResponseDTO buscarPorSigla(String sigla) {
        return EstadoResponseDTO.fromEntity(estadoRepository.buscarPorSigla(sigla));
    }
}