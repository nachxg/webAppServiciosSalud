package com.egg.webApp.entidades;

import com.egg.webApp.enumeraciones.Rol;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Administradores")

public class Administrador extends Usuario implements Serializable {

    public static void darBajaUsuario(Usuario usuario) {
        usuario.setActivo(false);
    }

    public static void darAltaUsuario(Usuario usuario) {
        usuario.setActivo(true);
    }

    public static void establecerROlUsuario(Usuario usuario, String rol) {
        usuario.setRol(Rol.valueOf(rol));
    }
}
