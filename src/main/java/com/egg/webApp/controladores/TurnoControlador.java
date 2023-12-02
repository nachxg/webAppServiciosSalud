package com.egg.webApp.controladores;


import com.egg.webApp.entidades.Turno;
import com.egg.webApp.repositorios.TurnoRepositorio;
import com.egg.webApp.servicios.PacienteServicio;
import com.egg.webApp.servicios.ProfesionalServicio;
import com.egg.webApp.servicios.TurnoServicio;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/turno")
public class TurnoControlador {

    @Autowired
    PacienteServicio pacienteServicio;
    @Autowired
    ProfesionalServicio profesionalServicio;
    @Autowired
    TurnoServicio turnoServicio;

    @PostMapping("/crear_turno/{id}")
    public String crearTurno(ModelMap modelo, String fecha, String hora, @PathVariable Long id,RedirectAttributes rdA) {

        try {
            LocalDateTime resultado = turnoServicio.convertirStringALocalDate(fecha, hora);
            Turno respuesta = turnoServicio.existeFechaHora(id, resultado);
            if (respuesta == null) {
                turnoServicio.crearTurnoDisponible(id, resultado);
                rdA.addFlashAttribute("exito","El turno se creó correctamente, y puede ser visualizado desde la sección 'Mis turnos'.");
            } else {
                System.out.println("El turno ya existe");
                rdA.addFlashAttribute("error","El turno ya existe.");
            }      
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return "redirect:/inicio";
    }

    @GetMapping("/listaTurnos/profesional/{id}")
    public String listarTurnosProfesional(ModelMap modelo, @PathVariable Long id) {
        try {
            List<Turno> turnos = turnoServicio.listaDeTurnosDisponibles(id);
            modelo.addAttribute("turnos", turnos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "listarTurnos.html";

    }




}

