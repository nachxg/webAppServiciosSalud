package com.egg.webApp.servicios;

import com.egg.webApp.entidades.HistoriaClinica;
import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.entidades.Profesional;
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
    public void crearHistorial(String diagnostico, String tratamiento, String medicacion, String indicaciones, String estudios, String observaciones, LocalDateTime fecha, Paciente paciente, Profesional profesional) throws Exception {
        HistoriaClinica historialClinico = new HistoriaClinica();
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
    public void modificarHistorial(String nota, LocalDateTime fecha, Long id) throws Exception {
        Optional<HistoriaClinica> respuesta = histoiralClinicoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            HistoriaClinica historialClinico = respuesta.get();
            historialClinico.setNotas(nota);
            historialClinico.setFechaConsulta(fecha);
            histoiralClinicoRepositorio.save(historialClinico);
        }
    }

    public List<HistoriaClinica> listaDeHistorial() {
        List<HistoriaClinica> historiales = new ArrayList();
        historiales = histoiralClinicoRepositorio.findAll();
        return historiales;
    }