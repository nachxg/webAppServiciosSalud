package com.egg.webApp.servicios;
import com.egg.webApp.repositorios.CalificacionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalificacionServicio {
    private final CalificacionRepository calificacionRepository;
    public CalificacionServicio(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }
    public List<Double> obtenerPromedioPuntuacionPorProfesional(List<Long> idsProfesionales) {
        List<Double> promedios = new ArrayList<>();
        for (Long idProfesional : idsProfesionales) {
            Double promedio = calificacionRepository.obtenerPromedioPuntuacionPorProfesional(idProfesional);
            promedios.add(promedio != null ? promedio : 0.0);
        }
        return promedios;
    }
}