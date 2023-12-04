package com.egg.webApp.entidades;

import com.egg.webApp.enumeraciones.Vinculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GrupoFamiliar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Vinculo parentesco;

    @ManyToOne
    @JoinColumn(name = "paciente_titular_id")
    private Paciente pacienteTitular;

    @OneToMany(mappedBy = "grupoFamiliar", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Paciente> miembros;


}

