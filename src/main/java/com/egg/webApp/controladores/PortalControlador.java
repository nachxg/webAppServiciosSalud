package com.egg.webApp.controladores;

<<<<<<< HEAD
=======
import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.entidades.Profesional;
import com.egg.webApp.entidades.Usuario;
>>>>>>> 8547b25e8057f5757241fd457b275703cae50651
import com.egg.webApp.enumeraciones.Sexo;
import com.egg.webApp.repositorios.PacienteRepositorio;
import com.egg.webApp.repositorios.UsuarioRepositorio;
import com.egg.webApp.servicios.EnumServicio;
import com.egg.webApp.servicios.PacienteServicio;
<<<<<<< HEAD
=======
import com.egg.webApp.servicios.ProfesionalServicio;
import com.egg.webApp.servicios.UsuarioServicio;
>>>>>>> 8547b25e8057f5757241fd457b275703cae50651
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
=======
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
>>>>>>> 7144b931cd489a32de1570259484d5c2faf1d2cf
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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

<<<<<<< HEAD
    @GetMapping("/")
=======
    @GetMapping("/index")
>>>>>>> 8547b25e8057f5757241fd457b275703cae50651
    public String index(/*ModelMap modelo*/) throws Exception {


        return "index.html";

    }


    @GetMapping("/") //TODO: ACTUALIZAR ESTO
    public String registrar(ModelMap modelo) {

        List<Sexo> generos = enumServicio.obtenerGeneros();
        modelo.addAttribute("generos", generos);

<<<<<<< HEAD
<<<<<<< HEAD
        return "registrarPrueba.html";
=======
        return ".html"; //TODO: ACTUALIZAR ESTO
>>>>>>> 7144b931cd489a32de1570259484d5c2faf1d2cf
=======
        return "registrarPrueba.html";
>>>>>>> 8547b25e8057f5757241fd457b275703cae50651
    }

    @PostMapping("/") //TODO: ACTUALIZAR ESTO
    public String registro(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String password,
                           @RequestParam String password2, String dni, String sexo, String fechaNacimiento) {

        try {
<<<<<<< HEAD
            pacienteServicio.registrarPaciente(nombre, apellido, dni, password, password2, sexo);
            return "redirect:/inicio";
=======

<<<<<<< HEAD
            pacienteServicio.registrarPaciente(nombre, apellido, dni, password, password2, sexo, fechaNacimiento);

            return "redirect:/inicio.html";
>>>>>>> 7144b931cd489a32de1570259484d5c2faf1d2cf
=======
            return "redirect:/";
>>>>>>> 8547b25e8057f5757241fd457b275703cae50651

        } catch (Exception e) {
            System.out.println("ERROR ERROR USUARIO NO CREADO");
            System.out.println(e.getMessage());
<<<<<<< HEAD
<<<<<<< HEAD
            return "inicio.html";
=======
            return ".html"; //TODO: ACTUALIZAR ESTO
=======
            return "redirect:/registrar";
>>>>>>> 8547b25e8057f5757241fd457b275703cae50651

        }
    }

<<<<<<< HEAD
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
=======
    @GetMapping("/perfil/paciente") // TODO: Hay que solucionar, no podemos entrar a perfil
    public String perfilPaciente(ModelMap modelo, HttpSession session) {
        Paciente paciente = (Paciente) session.getAttribute("usuariosession");
        modelo.put("paciente", paciente);
        return "editarPerfilPaciente.html";
    }

    @PostMapping("/perfil/paciente/{id}") // TODO: Hay que solucionar, no podemos entrar a perfil
    public String actualizar(MultipartFile archivo, @PathVariable Long id, @RequestParam String email, @RequestParam String fechaNacimiento,
                             @RequestParam String password, @RequestParam String password2, ModelMap modelo, String telefono, String sexo) {
>>>>>>> 8547b25e8057f5757241fd457b275703cae50651

        try {

            pacienteServicio.actualizarPaciente(archivo, id, email, password, password2, fechaNacimiento, telefono, sexo);

            return ".html"; //TODO: ACTUALIZAR ESTO
        } catch (Exception e) {
            System.out.println("ERROR ERROR ");
            System.out.println(e.getMessage());

<<<<<<< HEAD
            return ".html"; //TODO: ACTUALIZAR ESTO
>>>>>>> 7144b931cd489a32de1570259484d5c2faf1d2cf
=======
            return "editarPerfilPaciente.html";
>>>>>>> 8547b25e8057f5757241fd457b275703cae50651
        }
    }
<<<<<<< HEAD
=======
    @GetMapping("/perfil/profesional") // TODO: Hay que solucionar, no podemos entrar a perfil
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
            modelo.put("error", "ERROR, usuario o contraseña invalidos");
>>>>>>> 8547b25e8057f5757241fd457b275703cae50651

<<<<<<< HEAD


    @GetMapping("/login") // TODO: Hay que solucionar el login, tira eror. No se puedo acceder
=======
    @GetMapping("/login")
>>>>>>> 7144b931cd489a32de1570259484d5c2faf1d2cf
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {

        if (error != null) {

            modelo.put("error", "Usuario o contraseña incorrectos");
        }
        return "login.html";
    }
<<<<<<< HEAD
=======


>>>>>>> 7144b931cd489a32de1570259484d5c2faf1d2cf
}
    
    
   


