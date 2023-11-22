
package com.egg.webApp.entidades;

import com.egg.webApp.enumeraciones.Rol;


public class Administrador extends Usuario {

    public static void darBajaUsuario(Usuario usuario) {
        usuario.setAltaSistema(false);
    }

    public static void darAltaUsuario(Usuario usuario) {
        usuario.setAltaSistema(true);
    }

    public static void establecerROlUsuario(Usuario usuario, String rol) {
        usuario.setRol(Rol.valueOf(rol));
    }
}
