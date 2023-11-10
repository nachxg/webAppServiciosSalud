package com.egg.webApp.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pacientes")
public class Paciente extends Usuario {
    private String numeroObraSocial;
    @OneToMany(mappedBy = "paciente")
    private List<Turno> turnos;
    @OneToMany(mappedBy = "paciente")
    private List<HistoriaClinica> historiasClinicas;
    private List<Paciente> grupoFamiliar;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
