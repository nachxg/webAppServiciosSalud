
package com.prueba.pruebas.servicios;

import com.prueba.pruebas.entidades.Profesional;
import com.prueba.pruebas.repositorios.ProfesionalRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesionalServicio {
    
    @Autowired
    ProfesionalRepositorio pR;
    
    public void crearProfesional(){
        
        Profesional profesional = new Profesional();
        profesional.setApellido("Garcia");
        profesional.setNombre("Javier");
        profesional.setMatricula("ABC123");
        pR.save(profesional);
        
    }
    
}
