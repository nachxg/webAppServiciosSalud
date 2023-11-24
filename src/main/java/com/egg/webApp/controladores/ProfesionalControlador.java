package com.egg.webApp.controladores;
import com.egg.webApp.enumeraciones.Especialidad;
import com.egg.webApp.enumeraciones.Sexo;
import com.egg.webApp.servicios.EnumServicio;
import com.egg.webApp.servicios.ProfesionalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
=======
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
>>>>>>> fd0e386d540f1c7788488bdd0cd18d7ab1d321d1
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
<<<<<<< HEAD
                           @RequestParam String password2, @RequestParam String dni, @RequestParam String sexo, @RequestParam String matricula, @RequestParam String especialidad, @RequestParam String fechaNacimiento) {
=======
                           @RequestParam String password2, @RequestParam String dni, @RequestParam String sexo, @RequestParam String matricula,
                           @RequestParam String especialidad, @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate fechaNacimiento) {
>>>>>>> fd0e386d540f1c7788488bdd0cd18d7ab1d321d1

        try {

            profesionalServicio.registrarProfesional(nombre, apellido, dni, password, password2, sexo, matricula, especialidad, fechaNacimiento);
            return "redirect:/";
            //return "index.html";

        } catch (Exception e) {
            System.out.println("ERROR ERROR USUARIO NO CREADO");
            System.out.println(e.getMessage());
            return "redirect:/registrar/profesional";
        }
    }
}
