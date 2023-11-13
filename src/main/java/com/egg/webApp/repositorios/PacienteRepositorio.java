package com.egg.webApp.repositorios;

import com.egg.webApp.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {


}
