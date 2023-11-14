
package com.prueba.pruebas.repositorios;

import com.prueba.pruebas.entidades.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesionalRepositorio extends JpaRepository<Profesional,Integer>{
    
}
