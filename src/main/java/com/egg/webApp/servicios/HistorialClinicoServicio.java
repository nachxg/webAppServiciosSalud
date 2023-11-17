package com.egg.webApp.servicios;

import com.egg.webApp.entidades.HistoriaClinica;
import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.entidades.Profesional;
import com.egg.webApp.excepciones.MiExcepcion;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egg.webApp.repositorios.HistorialClinicoRepositorio;

@Service
public class HistorialClinicoServicio {

    @Autowired
    private HistorialClinicoRepositorio historialClinicoRepositorio;

    @Transactional
    public void crearHistorial(String diagnostico, String tratamiento, String medicacion, String indicaciones, String estudios, String observaciones, LocalDateTime fecha, Paciente paciente, Profesional profesional) throws MiExcepcion {

        HistoriaClinica historialClinico = new HistoriaClinica();
        validar(paciente, profesional);
        historialClinico.setDiagnostico(diagnostico);
        historialClinico.setTratamiento(tratamiento);
        historialClinico.setMedicacion(medicacion);
        historialClinico.setIndicaciones(indicaciones);
        historialClinico.setEstudios(estudios);
        historialClinico.setObservaciones(observaciones);
        historialClinico.setFechaVisita(fecha);
        historialClinico.setPaciente(paciente);
        historialClinico.setProfesional(profesional);
        historialClinicoRepositorio.save(historialClinico);
    }

    @Transactional
    public void modificarHistorial(Long id, String diagnostico, String tratamiento, String medicacion, String indicaciones, String estudios, String observaciones, LocalDateTime fecha, Paciente paciente, Profesional profesional) throws MiExcepcion {

        Optional<HistoriaClinica> respuesta = historialClinicoRepositorio.findById(id);
        validar(paciente, profesional);
        if (respuesta.isPresent()) {
            HistoriaClinica historialClinico = respuesta.get();
            historialClinico.setDiagnostico(diagnostico);
            historialClinico.setTratamiento(tratamiento);
            historialClinico.setMedicacion(medicacion);
            historialClinico.setIndicaciones(indicaciones);
            historialClinico.setEstudios(estudios);
            historialClinico.setObservaciones(observaciones);
            historialClinico.setFechaVisita(fecha);
            historialClinico.setPaciente(paciente);
            historialClinico.setProfesional(profesional);
            historialClinicoRepositorio.save(historialClinico);
        }
    }

    public List<HistoriaClinica> listaDeHistorialesClinicosPorPaciente(Paciente paciente) {
        List<HistoriaClinica> historiales = new ArrayList();
        historiales = historialClinicoRepositorio.buscarHistorialesPorIdPaciente(paciente.getId());
        return historiales;
    }

    public HistoriaClinica getOne(Long id) {
        return historialClinicoRepositorio.getOne(id);
    }

    private void validar(Paciente paciente, Profesional profesional) throws MiExcepcion {
        if (paciente == null) {
            throw new MiExcepcion("el paciente no puede ser nulo");
        }
        if (profesional == null) {
            throw new MiExcepcion("el profecional no puede ser nulo");
        }
    }
}
