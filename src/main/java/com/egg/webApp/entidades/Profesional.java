package com.egg.webApp.entidades;

import com.egg.webApp.enumeraciones.Especialidad;
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
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Profesional extends Usuario {

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @OneToMany(mappedBy = "profesional")
    private List<Turno> turnosDisponibles;

    private String matricula;

    @OneToMany(mappedBy = "profesional")
    private List<Calificacion> calificaciones;


}