package com.danielpolanco.turnoya.domain.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

/** Represents the domain model of a business that manages appointments. */
public class Negocio {

    private Long id;
    private UUID codigoPublico;
    private String nombre;
    private String email;
    private String passwordHash;
    private String telefono;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private int duracionCitaMinutos;
    private Set<DayOfWeek> diasAbiertos;

    // Reconstruye un negocio que YA existe (lo usará el mapper al leer de la BD)
    public Negocio(Long id, UUID codigoPublico, String nombre, String email, String passwordHash,
            String telefono, LocalTime horaApertura, LocalTime horaCierre,
            int duracionCitaMinutos, Set<DayOfWeek> diasAbiertos) {
        this.id = id;
        this.codigoPublico = codigoPublico;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
        this.telefono = telefono;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.duracionCitaMinutos = duracionCitaMinutos;
        this.diasAbiertos = diasAbiertos;
    }

    // Crea un negocio NUEVO (registro): genera el código del QR una sola vez
    public static Negocio crear(String nombre, String email, String passwordHash,
            String telefono, LocalTime horaApertura, LocalTime horaCierre,
            int duracionCitaMinutos, Set<DayOfWeek> diasAbiertos) {
        return new Negocio(
                null,
                UUID.randomUUID(),
                nombre, email, passwordHash, telefono,
                horaApertura, horaCierre, duracionCitaMinutos, diasAbiertos);
    }

    public Long getId() {
        return id;
    }

    public UUID getCodigoPublico() {
        return codigoPublico;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public int getDuracionCitaMinutos() {
        return duracionCitaMinutos;
    }

    public Set<DayOfWeek> getDiasAbiertos() {
        return diasAbiertos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }

    public void setDuracionCitaMinutos(int duracionCitaMinutos) {
        this.duracionCitaMinutos = duracionCitaMinutos;
    }

    public void setDiasAbiertos(Set<DayOfWeek> diasAbiertos) {
        this.diasAbiertos = diasAbiertos;
    }
}