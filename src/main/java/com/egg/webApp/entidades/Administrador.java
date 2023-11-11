
package com.egg.webApp.entidades;

import com.egg.webApp.enumeraciones.Rol;

import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario{
    public static void darBajaUsuario(Usuario usuario)   {
        usuario.setActivo(false);
    }

    public static void darAltaUsuario(Usuario usuario) {
        usuario.setActivo(true);
    }

    public static void establecerROlUsuario(Usuario usuario, String rol) {
        usuario.setRol(Rol.valueOf(rol));
    }
    
}

