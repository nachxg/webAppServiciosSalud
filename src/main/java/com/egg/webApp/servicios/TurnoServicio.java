package com.egg.webApp.servicios;

import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.entidades.Profesional;
import com.egg.webApp.entidades.Turno;
import com.egg.webApp.repositorios.ProfesionalRepositorio;
import com.egg.webApp.repositorios.TurnoRepositorio;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TurnoServicio {
    private final TurnoRepositorio turnoRepositorio;
    private final PacienteServicio pacienteServicio;
    private final ProfesionalRepositorio profesionalRepositorio;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public TurnoServicio(TurnoRepositorio turnoRepositorio, PacienteServicio pacienteServicio, ProfesionalRepositorio profesionalRepositorio) {
        this.turnoRepositorio = turnoRepositorio;
        this.pacienteServicio = pacienteServicio;
        this.profesionalRepositorio = profesionalRepositorio;
    }

    @Transactional
    public void crearTurnoDisponible(Long idProfesional, LocalDateTime fecha) {
        Turno nuevoTurno = new Turno();
        Profesional profesional = profesionalRepositorio.getById(idProfesional);
        if (profesional.isAltaSistema()) {
            nuevoTurno.setProfesional(profesional);
            nuevoTurno.setFechaTurno(fecha);
            nuevoTurno.setAtendido(false);
            nuevoTurno.setCancelado(false);
            nuevoTurno.setEspecialidad(profesional.getEspecialidad());
            nuevoTurno.setMotivoConsulta("Motivo de consulta");
            nuevoTurno.setPaciente(null);
            profesional.getTurnosDisponibles().add(nuevoTurno);
            profesionalRepositorio.save(profesional);
            turnoRepositorio.save(nuevoTurno);
        }
    }

    @Transactional
    public void modificarTurno(Long idTurno, LocalDateTime fecha) {
        Optional<Turno> respuesta = turnoRepositorio.findById(idTurno);
        if (respuesta.isPresent()) {
            Turno turno = respuesta.get();
            turno.setFechaTurno(fecha);
            turnoRepositorio.save(turno);
        }
    }

    @Transactional
    public void tomarUnTurnoPaciente(Long idPaciente, Long idTurno, String motivoConsulta) {

        Paciente paciente = pacienteServicio.getOne(idPaciente);
        Turno turno = turnoRepositorio.getOne(idTurno);
        //if (paciente.isAltaSistema() && !turno.isAtendido() && !turno.isCancelado()) {
        turno.setTurnoTomado(true);
        turno.setPaciente(paciente);
        turno.setMotivoConsulta(motivoConsulta);
        turnoRepositorio.save(turno);
        //}
    }

    @Transactional
    public void cancelarTurnoProfesional(Long id) {
        Turno turno = turnoRepositorio.buscarTurnosPorId(id);

        if (turno != null) {
            turno.setCancelado(true);
            turnoRepositorio.save(turno);
        } else {
            System.out.println("No encontro Turno");
        }
    }

    @Transactional
    public void cancelarTurnoPaciente(Long id) {
        Turno turno = turnoRepositorio.buscarTurnosPorId(id);

        if (turno != null) {
            turno.setTurnoTomado(false); // La idea es manejar el booleano de turno tomado para paciente y turno cancelado para profesional
            turnoRepositorio.save(turno);
        } else {
            System.out.println("No encontro Turno");
        }
    }

    public List<Turno> listaDeTurnosDisponibles(Long idProfesional) {
        List<Turno> turnos = turnoRepositorio.buscarTurnosDisponiblesDeProfesional(idProfesional);
        return turnos;
    }

    public Turno existeFechaHora(Long idProfesional, LocalDateTime fechaHora) throws Exception {
        return turnoRepositorio.existeFechaHora(idProfesional, fechaHora);
    }

    public List<Turno> listaDeTurnosParaPaciente(Long idProfesional) {

        LocalDateTime fecha = LocalDateTime.now();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        String fechaFormateada = fecha.format(formato);

        LocalDateTime fechaFormateadaALocalDate = LocalDateTime.parse(fechaFormateada, formato);

        return turnoRepositorio.buscarTurnosDisponiblesParaPaciente(idProfesional, fechaFormateadaALocalDate);
    }

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

    public Turno getOne(Long id) {
        return turnoRepositorio.getOne(id);
    }

    public LocalDateTime convertirStringALocalDate(String fecha, String hora) {

        System.out.println(fecha);
        System.out.println(hora);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        String fechaHoraString = fecha + " " + hora;

        return LocalDateTime.parse(fechaHoraString, formato);
    }

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public LocalDateTime convertirStringALocalDateb(String fecha, String hora) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String fechaHoraString = fecha + " " + hora;
        return LocalDateTime.parse(fechaHoraString, formato);

    }
}