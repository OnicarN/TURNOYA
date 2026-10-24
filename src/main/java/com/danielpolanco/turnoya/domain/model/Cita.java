package com.danielpolanco.turnoya.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class Cita {

    private Long id;
    private UUID token;
    private Long negocioId;
    private LocalDate fecha;
    private LocalTime hora;
    private String nombreCliente;
    private String emailCliente;
    private String telefonoCliente;
    private OrigenCita origen;
    private EstadoCita estado;
    private LocalDateTime inicioReal;
    private LocalDateTime finReal;
    private LocalDateTime creadaEn;

    // Reconstruye una cita que YA existe (lo usará el mapper al leer de la BD)
    public Cita(Long id, UUID token, Long negocioId, LocalDate fecha, LocalTime hora,
            String nombreCliente, String emailCliente, String telefonoCliente,
            OrigenCita origen, EstadoCita estado, LocalDateTime inicioReal,
            LocalDateTime finReal, LocalDateTime creadaEn) {
        this.id = id;
        this.token = token;
        this.negocioId = negocioId;
        this.fecha = fecha;
        this.hora = hora;
        this.nombreCliente = nombreCliente;
        this.emailCliente = emailCliente;
        this.telefonoCliente = telefonoCliente;
        this.origen = origen;
        this.estado = estado;
        this.inicioReal = inicioReal;
        this.finReal = finReal;
        this.creadaEn = creadaEn;
    }

    // Crea una cita NUEVA: genera el token y nace CONFIRMADA
    public static Cita reservar(Long negocioId, LocalDate fecha, LocalTime hora,
            String nombreCliente, String emailCliente, String telefonoCliente,
            OrigenCita origen) {
        return new Cita(
                null,                    // el id lo pone la base de datos
                UUID.randomUUID(),       // token secreto del cliente
                negocioId, fecha, hora,
                nombreCliente, emailCliente, telefonoCliente,
                origen,
                EstadoCita.CONFIRMADA,   // toda cita nace confirmada
                null,                    // aún no ha empezado
                null,                    // aún no ha terminado
                LocalDateTime.now());
    }

    // ── Cambios de estado ─────────────────────────────────────

    // El dueño pulsa "Empezar"
    public void empezar(LocalDateTime ahora) {
        verificarEstado(EstadoCita.CONFIRMADA, "empezar");
        this.estado = EstadoCita.EN_CURSO;
        this.inicioReal = ahora;
    }

    // El dueño pulsa "Terminar"
    public void terminar(LocalDateTime ahora) {
        verificarEstado(EstadoCita.EN_CURSO, "terminar");
        this.estado = EstadoCita.TERMINADA;
        this.finReal = ahora;
        borrarDatosPersonales();
    }

    // El dueño pulsa "No se presentó"
    public void marcarNoPresentado() {
        verificarEstado(EstadoCita.CONFIRMADA, "marcar como no presentada");
        this.estado = EstadoCita.NO_PRESENTADO;
        borrarDatosPersonales();
    }

    // El cliente o el dueño cancelan
    public void cancelar() {
        verificarEstado(EstadoCita.CONFIRMADA, "cancelar");
        this.estado = EstadoCita.CANCELADA;
        borrarDatosPersonales();
    }

    // Solo deja hacer la acción si la cita está en el estado correcto
    private void verificarEstado(EstadoCita esperado, String accion) {
        if (this.estado != esperado) {
            throw new IllegalStateException(
                    "No se puede " + accion + " una cita en estado " + this.estado);
        }
    }

    // RGPD: cuando la cita acaba, ya no necesitamos email ni teléfono
    private void borrarDatosPersonales() {
        this.emailCliente = null;
        this.telefonoCliente = null;
    }

    // ── Getters ──────────────────────────────────────────────

    public Long getId() { return id; }
    public UUID getToken() { return token; }
    public Long getNegocioId() { return negocioId; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public String getNombreCliente() { return nombreCliente; }
    public String getEmailCliente() { return emailCliente; }
    public String getTelefonoCliente() { return telefonoCliente; }
    public OrigenCita getOrigen() { return origen; }
    public EstadoCita getEstado() { return estado; }
    public LocalDateTime getInicioReal() { return inicioReal; }
    public LocalDateTime getFinReal() { return finReal; }
    public LocalDateTime getCreadaEn() { return creadaEn; }
}