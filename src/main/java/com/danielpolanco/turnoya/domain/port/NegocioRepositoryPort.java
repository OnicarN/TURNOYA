package com.danielpolanco.turnoya.domain.port;

import com.danielpolanco.turnoya.domain.model.Negocio;

import java.util.Optional;
import java.util.UUID;

public interface NegocioRepositoryPort {

    Negocio guardar(Negocio negocio);

    Optional<Negocio> buscarPorId(Long id);

    Optional<Negocio> buscarPorEmail(String email);

    boolean existePorEmail(String email);

    Optional<Negocio> buscarPorCodigoPublico(UUID codigoPublico);
}