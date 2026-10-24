package com.danielpolanco.turnoya.domain.service;

import com.danielpolanco.turnoya.domain.model.Cita;
import com.danielpolanco.turnoya.domain.model.EstadoCita;
import com.danielpolanco.turnoya.domain.model.Negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalculadoraHuecos {

    public List<LocalTime> calcularHuecosLibres(Negocio negocio, LocalDate fecha,
                                                List<Cita> citasDelDia, LocalDateTime ahora) {

        List<LocalTime> resultado = new ArrayList<>();
        //JUEVES 
        if (!negocio.getDiasAbiertos().contains(fecha.getDayOfWeek())){
            resultado = new ArrayList<>();
        }else{
            //Es viernes ? 
            if (fecha.isBefore(ahora.toLocalDate())){
                resultado = new ArrayList<>();
            }else{
                Set<LocalTime> horasOcupadas = new HashSet<>();   

                for (Cita citas: citasDelDia){
                    if (citas.getEstado() != EstadoCita.CANCELADA){
                        horasOcupadas.add(citas.getHora());
                    }
                }

                //9:00
                LocalTime horaApertura = negocio.getHoraApertura();
                LocalTime horaCierre = negocio.getHoraCierre();
                while (!horaApertura.isAfter(horaCierre)){
                    if (!horasOcupadas.contains(horaApertura)){
                        resultado.add(horaApertura);
                    }
                    horaApertura = horaApertura.plusMinutes(negocio.getDuracionCitaMinutos());
                }
                
            }
        }

        return resultado;
    }
}