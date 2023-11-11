package com.egg.webApp.repositorios;

import com.egg.webApp.entidades.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepositorio extends JpaRepository<Calificacion, Long> {
}
