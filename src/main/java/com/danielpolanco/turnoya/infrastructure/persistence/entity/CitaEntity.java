package com.danielpolanco.turnoya.infrastructure.persistence.entity;

import com.danielpolanco.turnoya.domain.model.EstadoCita;
import com.danielpolanco.turnoya.domain.model.OrigenCita;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "citas")
@Getter
@Setter
@NoArgsConstructor
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID token;

    @Column(nullable = false)
    private Long negocioId;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;

    @Column(nullable = false)
    private String nombreCliente;

    private String emailCliente;

    @Column(length = 20)
    private String telefonoCliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private OrigenCita origen;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoCita estado;

    private LocalDateTime inicioReal;

    private LocalDateTime finReal;

    @Column(nullable = false)
    private LocalDateTime creadaEn;
}