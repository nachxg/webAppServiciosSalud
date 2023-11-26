package com.egg.webApp.servicios;
import com.egg.webApp.entidades.Administrador;
import com.egg.webApp.entidades.Usuario;
import com.egg.webApp.excepciones.MiExcepcion;
import com.egg.webApp.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdministradorServicio {
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public void desactivarActivarUsuario(Long id) throws MiExcepcion {
        if (id == null) {
            throw new MiExcepcion("No se puede desactivar usuario: el ID no puede ser nulo");
        }
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
        assert usuario != null;
        if (usuario.isAltaSistema()) {
            Administrador.darBajaUsuario(usuario);
            usuarioRepositorio.save(usuario);
        } else {
            Administrador.darAltaUsuario(usuario);
            usuarioRepositorio.save(usuario);
        }
    }

    @Transactional
    public void establecerRolUsuario(Long id, String rol) throws MiExcepcion {
        if (id == null) {
            throw new MiExcepcion("No se puede establecer rol: el ID no puede ser nulo");
        }
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
        assert usuario != null;
        Administrador.establecerROlUsuario(usuario, rol);
        usuarioRepositorio.save(usuario);
    }
}