package com.egg.webApp.servicios;

import com.egg.webApp.entidades.Administrador;
import com.egg.webApp.entidades.Usuario;
import com.egg.webApp.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServicio {
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    /*
    public void desactivarUsuario(Long id) throws Exception {
        if (id == null) {
            throw new Exception("No se puede desactivar usuario: el ID no puede ser nulo");
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
*/


}