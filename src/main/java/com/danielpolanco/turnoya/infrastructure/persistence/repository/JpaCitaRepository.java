package com.danielpolanco.turnoya.infrastructure.persistence.repository;

import com.danielpolanco.turnoya.infrastructure.persistence.entity.CitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaCitaRepository extends JpaRepository<CitaEntity, Long> {

    // El cliente consulta o cancela su cita con el token
    Optional<CitaEntity> findByToken(UUID token);

    // Agenda del día, huecos y retraso: todas las citas de un negocio en un día, por hora
    List<CitaEntity> findByNegocioIdAndFechaOrderByHoraAsc(Long negocioId, LocalDate fecha);
}