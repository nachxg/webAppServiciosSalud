package com.egg.webApp.servicios;

import com.egg.webApp.entidades.ImagenPredeterminada;
import com.egg.webApp.excepciones.MiExcepcion;
import com.egg.webApp.repositorios.ImagenPredeterminadaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImagenPredeterminadaServicio {
    private final ImagenPredeterminadaRepository imagenPredeterminadaRepository;

    public ImagenPredeterminadaServicio(ImagenPredeterminadaRepository imagenPredeterminadaRepository) {
        this.imagenPredeterminadaRepository = imagenPredeterminadaRepository;
    }

    public ImagenPredeterminada guardarImagen(MultipartFile archivo) throws MiExcepcion {
        if (archivo == null) {
            throw new MiExcepcion("No se ha enviado ningún archivo");
        }
        ImagenPredeterminada imagen = new ImagenPredeterminada();
        imagen.setNombre(archivo.getName());
        imagen.setMime(archivo.getContentType());
        try {
            imagen.setContenido(archivo.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imagenPredeterminadaRepository.save(imagen);
    }

    @Transactional
    public ImagenPredeterminada ActualizarImagen(MultipartFile archivo, Long id) throws MiExcepcion {
        if (archivo == null) {
            throw new MiExcepcion("No se ha enviado ningún archivo");
        }
        if (id == null) {
            throw new MiExcepcion("No se ha enviado ningún id");
        }

        Optional<ImagenPredeterminada> imagenOptional = imagenPredeterminadaRepository.findById(id);
        if (imagenOptional.isPresent()) {
            ImagenPredeterminada imagen = imagenOptional.get();
            imagen.setNombre(archivo.getOriginalFilename());
            imagen.setMime(archivo.getContentType());
            try {
                imagen.setContenido(archivo.getBytes());
                return imagenPredeterminadaRepository.save(imagen);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            ImagenPredeterminada nuevaImagen = new ImagenPredeterminada();
            nuevaImagen.setId(id);
            nuevaImagen.setNombre(archivo.getOriginalFilename());
            nuevaImagen.setMime(archivo.getContentType());
            try {
                nuevaImagen.setContenido(archivo.getBytes());
                return imagenPredeterminadaRepository.save(nuevaImagen);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ImagenPredeterminada obtenerImagenPredeterminada() {
        Optional<ImagenPredeterminada> imagenPredeterminadaOptional = imagenPredeterminadaRepository.findById(1L);
        return imagenPredeterminadaOptional.orElse(null);
    }
}
