package com.egg.webApp.servicios;

import com.egg.webApp.entidades.Calificacion;
import com.egg.webApp.entidades.Profesional;
import com.egg.webApp.repositorios.CalificacionRepositorio;
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
    
    @Transactional
    public void crearCalificacion(String comentario, Integer calificacion) {
        Calificacion nuevaCalificacion = new Calificacion();
        nuevaCalificacion.setComentario(comentario);
        nuevaCalificacion.setPuntuacion(calificacion);
        calificacionRepositorio.save(nuevaCalificacion);
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
    
    public List<Calificacion> calificacionesDeUnProfecional(Profesional profecional) {
        List<Calificacion> calificaciones = new ArrayList();
        calificaciones = calificacionRepositorio.buscarCalificacionesPorId(profecional.getId());
        return calificaciones;
    }

    public Calificacion getOne(Long id) {
        return calificacionRepositorio.getOne(id);
    }
}
