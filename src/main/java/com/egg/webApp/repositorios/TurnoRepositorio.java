package com.egg.webApp.repositorios;

import com.egg.webApp.entidades.Turno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepositorio extends JpaRepository<Turno, Long> {

    @Query("SELECT t FROM turnos t WHERE t.paciente_id = :id")
    public List<Turno> buscarTurnosPorIdPaciente(@Param("id") Long id);
    
    @Query("SELECT t FROM turnos t WHERE atendido = 0 AND t.profesional_id = :id")
    public List<Turno> buscarTurnosDisponiblesDeProfecional(@Param("id") Long id);
}
