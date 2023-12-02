package com.egg.webApp.servicios;

import com.egg.webApp.entidades.Calificacion;
import com.egg.webApp.entidades.Profesional;
import com.egg.webApp.repositorios.CalificacionRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalificacionServicio {
    private final CalificacionRepository calificacionRepository;

    public CalificacionServicio(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }

    public double calcularPromedioPuntuacionPorProfesional(Profesional profesional) {
        List<Calificacion> calificaciones = calificacionRepository.findByProfesional(profesional);
        if (calificaciones.isEmpty()) {
            return 0;
        }
        int sumaPuntuaciones = calificaciones.stream().mapToInt(Calificacion::getPuntuacion).sum();
        return (double) sumaPuntuaciones / calificaciones.size();
    }

    public Map<Long, Double> calcularPromedioPuntuacionPorProfesionales(List<Profesional> profesionales) {
        Map<Long, Double> promedios = new HashMap<>();
        for (Profesional profesional : profesionales) {
            double promedioPuntuacion = calcularPromedioPuntuacionPorProfesional(profesional);
            promedios.put(profesional.getId(), promedioPuntuacion);
        }
        return promedios;
    }
}