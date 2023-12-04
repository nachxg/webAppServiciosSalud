package com.egg.webApp.repositorios;

import com.egg.webApp.entidades.Paciente;
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
    @Query("SELECT p FROM Profesional p WHERE p.altaSistema = true")
    public List<Profesional> listarProfesionalesDeAltaEnSistema();
    @Query("SELECT p FROM Profesional p WHERE p.altaSistema = false")
    public List<Profesional> listarProfesionalesPendientesAlta();
    public Boolean existsByMatricula(String matricula);
}
