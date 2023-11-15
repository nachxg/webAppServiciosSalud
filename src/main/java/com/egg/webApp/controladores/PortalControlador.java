package com.egg.webApp.controladores;

import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.servicios.PacienteServicio;
import com.egg.webApp.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    PacienteServicio pacienteServicio;

    @GetMapping
    public String index(/*ModelMap modelo*/) throws Exception {

        //pacienteServicio.registrarPaciente("55345343", "hola1234", "hola1234");

        return "index.html";

    }

    // BORRAR DESPUES:


    @GetMapping("/registrar")
    public String registrar() {

        return "registrarPrueba.html";
    }

    @PostMapping("/registrarPrueba")
    public String registro(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String password,
                           @RequestParam String password2, String dni, String sexo) {

        try {
            pacienteServicio.registrarPaciente(nombre, apellido, dni, password, password2, sexo);

            return "index.html";

        } catch (Exception e) {
            System.out.println("ERROR ERROR USUARIO NO CREADO");

            return "registrarPrueba.html";

        }


    }

    @GetMapping("/perfil")
    public String perfil(ModelMap modelo, HttpSession session) {
        //Paciente paciente = (Paciente) session.getAttribute("usuarioSession");
        //modelo.put("paciente", paciente);
        return "editarPerfil.html";
    }

    @PostMapping("/perfil/{id}")
    public String actualizar(MultipartFile archivo, @PathVariable Long id, @RequestParam String email, @RequestParam String fechaNacimiento,
                             @RequestParam String password, @RequestParam String password2, ModelMap modelo, String telefono, String sexo) {

        try {
            pacienteServicio.actualizarPaciente(archivo, id, email, password,password2, fechaNacimiento, telefono, sexo);

            return "inicio.html";

        } catch (Exception e) {
            System.out.println("ERROR ERROR ");

            return "editarPerfil.html";
        }


    }


}
