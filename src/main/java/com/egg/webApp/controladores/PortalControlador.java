package com.egg.webApp.controladores;


import com.egg.webApp.entidades.Usuario;
import com.egg.webApp.excepciones.MiExcepcion;
import com.egg.webApp.repositorios.TurnoRepositorio;
import com.egg.webApp.servicios.EnumServicio;
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
    TurnoRepositorio turnoRepositorio;
    @Autowired
    EnumServicio enumServicio;
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

        // NOS TRAE UN PACIENTE PARA PASARLO A LA VISTA
        switch (logueado.getRol().toString()) {
            case "PROFESIONAL":
                modelo.addAttribute("profesional", profesionaServicio.buscarPorId(logueado.getId()));
                modelo.addAttribute("turnos", turnoServicio.listaDeTurnosDisponibles(logueado.getId()));
                return "inicioProfesional.html";
            case "ADMIN":
                return "redirect:/admin/inicio";
            default:
                modelo.addAttribute("especialidades", enumServicio.obtenerEspecialidad());
                try {
                    modelo.addAttribute("profesionales", profesionaServicio.listarProfesionales());
                } catch (MiExcepcion e) {
                    throw new RuntimeException(e);
                }
                modelo.addAttribute("turnos",turnoRepositorio.findAll());
                modelo.addAttribute("paciente", pacienteServicio.buscarPorId(logueado.getId()));
                return "inicio.html";          
        }
    }


    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o contraseña incorrectos");
        }
        return "login.html";
    }


}