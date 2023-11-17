package com.egg.webApp.controladores;

import com.egg.webApp.servicios.PacienteServicio;
import com.egg.webApp.servicios.ProfesionalServicio;
import com.egg.webApp.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdministradorControlador {

    private final UsuarioServicio usuarioServicio;
    private final PacienteServicio pacienteServicio;
    private final ProfesionalServicio profesionalServicio;

    @Autowired
    public AdministradorControlador(UsuarioServicio usuarioServicio, PacienteServicio pacienteServicio, ProfesionalServicio profesionalServicio) {
        this.usuarioServicio = usuarioServicio;
        this.pacienteServicio = pacienteServicio;
        this.profesionalServicio = profesionalServicio;
    }

    @GetMapping("/dashboard")
    public String listarUsuarios(ModelMap model) {
        model.put("paciente", pacienteServicio.listarPacientes());
        model.put("profesional", profesionalServicio.listarProfesionales());
        return "adminDashboard";
    }
}
