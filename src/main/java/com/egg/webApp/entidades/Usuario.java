package com.egg.webApp.entidades;

import com.egg.webApp.enumeraciones.Rol;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long dni;
    private String password;
    private String email;
    private boolean activo;
    private Rol rol;
    private LocalDate fechaAlta;
    private LocalDate fechaNacimiento;
    private Imagen imagen;
    private String telefono;
    private String nombre;
    private String apellido;
    private String sexo;

    private Paciente paciente;
    private Profesional profesional;

}
