package com.egg.webApp.controladores;

import com.egg.webApp.excepciones.MiExcepcion;
import com.egg.webApp.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdministradorControlador {

    private final UsuarioServicio usuarioServicio;
    private final PacienteServicio pacienteServicio;
    private final ProfesionalServicio profesionalServicio;
    private final EnumServicio enumServicio;
    private final AdministradorServicio administradorServicio;

    @Autowired
    public AdministradorControlador(UsuarioServicio usuarioServicio, PacienteServicio pacienteServicio, ProfesionalServicio profesionalServicio, EnumServicio enumServicio, AdministradorServicio administradorServicio) {
        this.usuarioServicio = usuarioServicio;
        this.pacienteServicio = pacienteServicio;
        this.profesionalServicio = profesionalServicio;
        this.enumServicio = enumServicio;
        this.administradorServicio = administradorServicio;
    }

    @GetMapping("/dashboard")
    public String listarUsuarios(ModelMap model) {

<<<<<<< HEAD
        model.put("paciente", pacienteServicio.listarPacientes());
        model.put("profesional", profesionalServicio.listarProfesionales());
        model.put("roles", enumServicio.obtenerRoles());
        model.put("generos", enumServicio.obtenerGeneros());
        model.put("especialidades", enumServicio.obtenerEspecialidad());
        return "adminDashboard";
=======
        modelo.addAttribute("usuarios", usuarioServicio.listarUsuarios());

        //modelo.put("profesional", profesionalServicio.listarProfesionales());
        modelo.put("roles", enumServicio.obtenerRoles());
        //modelo.put("generos", enumServicio.obtenerGeneros());
        //modelo.put("especialidades", enumServicio.obtenerEspecialidad());

        return "lista_usuarios";
>>>>>>> fd0e386d540f1c7788488bdd0cd18d7ab1d321d1
    }

    @PostMapping("/dashboard/cambiar-rol")
    public String cambiarRol(@RequestParam Long id, @RequestParam String rol, Model model) {
        try {
            administradorServicio.establecerRolUsuario(id, rol);
            return "redirect:/admin/dashboard";
        } catch (MiExcepcion e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/admin/dashboard";
        }
    }
<<<<<<< HEAD
=======

>>>>>>> fd0e386d540f1c7788488bdd0cd18d7ab1d321d1

    @GetMapping("/dashboard/desactivar/{id}")
    public String desactivarUsuario(@PathVariable Long id, Model model) {
        try {
            administradorServicio.desactivarActivarUsuario(id);
            model.addAttribute("exito", "Usuario desactivado correctamente");
            return "redirect:/admin/dashboard";
        } catch (MiExcepcion e) {
            model.addAttribute("error", "Mensaje Admin "+e.getMessage());
            return "redirect:/admin/dashboard";
        }
    }
}
