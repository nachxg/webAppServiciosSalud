package com.egg.webApp.entidades;

import com.egg.webApp.enumeraciones.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase que representa a un usuario en la aplicación.
 * Esta clase es una entidad persistente que se mapea a la tabla "usuarios" en la base de datos.
 * Se utiliza la estrategia de herencia "JOINED" para manejar la herencia de clases.
 *
 * @version v0.1.0-alpha 07/11/2023
 * @see <a href="https://github.com/nachxg/webAppServiciosSalud/releases/tag/v0.1.0-alpha">Versión en GitHub</a>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    /**
     * Identificador único del usuario generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * Número de identificación del usuario (DNI).
     */
    protected String dni;

    /**
     * Contraseña del usuario.
     */
    protected String password;

    /**
     * Dirección de correo electrónico del usuario.
     */
    protected String email;

    /**
     * Indica si el usuario está activo o no.
     */
    protected boolean activo;

    /**
     * Rol del usuario, representado como una enumeración de tipo Rol.
     */
    @Enumerated(EnumType.STRING)
    protected Rol rol;

    /**
     * Fecha y hora en la que se registró al usuario.
     */
    protected LocalDateTime fechaAlta;

    /**
     * Fecha de nacimiento del usuario.
     */
    protected LocalDate fechaNacimiento;

    /**
     * Imagen asociada al usuario.
     */
    @OneToOne
    @JoinColumn(name = "imagen_id")
    protected Imagen imagen;

    /**
     * Número de teléfono del usuario.
     */
    protected String telefono;

    /**
     * Nombre del usuario.
     */
    protected String nombre;

    /**
     * Apellido del usuario.
     */
    protected String apellido;

    /**
     * Género del usuario.
     */
    protected String sexo;

    /**
     * Relación uno a uno con la entidad Paciente, representando al paciente asociado al usuario.
     */
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    protected Paciente paciente;

    /**
     * Relación uno a uno con la entidad Profesional, representando al profesional asociado al usuario.
     */
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    protected Profesional profesional;
}
