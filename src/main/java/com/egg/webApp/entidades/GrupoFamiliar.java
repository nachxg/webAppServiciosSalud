package com.egg.webApp.entidades;

import com.egg.webApp.enumeraciones.Vinculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GrupoFamiliar {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private Vinculo parentesco;

    @ManyToOne
    @JoinColumn(name = "paciente_titular_id")
    private Paciente pacienteTitular;

    @OneToMany(mappedBy = "grupoFamiliar", cascade = CascadeType.ALL)
    private List<Paciente> miembros;

}

