
package com.egg.webApp.entidades;

import java.time.LocalDateTime;
import javax.persistence.*;

import com.egg.webApp.enumeraciones.Especialidad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_turno")
    private LocalDateTime fechaTurno;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "profesional_id")
    private Profesional profesional;
}