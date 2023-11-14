
package com.prueba.pruebas.controladores;

import com.prueba.pruebas.servicios.PacienteServicio;
import com.prueba.pruebas.servicios.ProfesionalServicio;
import com.prueba.pruebas.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    PacienteServicio pS;
    
    @Autowired
    ProfesionalServicio prS;
    
    @GetMapping("/")
    public String index(){
        
        pS.crearPaciente();
        prS.crearProfesional();
        
        return "index.html";
    }
    
    
}
