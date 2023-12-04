package com.egg.webApp.controladores;

import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.entidades.Profesional;
import com.egg.webApp.entidades.Usuario;
import com.egg.webApp.excepciones.MiExcepcion;
import com.egg.webApp.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/inicio")
    public String inicioAdmin(Model modelo)  {

        List<Profesional> profesionalesInactivos = null;
        List<Profesional> profesionalesActivos = null;
        List<Paciente> pacientesActivos = null;
        modelo.addAttribute("usuariosRegistradosConAumento", administradorServicio.calcularUsuariosRegistradosConAumento());
        try {
            modelo.addAttribute("porcentajeIncremental", administradorServicio.calcularPorcentajeCambioFormateado());
        } catch (MiExcepcion e) {
            modelo.addAttribute("errorPorcentajeIncremental", e.getMessage());
        }
        modelo.addAttribute("usuarios", usuarioServicio.listarUsuarios());
        try {
            profesionalesInactivos = profesionalServicio.listarProfesionalesPendientesAlta();
            modelo.addAttribute("profesionalesInactivos", profesionalesInactivos);
        } catch (MiExcepcion e) {
            modelo.addAttribute("errorProfesionalInactivo", e.getMessage());
        }
        try {
            profesionalesInactivos = profesionalServicio.listarProfesionalesPendientesAlta();
            modelo.addAttribute("profesionalesInactivos", profesionalesInactivos);
        } catch (MiExcepcion e) {
            modelo.addAttribute("errorProfesionalInactivo", e.getMessage());
        }
        try {
            profesionalesActivos = profesionalServicio.listarProfesionalesActivos();
            modelo.addAttribute("profesionalesActivos", profesionalesActivos);
            modelo.addAttribute("altasProfesionales", profesionalesActivos);
            System.out.println("Profesionales activos: " + profesionalesActivos.size());
        } catch (MiExcepcion e) {
            modelo.addAttribute("errorProfesionalesActivos", e.getMessage());
        }
        try {
            pacientesActivos = pacienteServicio.listarPacientesActivos();
            modelo.addAttribute("pacientesActivos", pacientesActivos);
        } catch (MiExcepcion e) {
            modelo.addAttribute("errorPacientesActivos", e.getMessage());
        }

        return "adminInicio.html";
    }

    @GetMapping("/dashboard")
    public String listarUsuarios(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();


        modelo.addAttribute("usuarios", usuarios);
        modelo.addAttribute("roles", enumServicio.obtenerRoles());

        //modelo.put("profesional", profesionalServicio.listarProfesionales());
        modelo.put("roles", enumServicio.obtenerRoles());
        //modelo.put("generos", enumServicio.obtenerGeneros());
        //modelo.put("especialidades", enumServicio.obtenerEspecialidad());

        return "lista_usuarios";
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
    @GetMapping("/usuario/baja/{id}")
    public String desactivarUsuarios(@PathVariable Long id, ModelMap modelo,  HttpServletRequest request) {
        try {
            administradorServicio.desactivarActivarUsuario(id);
            modelo.addAttribute("exito", "Usuario desactivado correctamente");
            String referencia = request.getHeader("Referer");
            if (referencia != null && referencia.contains("/admin/dashboard/pacientes")) {
                return "redirect:/admin/dashboard/pacientes";
            } else {
                return "redirect:/admin/dashboard";
            }
        } catch (MiExcepcion e) {
            modelo.addAttribute("error", "Mensaje Admin "+e.getMessage());
            return "redirect:/admin/dashboard";
        }
    }
    @GetMapping("/dashboard/pacientes")
    public String listarPacientes(ModelMap modelo) {
        List<Paciente> pacientes = pacienteServicio.listarPacientes();
        modelo.addAttribute("pacientes", pacientes);
        modelo.addAttribute("generos", enumServicio.obtenerGeneros());
        return "lista_pacientes.html";
    }

}
