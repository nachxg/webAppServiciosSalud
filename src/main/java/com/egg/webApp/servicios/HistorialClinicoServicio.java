package com.egg.webApp.servicios;

import com.egg.webApp.entidades.HistoriaClinica;
import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.entidades.Profesional;
import com.egg.webApp.excepciones.MiExcepcion;
import com.egg.webApp.repositorios.HistoiralClinicoRepositorio;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistorialClinicoServicio {

    @Autowired
    private HistoiralClinicoRepositorio histoiralClinicoRepositorio;

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
        histoiralClinicoRepositorio.save(historialClinico);
    }

    @Transactional
    public void modificarHistorial(Long id, String diagnostico, String tratamiento, String medicacion, String indicaciones, String estudios, String observaciones, LocalDateTime fecha, Paciente paciente, Profesional profesional) throws MiExcepcion {

        Optional<HistoriaClinica> respuesta = histoiralClinicoRepositorio.findById(id);
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
            histoiralClinicoRepositorio.save(historialClinico);
        }
    }

    public List<HistoriaClinica> listaDeHistorialesClinicosPorPaciente(Paciente paciente) {
        List<HistoriaClinica> historiales = new ArrayList();
        historiales = histoiralClinicoRepositorio.buscarHistorialesPorIdPaciente(paciente.getId());
        return historiales;
    }

    public HistoriaClinica getOne(Long id) {
        return histoiralClinicoRepositorio.getOne(id);
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
