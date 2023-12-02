package com.egg.webApp.repositorios;

import com.egg.webApp.entidades.Calificacion;
import com.egg.webApp.entidades.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

    List<Calificacion> findByProfesional(Profesional profesional);
}
