package com.egg.webApp.servicios;

import com.egg.webApp.entidades.GrupoFamiliar;
import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.enumeraciones.Vinculo;
import com.egg.webApp.repositorios.FamiliarRepositorio;
import com.egg.webApp.repositorios.PacienteRepositorio;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class FamiliarServicio {
    private final FamiliarRepositorio familiarRepositorio;
    private final PacienteRepositorio pacienteRepositorio;
    private final PacienteServicio pacienteServicio;

    public FamiliarServicio(FamiliarRepositorio familiarRepositorio, PacienteRepositorio pacienteRepositorio, PacienteServicio pacienteServicio) {
        this.familiarRepositorio = familiarRepositorio;
        this.pacienteRepositorio = pacienteRepositorio;
        this.pacienteServicio = pacienteServicio;
    }

    @Transactional
    public void registrarMiembro(GrupoFamiliar familiar, Paciente miembro,
                                 String parentesco, String nombre, String apellido,
                                 String dni, String password, String password2,
                                 String sexo, LocalDate fechaNacimiento) throws Exception{
        try{
            if (familiar == null){
                throw new Exception("El familiar no puede ser nulo");
            }

            if (familiar.getMiembros() == null){
                familiar.setMiembros(new ArrayList<>());
            }
            pacienteServicio.registrarPaciente(nombre, apellido, dni, password, password2, sexo, fechaNacimiento);
            miembro.setGrupoFamiliar(familiar);
            familiar.setParentesco(Vinculo.valueOf(parentesco));
            familiar.getMiembros().add(miembro);
            familiarRepositorio.save(familiar);
            pacienteRepositorio.save(miembro);
        }catch (Exception e){
            throw new Exception("Error" + e.getMessage());
        }
    }
}