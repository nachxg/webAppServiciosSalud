package com.egg.webApp.servicios;

import com.egg.webApp.entidades.Administrador;
import com.egg.webApp.entidades.Usuario;
import com.egg.webApp.excepciones.MiExcepcion;
import com.egg.webApp.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServicio {
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

<<<<<<< HEAD
    public void desactivarActivarUsuario(Long id) throws MiExcepcion {
=======
    public void desactivarUsuario(Long id) throws Exception {
>>>>>>> 7fd26438f8bb3d85327f493083382d6c2ca0a3e4
        if (id == null) {
            throw new MiExcepcion("No se puede desactivar usuario: el ID no puede ser nulo");
        }
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
        assert usuario != null;
        if (usuario.isActivo()) {
            Administrador.darBajaUsuario(usuario);
            usuarioRepositorio.save(usuario);
        } else {
            Administrador.darAltaUsuario(usuario);
            usuarioRepositorio.save(usuario);
        }
    }
<<<<<<< HEAD

public void establecerRolUsuario(Long id, String rol) throws MiExcepcion {
        if (id == null) {
            throw new MiExcepcion("No se puede establecer rol: el ID no puede ser nulo");
        }
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
        assert usuario != null;
        Administrador.establecerROlUsuario(usuario, rol);
        usuarioRepositorio.save(usuario);
    }
=======
>>>>>>> 7fd26438f8bb3d85327f493083382d6c2ca0a3e4
}