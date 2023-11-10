package com.egg.webApp.entidades;

import javax.persistence.*;

@Entity
@Table(name = "profesionales")
public class Profesional extends Usuario {
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @OneToMany(mappedBy = "profesional")
    private List<Turnos> turnosDisponibles;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
   private String matricula;
   private List<Calificacion> calificaciones;

    public Profesional() {
        super();
    }


}
