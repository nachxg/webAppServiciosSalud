package com.egg.webApp.controladores;

import com.egg.webApp.entidades.Usuario;
import com.egg.webApp.servicios.PacienteServicio;
import com.egg.webApp.servicios.ProfesionalServicio;
import com.egg.webApp.servicios.TurnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

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
    public String crearTurno(ModelMap modelo, String fecha, String hora, @PathVariable Long id, HttpSession session){

        try {

            LocalDateTime resultado = turnoServicio.convertirStringALocalDate(fecha,hora);
            turnoServicio.crearTurnoDisponible(id, resultado);
            //modelo.addAttribute("turnos",turnoServicio.listaDeTurnosDisponibles(id));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "redirect:/inicio";

    }


   }




