package com.danielpolanco.turnoya.infrastructure.persistence.mapper;

import com.danielpolanco.turnoya.domain.model.Cita;
import com.danielpolanco.turnoya.infrastructure.persistence.entity.CitaEntity;

public final class CitaMapper {

    private CitaMapper() {
    }

    public static Cita toDomain(CitaEntity entity) {
        return new Cita(
                entity.getId(),
                entity.getToken(),
                entity.getNegocioId(),
                entity.getFecha(),
                entity.getHora(),
                entity.getNombreCliente(),
                entity.getEmailCliente(),
                entity.getTelefonoCliente(),
                entity.getOrigen(),
                entity.getEstado(),
                entity.getInicioReal(),
                entity.getFinReal(),
                entity.getCreadaEn());
    }

    public static CitaEntity toEntity(Cita cita) {
        CitaEntity entity = new CitaEntity();
        entity.setId(cita.getId());
        entity.setToken(cita.getToken());
        entity.setNegocioId(cita.getNegocioId());
        entity.setFecha(cita.getFecha());
        entity.setHora(cita.getHora());
        entity.setNombreCliente(cita.getNombreCliente());
        entity.setEmailCliente(cita.getEmailCliente());
        entity.setTelefonoCliente(cita.getTelefonoCliente());
        entity.setOrigen(cita.getOrigen());
        entity.setEstado(cita.getEstado());
        entity.setInicioReal(cita.getInicioReal());
        entity.setFinReal(cita.getFinReal());
        entity.setCreadaEn(cita.getCreadaEn());
        return entity;
    }
}