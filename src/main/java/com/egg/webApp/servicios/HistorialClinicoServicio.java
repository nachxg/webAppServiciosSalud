package com.egg.webApp.servicios;

import com.egg.webApp.entidades.HistoriaClinica;
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
    public void crearHistorial(String nota, LocalDateTime fecha) throws Exception {
        HistoriaClinica historialClinico = new HistoriaClinica();
        historialClinico.setNotas(nota);
        historialClinico.setFechaConsulta(fecha);
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