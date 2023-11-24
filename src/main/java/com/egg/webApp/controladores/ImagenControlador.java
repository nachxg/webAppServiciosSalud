package com.egg.webApp.controladores;

import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.entidades.Profesional;
import com.egg.webApp.servicios.PacienteServicio;
import com.egg.webApp.servicios.ProfesionalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Controlador para las operaciones relacionadas con imágenes en la aplicación.
 * Todas las URL asociadas a este controlador comienzan con "/imagen".
 *  * @version v0.1.0-alfa 11/11/2023
 */
@Controller
@RequestMapping("/imagen")
public class ImagenControlador {

    @Autowired
    PacienteServicio pacienteServicio;
    @Autowired
    ProfesionalServicio profesionalServicio;
<<<<<<< HEAD
    
    @GetMapping("/perfil/paciente{id}")
    public ResponseEntity<byte[]> imagenPaciente(@PathVariable Long  id){

        Paciente paciente = pacienteServicio.getOne(id);
        byte[] imagen =  paciente.getImagen().getContenido();
=======
    @GetMapping("/perfil/{id}")
    public ResponseEntity<byte[]> imagenProfesional(@PathVariable Long id) {
        Usuario usuario = usuarioServicio.getOne(id);
>>>>>>> fd0e386d540f1c7788488bdd0cd18d7ab1d321d1


        HttpHeaders headers = new HttpHeaders();

        // Metodo para que se pueda ingresar cualquier tipo de imagen (png, jpg, gif, etc)
        String mime = paciente.getImagen().getMime().toLowerCase();

        MediaType tipo = MediaType.parseMediaType("image/" + mime.replace("/", "_"));

        headers.setContentType(tipo);

        return new ResponseEntity<>(imagen,headers, HttpStatus.OK);
    }
    @GetMapping("/perfil/profesional{id}")
    public ResponseEntity<byte[]> imagenProfesional(@PathVariable Long  id){

<<<<<<< HEAD
        Profesional profesional = profesionalServicio.getOne(id);
        byte[] imagen =  profesional.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders();

        // Metodo para que se pueda ingresar cualquier tipo de imagen (png, jpg, gif, etc)
        String mime = profesional.getImagen().getMime().toLowerCase();

        MediaType tipo = MediaType.parseMediaType("image/" + mime.replace("/", "_"));

        headers.setContentType(tipo);

        return new ResponseEntity<>(imagen,headers, HttpStatus.OK);
    }

=======
>>>>>>> fd0e386d540f1c7788488bdd0cd18d7ab1d321d1
}
