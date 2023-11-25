package com.egg.webApp.controladores;
import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.enumeraciones.Sexo;
import com.egg.webApp.servicios.EnumServicio;
import com.egg.webApp.servicios.PacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping("/paciente")
public class PacienteControlador {

    @Autowired
    PacienteServicio pacienteServicio;
    @Autowired
    EnumServicio enumServicio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {

        List<Sexo> generos = enumServicio.obtenerGeneros();
        modelo.addAttribute("generos", generos);

        return "registro.html";

    }
    @PostMapping("/registrar")
    public String registro(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String password,
                           @RequestParam String password2, @RequestParam String dni,  @RequestParam String sexo, @RequestParam String fechaNacimiento) {

        try {

            pacienteServicio.registrarPaciente(nombre, apellido, dni, password, password2, sexo, fechaNacimiento);

            return "redirect:/index";

        } catch (Exception e) {
            System.out.println("Usuario no creado " + e.getMessage() );
            return "redirect:/paciente/registrar";
        }
    }

    @GetMapping("/perfil")
    public String perfilPaciente(ModelMap modelo, HttpSession session) {

        List<Sexo> generos = enumServicio.obtenerGeneros();
        modelo.addAttribute("generos", generos);

        Paciente paciente = (Paciente) session.getAttribute("usuariosession");
        modelo.put("paciente", paciente);
        return "editarPaciente.html";
    }

    @PostMapping("/perfil/{id}")
    public String actualizarPaciente(MultipartFile archivo, @PathVariable Long id, @RequestParam String email, @RequestParam String password, @RequestParam String password2,
                                     ModelMap modelo, @RequestParam String telefono, @RequestParam String sexo) {

        try {
            pacienteServicio.actualizarPaciente(archivo, id, email, password,password2, telefono, sexo.toUpperCase());
            return "redirect:/inicio";
        } catch (Exception e) {
            System.out.println("ERROR ERROR ");
            System.out.println(e.getMessage());
            return "editarPaciente.html";
        }
    }
}