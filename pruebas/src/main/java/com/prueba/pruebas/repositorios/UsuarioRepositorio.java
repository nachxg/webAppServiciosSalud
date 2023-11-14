
package com.prueba.pruebas.repositorios;

import com.prueba.pruebas.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer>{
    
}
