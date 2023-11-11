package com.egg.webApp.entidades;

import com.egg.webApp.enumeraciones.Especialidad;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profesionales")
public class Profesional extends Usuario {
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @OneToMany(mappedBy = "profesional")
    private List<Turno> turnosDisponibles;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
   private String matricula;
   private List<Calificacion> calificaciones;

    public Profesional() {
        super();
    }


}
