package com.egg.webApp.entidades;

import com.egg.webApp.enumeraciones.Especialidad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profesionales")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Profesional extends Usuario {

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    private String matricula;
    @OneToMany(mappedBy = "profesional", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Turno> turnosDisponibles;

    @OneToMany(mappedBy = "profesional", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Calificacion> calificaciones;

}