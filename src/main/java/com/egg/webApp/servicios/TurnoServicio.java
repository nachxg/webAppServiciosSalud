package com.egg.webApp.servicios;

import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.entidades.Profesional;
import com.egg.webApp.entidades.Turno;
import com.egg.webApp.repositorios.ProfesionalRepositorio;
import com.egg.webApp.repositorios.TurnoRepositorio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnoServicio {

    @Autowired
    private TurnoRepositorio turnoRepositorio;
    @Autowired
    private PacienteServicio pacienteServicio;
    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Transactional
    public void crearTurnoDisponible(Long idProfesional, LocalDateTime fecha) {

        try{
            Turno nuevoTurno = new Turno();
            Profesional profesional = profesionalRepositorio.getById(idProfesional);

            if (profesional.isAltaSistema()) {
                nuevoTurno.setProfesional(profesional);
                nuevoTurno.setFechaTurno(fecha);
                nuevoTurno.setAtendido(false);
                nuevoTurno.setCancelado(false);
                nuevoTurno.setTurnoTomado(false);
                nuevoTurno.setEspecialidad(profesional.getEspecialidad());
                profesional.getTurnosDisponibles().add(nuevoTurno);
                profesionalRepositorio.save(profesional);
                turnoRepositorio.save(nuevoTurno);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Transactional
    public void modificarTurno(Long idTurno, String motivoConsulta, boolean atendido, boolean cancelado) {
        Optional<Turno> respuesta = turnoRepositorio.findById(idTurno);
        if (respuesta.isPresent()) {
            Turno turno = respuesta.get();
            turno.setMotivoConsulta(motivoConsulta);
            turno.setCancelado(cancelado);
            turno.setAtendido(atendido);
            turnoRepositorio.save(turno);
        }
    }

    @Transactional
    public void tomarUnTurnoPaciente(Long idTurno, Long idPaciente) {
        Paciente paciente = pacienteServicio.getOne(idPaciente);
        Turno turno = turnoRepositorio.getOne(idTurno);
        if (paciente.isAltaSistema() && !turno.isAtendido() && !turno.isCancelado()) {
            turno.setTurnoTomado(true);
            turno.setPaciente(paciente);
            turnoRepositorio.save(turno);
        }
    }

    public List<Turno> listaDeTurnosDisponibles(Long idProfesional) {
        List<Turno> turnos = turnoRepositorio.buscarTurnosDisponiblesDeProfesional(idProfesional);
        return turnos;
    }

/*



    public List<Turno> listaDeTodosLosTurnosPorProfesional(Long idProfecional) {
        List<Turno> turnos = turnoRepositorio.todosLosTurnosDeProfecional(idProfecional);
        return turnos;
    }

    public List<Turno> listaDeTurnosPorEspecialidad(String especialidad) {
        List<Turno> turnos = turnoRepositorio.todosLosTurnosPorEspecialidad(especialidad);
        return turnos;
    }

    public List<Turno> listaDeTurnosPorPaciente(Long idPaciente) {
        List<Turno> turnos = turnoRepositorio.buscarTurnosPorIdPaciente(idPaciente);
        return turnos;
    }

    public List<Turno> listaDeTurnosPorPacienteAtendido(Long idPaciente) {
        List<Turno> turnos = turnoRepositorio.buscarTurnosPorIdPacienteAtendido(idPaciente);
        return turnos;
    }
    */
    public Turno getOne(Long id){
        return turnoRepositorio.getOne(id);
    }

    public LocalDateTime convertirStringALocalDate(String fecha, String hora) {

        System.out.println(fecha);
        System.out.println(hora);

        if (fecha == null || hora == null || fecha.isEmpty() || hora.isEmpty()) {

            throw new IllegalArgumentException("Fecha y hora deben estar presentes y no deben ser vacías.");
        }

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String fechaHoraString = fecha + " " + hora;

        return LocalDateTime.parse(fechaHoraString, formato);

    }

}
