package com.danielpolanco.turnoya.infrastructure.persistence.adapter;

import com.danielpolanco.turnoya.domain.model.Negocio;
import com.danielpolanco.turnoya.domain.port.NegocioRepositoryPort;
import com.danielpolanco.turnoya.infrastructure.persistence.entity.NegocioEntity;
import com.danielpolanco.turnoya.infrastructure.persistence.mapper.NegocioMapper;
import com.danielpolanco.turnoya.infrastructure.persistence.repository.*;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component                                   // Spring crea este objeto y lo gestiona
public class NegocioRepositoryAdapter implements NegocioRepositoryPort {   // "yo encajo en ese enchufe"

    private final JpaNegocioRepository jpaRepository;

    // Spring nos pasa el JpaNegocioRepository automáticamente (inyección por constructor)
    public NegocioRepositoryAdapter(JpaNegocioRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Negocio guardar(Negocio negocio) {
        NegocioEntity entity = NegocioMapper.toEntity(negocio);   // Negocio → Entity
        NegocioEntity guardada = jpaRepository.save(entity);      // guarda en PostgreSQL
        return NegocioMapper.toDomain(guardada);                  // Entity → Negocio (ya con id)
    }

    @Override
    public Optional<Negocio> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(NegocioMapper::toDomain);
    }

    @Override
    public Optional<Negocio> buscarPorEmail(String email) {
        return jpaRepository.findByEmail(email).map(NegocioMapper::toDomain);
    }

    @Override
    public boolean existePorEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<Negocio> buscarPorCodigoPublico(UUID codigoPublico) {
        return jpaRepository.findByCodigoPublico(codigoPublico).map(NegocioMapper::toDomain);
    }
}