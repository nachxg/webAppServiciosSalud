package com.egg.webApp.controladores;

import com.egg.webApp.excepciones.MiExcepcion;
import com.egg.webApp.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/dashboard")
    public String listarUsuarios(ModelMap modelo) {

        modelo.addAttribute("usuarios", usuarioServicio.listarUsuarios());

        //modelo.put("profesional", profesionalServicio.listarProfesionales());
        //modelo.put("roles", enumServicio.obtenerRoles());
        //modelo.put("generos", enumServicio.obtenerGeneros());
        //modelo.put("especialidades", enumServicio.obtenerEspecialidad());

        return "lista_usuarios";
    }

    /*
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
     */


    @GetMapping("/usuario/baja/{id}")
    public String desactivarUsuario(@PathVariable Long id, ModelMap modelo){
        modelo.put("usuarios", usuarioServicio.listarUsuarios());
        return "lista_usuarios.html";

    }

    @PostMapping("/usuario/baja/{id}")
    public String desactivarUsuarios(@PathVariable Long id, ModelMap modelo) {
        try {
            administradorServicio.desactivarActivarUsuario(id);
            modelo.addAttribute("exito", "Usuario desactivado correctamente");
            return "redirect:/admin/dashboard";
        } catch (MiExcepcion e) {
            modelo.addAttribute("error", "Mensaje Admin "+e.getMessage());
            return "redirect:/admin/dashboard";
        }
    }

/*
    @GetMapping("/eliminar/{id}")
   public String eliminarNoticia(@PathVariable Long id, ModelMap modelo){

       modelo.put("noticia", noticiaS.getOne(id));

       return "noticia_eliminar.html";

   }

   @PostMapping("eliminar/{id}")
   public String eliminarNoticia(@PathVariable Long id, String cuerpo, String titulo, ModelMap modelo){

       try {

           noticiaS.bajarNoticia(id);

           return "redirect:../lista";

       } catch (Exception e) {
           modelo.put("error", e.getMessage());

           return "noticia_modificar.html";

       }
*/

}
