package com.egg.webApp.servicios;

import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.entidades.Profesional;
import com.egg.webApp.entidades.Turno;
import com.egg.webApp.repositorios.TurnoRepositorio;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*● La app permite el acceso de las cuentas PROFESIONAL calendario, a la creación,
eliminación y modificación de turnos disponibles, turnos reservados, posibilidad de
cancelación, lista de pacientes con acceso a su ficha personal e historial clínico, etc).
● Cada PROFESIONAL puede modificar sus horarios disponibles.*/
@Service
public class TurnosServicio {

    @Autowired
    TurnoRepositorio turnoRepositorio;

    @Transactional
    public void crearTurnoDisponible(Profesional profesional, LocalDateTime fecha) {
        Turno nuevoTurno = new Turno();
        nuevoTurno.setProfesional(profesional);
        nuevoTurno.setFechaTurno(fecha);
        nuevoTurno.setDisponible(true);
        nuevoTurno.setCancelado(false);
        nuevoTurno.setEspecialidad(profesional.getEspecialidad());
        turnoRepositorio.save(nuevoTurno);
    }

    @Transactional
    public void modificarTurno() {
        
    }
    
    public List<Turno> listaDeTurnosPorPaciente(Paciente paciente){
        
        return null;
    }

}
