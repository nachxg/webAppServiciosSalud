package com.egg.webApp.repositorios;

import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.entidades.Profesional;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionalRepositorio extends JpaRepository<Profesional, Long> {

    @Query("SELECT p FROM Profesional p WHERE p.dni = :dni")
    public Profesional buscarPorDni(@Param("dni") String dni);

    @Query("SELECT p FROM Profesional p WHERE p.id = :id")
    public Profesional buscarPorId(@Param("id")Long id);

    public Boolean existsByMatricula(String matricula);
   //@EntityGraph(attributePaths = {"turnosDisponibles", "calificaciones"})
    //@Query("SELECT DISTINCT p FROM Profesional p LEFT JOIN FETCH p.turnosDisponibles LEFT JOIN FETCH p.calificaciones WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR LOWER(REPLACE(p.especialidad, ' ', '_')) LIKE LOWER(CONCAT('%', :termino, '%'))")
   //List<Profesional> buscarPorNombreOEspecialidad(@Param("termino") String termino);
   @Query("SELECT DISTINCT p FROM Profesional p WHERE LOWER(REPLACE(p.especialidad, ' ', '_')) LIKE LOWER(CONCAT('%', :especialidad, '%'))")
    List<Profesional> buscarPorNombreOEspecialidad(@Param("especialidad") String especialidad);
}
