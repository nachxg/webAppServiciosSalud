package com.egg.webApp.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/turno")
public class TurnoControlador {


    @GetMapping("/solicitar-turno/{profesionalId}")
    public String mostrarFormularioSolicitudTurno(@PathVariable Long profesionalId, HttpSession session) {

        return "redirect:/confirmacion-turno";
    }

    @PostMapping("/solicitar-turno/{profesionalId}")
    public String solicitarTurno(@PathVariable Long profesionalId, HttpSession session) {
        return "redirect:/confirmacion-turno";
    }

}