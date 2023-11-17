package com.egg.webApp.controladores;


import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.entidades.Profesional;
import com.egg.webApp.enumeraciones.Sexo;
import com.egg.webApp.repositorios.PacienteRepositorio;
import com.egg.webApp.servicios.EnumServicio;
import com.egg.webApp.servicios.PacienteServicio;
import com.egg.webApp.servicios.ProfesionalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    PacienteServicio pacienteServicio;
    @Autowired
    ProfesionalServicio profesionalServicio;

    @Autowired
    PacienteRepositorio pacienteRepositorio;

    @Autowired
    EnumServicio enumServicio;


    @GetMapping("/index")
    public String index(/*ModelMap modelo*/) throws Exception {
        return "index.html";
    }


    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {

        List<Sexo> generos = enumServicio.obtenerGeneros();
        modelo.addAttribute("generos", generos);

        return "registro.html";

    }

    @PostMapping("/registrar")
    public String registro(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String password,
                           @RequestParam String password2, String dni, String sexo, String fechaNacimiento) {
        try {
            pacienteServicio.registrarPaciente(nombre, apellido, dni, password, password2, sexo, fechaNacimiento);
            return "redirect:/inicio.html";
        } catch (Exception e) {
            System.out.println("ERROR ERROR USUARIO NO CREADO");
            System.out.println(e.getMessage());
            return "inicio.html"; //TODO: ACTUALIZAR ESTO
        }
    }


    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    }

    @GetMapping("/perfil")
    public String perfil(ModelMap modelo, HttpSession session) {

        List<Sexo> generos = enumServicio.obtenerGeneros();
        modelo.addAttribute("generos", generos);

        Paciente paciente = (Paciente) session.getAttribute("usuariosession");
        modelo.put("paciente", paciente);


        return "editarPerfilPaciente.html";
    }


    @GetMapping("/perfil/paciente") // TODO: Hay que solucionar, no podemos entrar a perfil
    public String perfilPaciente(ModelMap modelo, HttpSession session) {
        Paciente paciente = (Paciente) session.getAttribute("usuariosession");
        modelo.put("paciente", paciente);
        return "editarPerfilPaciente.html";
    }

    @PostMapping("/perfil/paciente/{id}") // TODO: Hay que solucionar, no podemos entrar a perfil
    public String actualizar(MultipartFile archivo, @PathVariable Long id, @RequestParam String email, @RequestParam String fechaNacimiento,
                             @RequestParam String password, @RequestParam String password2, ModelMap modelo, String telefono, String sexo) {

        try {
            pacienteServicio.actualizarPaciente(archivo, id, email, password, password2, fechaNacimiento, telefono, sexo);
            return "index.html";
        } catch (Exception e) {
            System.out.println("ERROR ERROR ");
            System.out.println(e.getMessage());
            return "editarPerfilPaciente.html";
        }
    }

    @GetMapping("/perfil/profesional")
    public String perfilProfesional(ModelMap modelo, HttpSession session) {

        List<Sexo> generos = enumServicio.obtenerGeneros();
        modelo.addAttribute("generos", generos);

        Profesional profesional = (Profesional) session.getAttribute("usuariosession");
        modelo.put("profesional", profesional);
        return "editarPerfilProfesional.html";
    }

    @PostMapping("/perfil/profesional/{id}") // TODO: Hay que solucionar, no podemos entrar a perfil
    public String actualizar(MultipartFile archivo, @PathVariable Long id, @RequestParam String email,
                             @RequestParam String password, @RequestParam String password2, ModelMap modelo, @RequestParam String telefono, @RequestParam String sexo) {

        try {
            profesionalServicio.actualizarProfesional(archivo, id, email, password, password2, telefono, sexo);

            return "inicio.html";

        } catch (Exception e) {
            System.out.println("ERROR ERROR ");

            return "editarPerfilProfesional.html";
        }


    }

    @GetMapping("/login") // TODO: Hay que solucionar el login, tira eror. No se puedo acceder
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {

        if (error != null) {

            modelo.put("error", "Usuario o contraseña incorrectos");
        }
        return "login.html";
    }
}

    
   


