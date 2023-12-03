package com.egg.webApp.repositorios;

import com.egg.webApp.entidades.Turno;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepositorio extends JpaRepository<Turno, Long> {

    @Query("SELECT t FROM Turno t WHERE t.paciente.id = :id")
    public List<Turno> buscarTurnosPorIdPaciente(@Param("id") Long id);

    @Query("SELECT t FROM Turno t WHERE t.paciente.id = :id AND t.atendido = true")
    public List<Turno> buscarTurnosPorIdPacienteAtendido(@Param("id") Long id);

    @Query("SELECT t FROM Turno t WHERE t.profesional.id = :id AND t.atendido = false")
    public List<Turno> buscarTurnosDisponiblesDeProfesional(@Param("id") Long id);

    @Query("SELECT t FROM Turno t WHERE t.profesional.id = :id")
    public List<Turno> todosLosTurnosDeProfecional(@Param("id") Long id);

    @Query("SELECT t FROM Turno t WHERE t.especialidad = :especialidad AND t.atendido = false AND t.cancelado = false")
    public List<Turno> todosLosTurnosPorEspecialidad(@Param("especialidad") String especialidad);
    
    @Query("SELECT t FROM Turno t WHERE t.profesional.id = :id AND t.atendido = false AND t.fechaTurno <= :fechaAhora")
    public List<Turno> buscarTurnosDisponiblesParaPaciente(@Param("id") Long id, @Param ("fechaAhora") LocalDateTime fechaAhora);
    
}
