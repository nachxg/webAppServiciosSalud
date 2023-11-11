package com.egg.webApp.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profesionales")
public class Profesional extends Usuario {
    private String matricula;

    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL)
    private List<Turno> turnosDisponibles;

    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL)
    private List<Calificacion> calificaciones;

    @OneToOne
    @JoinColumn(name = "id")
    private Usuario usuario;
}