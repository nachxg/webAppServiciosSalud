package com.egg.webApp.servicios;

import com.egg.webApp.enumeraciones.Rol;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RolServicio {
    public List<Rol> obtenerRoles() {
        return Arrays.asList(Rol.values());
    }

}
