package com.danielpolanco.turnoya.infrastructure.persistence.mapper;

import com.danielpolanco.turnoya.domain.model.Negocio;
import com.danielpolanco.turnoya.infrastructure.persistence.entity.NegocioEntity;

import java.util.HashSet;

public final class NegocioMapper {

    private NegocioMapper() {
    }

    // LEER: de la base de datos (NegocioEntity) al dominio (Negocio)
    public static Negocio toDomain(NegocioEntity entity) {
        return new Negocio(
                entity.getId(),
                entity.getCodigoPublico(),
                entity.getNombre(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.getTelefono(),
                entity.getHoraApertura(),
                entity.getHoraCierre(),
                entity.getDuracionCitaMinutos(),
                new HashSet<>(entity.getDiasAbiertos()));
    }

    // GUARDAR: del dominio (Negocio) a la base de datos (NegocioEntity)
    public static NegocioEntity toEntity(Negocio negocio) {

        NegocioEntity entity = new NegocioEntity();

        entity.setId(negocio.getId());
        entity.setCodigoPublico(negocio.getCodigoPublico());
        entity.setNombre(negocio.getNombre());
        entity.setEmail(negocio.getEmail());
        entity.setPasswordHash(negocio.getPasswordHash());
        entity.setTelefono(negocio.getTelefono());
        entity.setHoraApertura(negocio.getHoraApertura());
        entity.setHoraCierre(negocio.getHoraCierre());
        entity.setDuracionCitaMinutos(negocio.getDuracionCitaMinutos());
        entity.setDiasAbiertos(new HashSet<>(negocio.getDiasAbiertos()));
        
        return entity;
    }
}