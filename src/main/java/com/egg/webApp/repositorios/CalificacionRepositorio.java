package com.egg.webApp.repositorios;

import com.egg.webApp.entidades.Calificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepositorio extends JpaRepository<Calificacion, Long> {

    @Query("SELECT c FROM calificaciones c WHERE c.profesional_id = :id")
    public List<Calificacion> buscarCalificacionesPorId(@Param("id") Long id);
}
