package com.egg.webApp.controladores;

import com.egg.webApp.entidades.Usuario;
<<<<<<< HEAD
import com.egg.webApp.enumeraciones.Sexo;
import com.egg.webApp.servicios.EnumServicio;
import com.egg.webApp.servicios.PacienteServicio;
=======
//import com.egg.webApp.enumeraciones.Sexo;
//import com.egg.webApp.servicios.EnumServicio;
//import com.egg.webApp.servicios.PacienteServicio;
import com.egg.webApp.servicios.UsuarioServicio;
>>>>>>> 7fd26438f8bb3d85327f493083382d6c2ca0a3e4
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
    //PacienteServicio pacienteServicio;

   // @Autowired
   // EnumServicio enumServicio;

    @GetMapping
    public String index(/*ModelMap modelo*/) throws Exception {

        //pacienteServicio.registrarPaciente("55345343", "hola1234", "hola1234");

        return "index.html";

    }

    // BORRAR DESPUES:


    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {

        //List<Sexo> generos = enumServicio.obtenerGeneros();
       // modelo.addAttribute("generos", generos);

        return "registrarPrueba.html";
    }

    @PostMapping("/registrar")
    public String registro(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String password,
                           @RequestParam String password2, String dni, String sexo) {

        try {

           // pacienteServicio.registrarPaciente(nombre, apellido, dni, password, password2, sexo);

            return "index.html";

        } catch (Exception e) {
            System.out.println("ERROR ERROR USUARIO NO CREADO");
            System.out.println(e.getMessage());
            return "html";

        }


    }

    @GetMapping("/perfil") // TODO: Hay que solucionar, no podemos entrar a perfil
    public String perfil(ModelMap modelo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);
        return "html";
    }

    @PostMapping("/perfil/{id}") // TODO: Hay que solucionar, no podemos entrar a perfil
    public String actualizar(MultipartFile archivo, @PathVariable Long id, @RequestParam String email, @RequestParam String fechaNacimiento,
                             @RequestParam String password, @RequestParam String password2, ModelMap modelo, String telefono, String sexo) {

        try {
          //  pacienteServicio.actualizarPaciente(archivo, id, email, password,password2, fechaNacimiento, telefono, sexo);

            return "inicio.html";

        } catch (Exception e) {
            System.out.println("ERROR ERROR ");

            return "html";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {

        if (error != null) {

            modelo.put("error", "Usuario o contraseña incorrectos");
        }
        return "login.html";
    }

}
