package com.egg.webApp.controladores;


import com.egg.webApp.entidades.Usuario;
import com.egg.webApp.servicios.PacienteServicio;
import com.egg.webApp.servicios.ProfesionalServicio;
import com.egg.webApp.servicios.TurnoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    PacienteServicio pacienteServicio;
    @Autowired
    ProfesionalServicio profesionaServicio;
    @Autowired
    TurnoServicio turnoServicio;
    
    @GetMapping("/index")
    public String index(){
        return "index.html";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_PACIENTE', 'ROLE_ADMIN', 'ROLE_PROFESIONAL')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session, ModelMap modelo) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
<<<<<<< HEAD
        if (logueado.getRol().toString().equals("ADMIN")) {
            return "redirect:/admin/inicio";
        }
        if (logueado.getRol().toString().equals("PROFESIONAL")) {
            return "redirect:/profesional/inicio";
        }
        modelo.addAttribute("paciente", pacienteServicio.buscarPorId(logueado.getId()));
        return "inicio.html";
=======

        // NOS TRAE UN PACIENTE PARA PASARLO A LA VISTA
        switch (logueado.getRol().toString()) {
            case "PROFESIONAL":
                modelo.addAttribute("profesional", profesionaServicio.buscarPorId(logueado.getId()));
                modelo.addAttribute("turnos", turnoServicio.listaDeTurnosDisponibles(logueado.getId()));
                return "inicioProfesional.html";
            case "ADMIN":
                return "redirect:/admin/inicio";
            default:
                modelo.addAttribute("paciente", pacienteServicio.buscarPorId(logueado.getId()));
                return "inicio.html";          
        }
>>>>>>> 7ee957b3d99090c326b86128a21cab555fdd9206
    }


    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o contraseña incorrectos");
        }
        return "login.html";
    }


}