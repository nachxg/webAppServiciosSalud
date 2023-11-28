package com.egg.webApp.repositorios;

import com.egg.webApp.entidades.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesionalRepositorio extends JpaRepository<Profesional, Long> {

    @Query("SELECT p FROM Profesional p WHERE p.dni = :dni")
    public Profesional buscarPorDni(@Param("dni") String dni);

    @Query("SELECT p FROM Profesional p WHERE p.id = :id")
    public Profesional buscarPorId(@Param("id")Long id);

    public Boolean existsByMatricula(String matricula);
    @Query("SELECT p FROM Profesional p WHERE LOWER(REPLACE(p.especialidad, ' ', '_')) LIKE LOWER(CONCAT('%', :especialidad, '%'))")
    List<Profesional> buscarPorEspecialidadContiene(@Param("especialidad") String especialidad);

    @Query("SELECT p FROM Profesional p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR LOWER(REPLACE(p.especialidad, ' ', '_')) LIKE LOWER(CONCAT('%', :termino, '%'))")
    List<Profesional> buscarPorNombreOEspecialidad(@Param("termino") String termino);



}
