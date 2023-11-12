package com.egg.webApp.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Administradores")
public class Administrador extends Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

package com.egg.webApp.entidades;



import com.egg.webApp.enumeraciones.Rol;
public class Administrador extends Usuario {

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
