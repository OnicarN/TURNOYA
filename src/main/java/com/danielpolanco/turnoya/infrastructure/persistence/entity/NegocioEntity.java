package com.danielpolanco.turnoya.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/** JPA entity that persists business data in the database. */
@Entity
@Table(name = "negocios")
@Getter
@Setter
@NoArgsConstructor
public class NegocioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID codigoPublico;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    private String telefono;

    @Column(nullable = false)
    private LocalTime horaApertura;

    @Column(nullable = false)
    private LocalTime horaCierre;

    @Column(nullable = false)
    private int duracionCitaMinutos;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "negocio_dias_abiertos",
            joinColumns = @JoinColumn(name = "negocio_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "dia")
    private Set<DayOfWeek> diasAbiertos = new HashSet<>();
}