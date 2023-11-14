
package com.prueba.pruebas.servicios;

import com.prueba.pruebas.entidades.Paciente;
import com.prueba.pruebas.entidades.Usuario;
import com.prueba.pruebas.repositorios.PacienteRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteServicio {
    
    @Autowired
    private PacienteRepositorio pacienteRepositorio;
    
    @Transactional
    public void crearPaciente(){
        
        Paciente paciente = new Paciente();
        paciente.setNombre("Juan Carlos");
        paciente.setApellido("Rivera");
        paciente.setHistoriaClinica("Cancer");
        pacienteRepositorio.save(paciente);
        
    }
    
}
