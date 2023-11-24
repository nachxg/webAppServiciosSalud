package com.egg.webApp.controladores;

import com.egg.webApp.entidades.Profesional;
import com.egg.webApp.enumeraciones.Especialidad;
import com.egg.webApp.enumeraciones.Sexo;
import com.egg.webApp.servicios.EnumServicio;
import com.egg.webApp.servicios.ProfesionalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/profesional")
public class ProfesionalControlador {
    @Autowired
    ProfesionalServicio profesionalServicio;
    @Autowired
    EnumServicio enumServicio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {

        List<Sexo> generos = enumServicio.obtenerGeneros();
        modelo.addAttribute("generos", generos);

        List<Especialidad> especialidades = enumServicio.obtenerEspecialidad();
        modelo.addAttribute("especialidades", especialidades);

        return "registrar_profesional.html";
    }

    @PostMapping("/registrar")
    public String registro(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String password,
                           @RequestParam String password2, @RequestParam String dni, @RequestParam String sexo, @RequestParam String matricula,
                           @RequestParam String especialidad, @RequestParam String fechaNacimiento, ModelMap modelo) {

        try {

            profesionalServicio.registrarProfesional(nombre, apellido, dni, password, password2, sexo, matricula, especialidad, fechaNacimiento);
            modelo.put("exito", "Usuario creado con exito");
            return "redirect:/index";

        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "redirect:/profesional/registrar";
        }
    }

    @GetMapping("/perfil")
    public String perfilProfesional(ModelMap modelo, HttpSession session) {

        List<Sexo> generos = enumServicio.obtenerGeneros();
        modelo.addAttribute("generos", generos);

        Profesional profesional = (Profesional) session.getAttribute("usuariosession");
        modelo.put("profesional", profesional);
        return "editarProfesional.html";
    }

    @PostMapping("/perfil/{id}")
    public String actualizarProfesional(MultipartFile archivo, @PathVariable Long id, @RequestParam String email,
                                        @RequestParam String password, @RequestParam String password2, ModelMap modelo, @RequestParam String telefono, @RequestParam String sexo) {

        try {
            profesionalServicio.actualizarProfesional(archivo, id, email, password, password2, telefono, sexo.toUpperCase());
            modelo.put("exito", "Profesional actualizado con exito");
            return "inicio.html";

        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "editarProfesional.html";
        }
    }

    @GetMapping("/especialidad")
    public String especialidad(ModelMap modelo) {

        modelo.addAttribute("especialidades", enumServicio.obtenerEspecialidad());

        return "especialidades.html";
    }

}
