package com.egg.webApp.controladores;

import com.egg.webApp.entidades.Usuario;
import com.egg.webApp.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imagen")
public class ImagenControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/{id}") // FALTA AGREGAR LA URL DEL GET MAPPING
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable String  id){

        Usuario usuario = usuarioServicio.getOne(id); // TIRA ERROR PORQUE USUARIO SERVICIO ESTA VACIO

        byte[] imagen =  usuario.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders();

        // Metodo para que se pueda ingresar cualquier tipo de imagen (png, jpg, gif, etc)
        String mime = usuario.getImagen().getMime().toLowerCase();

        MediaType tipo = MediaType.parseMediaType("image/" + mime.replace("/", "_"));

        headers.setContentType(tipo);

        return new ResponseEntity<>(imagen,headers, HttpStatus.OK);
    }

}
