package com.egg.webApp.entidades;

import com.egg.webApp.enumeraciones.Rol;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;
    private String password;
    private String email;
    private boolean activo;
    private Rol rol;
    private LocalDate fechaAlta;
    private LocalDate fechaNacimiento;
    @OneToOne
    private Imagen imagen;
    private String telefono;
    private String nombre;
    private String apellido;
    private String sexo;

    @ElementCollection
    private List<Integer> calificaciones;
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Paciente paciente;
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Profesional profesional;

}
