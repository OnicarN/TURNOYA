package com.danielpolanco.turnoya.domain.service;

import com.danielpolanco.turnoya.domain.model.Cita;
import com.danielpolanco.turnoya.domain.model.Negocio;
import com.danielpolanco.turnoya.domain.model.OrigenCita;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraHuecosTest {

    private final CalculadoraHuecos calculadora = new CalculadoraHuecos();

    // Manolo abre los jueves de 9:30 a 11:00, con citas de 30 minutos
    private final Negocio manolo = Negocio.crear("Pelu Manolo", "manolo@pelu.com", "hash", null,
            LocalTime.of(9, 30), LocalTime.of(11, 0), 30, Set.of(DayOfWeek.THURSDAY));

    private final LocalDate jueves = LocalDate.of(2026, 10, 29);
    private final LocalDateTime miercoles = LocalDateTime.of(2026, 10, 28, 12, 0);

    @Test
    void devuelveLosHuecosQueNoEstanOcupados() {
        // PREPARAR: Ana tiene cita el jueves a las 10:00
        Cita citaDeAna = Cita.reservar(1L, jueves, LocalTime.of(10, 0),
                "Ana", "ana@mail.com", null, OrigenCita.QR);

        // EJECUTAR: el miércoles, Luis pide los huecos del jueves
        List<LocalTime> huecos = calculadora.calcularHuecosLibres(
                manolo, jueves, List.of(citaDeAna), miercoles);

        // COMPROBAR: 9:30 libre, 10:00 ocupada por Ana, 10:30 libre
        assertEquals(List.of(LocalTime.of(9, 30), LocalTime.of(10, 30)), huecos);
    }
}