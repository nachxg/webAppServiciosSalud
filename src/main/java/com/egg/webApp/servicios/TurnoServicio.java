package com.egg.webApp.servicios;

import com.egg.webApp.entidades.Profesional;
import com.egg.webApp.entidades.Turno;
import com.egg.webApp.repositorios.ProfesionalRepositorio;
import com.egg.webApp.repositorios.TurnoRepositorio;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*● La app permite el acceso de las cuentas PROFESIONAL calendario, a la creación,
eliminación y modificación de turnos disponibles, turnos reservados, posibilidad de
cancelación, lista de pacientes con acceso a su ficha personal e historial clínico, etc).
● Cada PROFESIONAL puede modificar sus horarios disponibles.*/
@Service
public class TurnoServicio {

    @Autowired
    private TurnoRepositorio turnoRepositorio;
    @Autowired
    private ProfesionalServicio profesionalServicio;
    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Transactional
    public void crearTurnoDisponible(Long idProfesional, String fecha) {
        Turno nuevoTurno = new Turno();
        Profesional profesional = profesionalRepositorio.getById(idProfesional);
        nuevoTurno.setProfesional(profesional);
        nuevoTurno.setFechaTurno(LocalDateTime.parse(fecha, formatter));
        nuevoTurno.setAtendido(false);
        nuevoTurno.setCancelado(false);
        nuevoTurno.setEspecialidad(profesional.getEspecialidad());
        profesional.getTurnosDisponibles().add(nuevoTurno);
        profesionalRepositorio.save(profesional);
        turnoRepositorio.save(nuevoTurno);
    }

    @Transactional
    public void modificarTurno(Long idTurno,String motivoConsulta, boolean atendido,boolean cancelado ) {
        Optional<Turno> respuesta = turnoRepositorio.findById(idTurno);
        if (respuesta.isPresent()) {
            Turno turno = respuesta.get();
            turno.setMotivoCosulta(motivoConsulta);
            turno.setCancelado(cancelado);
            turno.setAtendido(atendido);
            turnoRepositorio.save(turno);
        }
    }

    public List<Turno> listaDeTurnosDisponibles(Long idProfecional) {
        List<Turno> turnos = new ArrayList<>();
        turnos = turnoRepositorio.buscarTurnosDisponiblesDeProfecional(idProfecional);
        return turnos;
    }

    public List<Turno> listaDeTurnosPorPaciente(Long idPaciente) {
        List<Turno> turnosPaciente = turnoRepositorio.buscarTurnosPorIdPaciente(idPaciente);
        return turnosPaciente;
    }

}
