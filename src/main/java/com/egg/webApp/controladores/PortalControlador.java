package com.egg.webApp.controladores;

import com.egg.webApp.entidades.Usuario;
import com.egg.webApp.servicios.PacienteServicio;
import com.egg.webApp.servicios.ProfesionalServicio;
import com.egg.webApp.servicios.TurnoServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class PortalControlador {
    private final ProfesionalServicio profesionalServicio;
    private final PacienteServicio pacienteServicio;
    private final TurnoServicio turnoServicio;

    public PortalControlador(ProfesionalServicio profesionalServicio, PacienteServicio pacienteServicio, TurnoServicio turnoServicio) {
        this.profesionalServicio = profesionalServicio;
        this.pacienteServicio = pacienteServicio;
        this.turnoServicio = turnoServicio;
    }

    @GetMapping("/index")
    public String index() {
        return "index.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_PACIENTE', 'ROLE_ADMIN', 'ROLE_PROFESIONAL')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session, ModelMap modelo, RedirectAttributes rdA) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        modelo.addAttribute("turnos", turnoServicio.listaDeTurnosDisponibles(logueado.getId()));

        switch (logueado.getRol().toString()) {
            case "PROFESIONAL":
                modelo.addAttribute("profesional", profesionalServicio.getOne(logueado.getId()));
                modelo.addAttribute("turnos", turnoServicio.listaDeTurnosDisponibles(logueado.getId()));

                return "inicioProfesional.html";
            case "ADMIN":
                return "redirect:/admin/inicio";
            default:
                modelo.addAttribute("paciente", pacienteServicio.buscarPorId(logueado.getId()));
                modelo.addAttribute("turnos", turnoServicio.listaDeTurnosParaPaciente(17l));
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