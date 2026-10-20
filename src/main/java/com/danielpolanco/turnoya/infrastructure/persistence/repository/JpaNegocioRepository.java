package com.danielpolanco.turnoya.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielpolanco.turnoya.infrastructure.persistence.entity.NegocioEntity;

public interface JpaNegocioRepository extends JpaRepository<NegocioEntity,Long>{
     // Login: busca el negocio por su email
    Optional<NegocioEntity> findByEmail(String email);

    // Registro: ¿ya existe alguien con este email?
    boolean existsByEmail(String email);

    // QR: busca el negocio por el código que va en la URL
    Optional<NegocioEntity> findByCodigoPublico(UUID codigoPublico);
}
