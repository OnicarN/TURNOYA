package com.danielpolanco.turnoya.infrastructure.persistence.adapter;

import com.danielpolanco.turnoya.domain.exception.HuecoOcupadoException;
import com.danielpolanco.turnoya.domain.model.Cita;
import com.danielpolanco.turnoya.domain.port.CitaRepositoryPort;
import com.danielpolanco.turnoya.infrastructure.persistence.entity.CitaEntity;
import com.danielpolanco.turnoya.infrastructure.persistence.mapper.CitaMapper;
import com.danielpolanco.turnoya.infrastructure.persistence.repository.JpaCitaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CitaRepositoryAdapter implements CitaRepositoryPort {

    private final JpaCitaRepository jpaRepository;

    public CitaRepositoryAdapter(JpaCitaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Cita guardar(Cita cita) {
        try {
            CitaEntity guardada = jpaRepository.saveAndFlush(CitaMapper.toEntity(cita));
            return CitaMapper.toDomain(guardada);
        } catch (DataIntegrityViolationException e) {
            // El índice único ha rechazado la cita: alguien reservó ese hueco antes
            throw new HuecoOcupadoException();
        }
    }

    @Override
    public Optional<Cita> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(CitaMapper::toDomain);
    }

    @Override
    public Optional<Cita> buscarPorToken(UUID token) {
        return jpaRepository.findByToken(token).map(CitaMapper::toDomain);
    }

    @Override
    public List<Cita> buscarPorNegocioYFecha(Long negocioId, LocalDate fecha) {
        return jpaRepository.findByNegocioIdAndFechaOrderByHoraAsc(negocioId, fecha)
                .stream()
                .map(CitaMapper::toDomain)
                .toList();
    }
}