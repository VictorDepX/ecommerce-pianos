package com.ecommerce.service;

import com.ecommerce.dto.MunicipioRequestDTO;
import com.ecommerce.dto.MunicipioResponseDTO;
import com.ecommerce.model.Estado;
import com.ecommerce.model.Municipio;
import com.ecommerce.repository.EstadoRepository;
import com.ecommerce.repository.MunicipioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MunicipioServiceImpl implements MunicipioService {

    @Inject
    MunicipioRepository repository;

    @Inject
    EstadoRepository estadoRepository;

    @Override
    public List<MunicipioResponseDTO> listarTodos() {
        List<MunicipioResponseDTO> lista = repository.listAll()
            .stream().map(MunicipioResponseDTO::fromEntity).collect(Collectors.toList());
        return lista;
    }

    @Override
    public MunicipioResponseDTO buscarPorId(Long id) {
        return MunicipioResponseDTO.fromEntity(repository.findById(id));

    }

    @Override
    @Transactional
    public MunicipioResponseDTO salvar(MunicipioRequestDTO dto) {
        Estado estado = estadoRepository.findById(dto.estadoId());
        Municipio m = new Municipio();
        m.setNome(dto.nome());
        m.setEstado(estado);
        repository.persist(m);

        return MunicipioResponseDTO.fromEntity(m);
    }

    @Override
    @Transactional
    public void atualizar(Long id, MunicipioRequestDTO dto) {
        Municipio m = repository.findById(id);
        m.setNome(dto.nome());
        m.setEstado(estadoRepository.findById(dto.estadoId()));
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
