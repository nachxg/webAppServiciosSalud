package com.egg.webApp.servicios;

import com.egg.webApp.entidades.Calificacion;
import com.egg.webApp.entidades.Profesional;
import com.egg.webApp.repositorios.CalificacionRepositorio;
import com.egg.webApp.repositorios.ProfesionalRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalificacionServicio {

    @Autowired
    private CalificacionRepositorio calificacionRepositorio;
    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;

    @Transactional
    public void crearCalificacion(String comentario, Integer calificacion, Long idProfesional) {
        Calificacion nuevaCalificacion = new Calificacion();
        nuevaCalificacion.setComentario(comentario);
        nuevaCalificacion.setPuntuacion(calificacion);
        Optional<Profesional> respuesta = profesionalRepositorio.findById(idProfesional);
        if (respuesta.isPresent()) {
            Profesional profesional = respuesta.get();
            profesional.getCalificaciones().add(nuevaCalificacion);
            profesionalRepositorio.save(profesional);
            calificacionRepositorio.save(nuevaCalificacion);
        }
    }

    @Transactional
    public void modificarCalificacion(Long id, String comentarioNuevo, Integer nuevaCalificacion) {
        Optional<Calificacion> respuesta = calificacionRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Calificacion calificacionModificada = respuesta.get();
            calificacionModificada.setPuntuacion(nuevaCalificacion);
            calificacionModificada.setComentario(comentarioNuevo);
            calificacionRepositorio.save(calificacionModificada);
        }
    }

    @Transactional
    public void eliminarCalificacion(Long id) {
        Optional<Calificacion> respuesta = calificacionRepositorio.findById(id);
        if (respuesta.isPresent()) {
            calificacionRepositorio.deleteById(id);
        }
    }

    public List<Calificacion> calificacionesDeUnProfecional(Long idProfisional) {
        List<Calificacion> calificaciones = new ArrayList<>();
        calificaciones = calificacionRepositorio.buscarCalificacionesPorIdDeProfesionales(idProfisional);
        return calificaciones;
    }

    public Calificacion getOne(Long id) {
        return calificacionRepositorio.getOne(id);
    }
}
