package com.egg.webApp.controladores;

import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.entidades.Usuario;
import com.egg.webApp.enumeraciones.Sexo;
import com.egg.webApp.repositorios.PacienteRepositorio;
import com.egg.webApp.repositorios.UsuarioRepositorio;
import com.egg.webApp.servicios.EnumServicio;
import com.egg.webApp.servicios.PacienteServicio;
import com.egg.webApp.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    PacienteRepositorio pacienteRepositorio;

    @Autowired
    EnumServicio enumServicio;

    @GetMapping
    public String index(/*ModelMap modelo*/) throws Exception {


        return "index.html";

    }


    @GetMapping("/") //TODO: ACTUALIZAR ESTO
    public String registrar(ModelMap modelo) {

        List<Sexo> generos = enumServicio.obtenerGeneros();
        modelo.addAttribute("generos", generos);

        return ".html"; //TODO: ACTUALIZAR ESTO
    }

    @PostMapping("/") //TODO: ACTUALIZAR ESTO
    public String registro(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String password,
                           @RequestParam String password2, String dni, String sexo, String fechaNacimiento) {

        try {

            pacienteServicio.registrarPaciente(nombre, apellido, dni, password, password2, sexo, fechaNacimiento);

            return "redirect:/inicio.html";

        } catch (Exception e) {
            System.out.println("ERROR ERROR USUARIO NO CREADO");
            System.out.println(e.getMessage());
            return ".html"; //TODO: ACTUALIZAR ESTO

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


        return ".html"; //TODO: ACTUALIZAR ESTO
    }

    @PostMapping("/perfil/{id}")
    public String actualizar(MultipartFile archivo, @RequestParam String email, @RequestParam String fechaNacimiento,
                             @RequestParam String password, @RequestParam String password2,
                             ModelMap modelo, String telefono, String sexo, @PathVariable Long id) {

        try {

            pacienteServicio.actualizarPaciente(archivo, id, email, password, password2, fechaNacimiento, telefono, sexo);

            return ".html"; //TODO: ACTUALIZAR ESTO
        } catch (Exception e) {
            System.out.println("ERROR ERROR ");
            System.out.println(e.getMessage());

            return ".html"; //TODO: ACTUALIZAR ESTO
        }


    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "ERROR, usuario o contraseña invalidos");

        }
        return "login.html";
    }


}
