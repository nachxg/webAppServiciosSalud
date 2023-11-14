
package com.prueba.pruebas.servicios;

import com.prueba.pruebas.entidades.Usuario;
import com.prueba.pruebas.repositorios.PacienteRepositorio;
import com.prueba.pruebas.repositorios.UsuarioRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private PacienteServicio pacienteServicio;
    
    @Transactional
    public void crearUsuario(){
                        
        
    }
    
    
}
