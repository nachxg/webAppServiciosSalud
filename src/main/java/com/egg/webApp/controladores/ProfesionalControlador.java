package com.egg.webApp.controladores;

import com.egg.webApp.enumeraciones.Especialidad;
import com.egg.webApp.enumeraciones.Sexo;
import com.egg.webApp.servicios.EnumServicio;
import com.egg.webApp.servicios.ProfesionalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
@RequestMapping("/registrar")
public class ProfesionalControlador {
    @Autowired
    ProfesionalServicio profesionalServicio;
    @Autowired
    EnumServicio enumServicio;
    @GetMapping("/profesional")
    public String registrar(ModelMap modelo) {

        List<Sexo> generos = enumServicio.obtenerGeneros();
        modelo.addAttribute("generos", generos);

        List<Especialidad> especialidades = enumServicio.obtenerEspecialidad();
        modelo.addAttribute("especialidades", especialidades);

        return "registrar_profesional.html";
    }

    @PostMapping("/profesional")
    public String registro(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String password,
                           @RequestParam String password2, String dni, String sexo, @RequestParam String matricula, @RequestParam String especialidad) {

        try {

            profesionalServicio.registrarProfesional(nombre, apellido, dni, password, password2, sexo, matricula, especialidad);

            return "index.html";

        } catch (Exception e) {
            System.out.println("ERROR ERROR USUARIO NO CREADO");
            System.out.println(e.getMessage());
            return "registrar_profesional.html";

        }


    }

}
