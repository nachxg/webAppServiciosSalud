package com.egg.webApp.entidades;

import com.egg.webApp.enumeraciones.Rol;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String dni;
    private String password;
    private String email;
    private boolean activo;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    private LocalDate fechaAlta;
    private LocalDate fechaNacimiento;

    private String telefono;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    protected Paciente paciente;

    @OneToOne
    @JoinColumn(name = "imagen_id")
    protected Imagen imagen;

}
