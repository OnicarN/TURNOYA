package com.danielpolanco.turnoya.domain.port;

import com.danielpolanco.turnoya.domain.model.Cita;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CitaRepositoryPort {

    Cita guardar(Cita cita);

    Optional<Cita> buscarPorId(Long id);

    Optional<Cita> buscarPorToken(UUID token);

    List<Cita> buscarPorNegocioYFecha(Long negocioId, LocalDate fecha);
}