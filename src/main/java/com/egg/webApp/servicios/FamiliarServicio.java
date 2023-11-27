package com.egg.webApp.servicios;

import com.egg.webApp.entidades.GrupoFamiliar;
import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.enumeraciones.Vinculo;
import com.egg.webApp.repositorios.FamiliarRepositorio;
import com.egg.webApp.repositorios.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class FamiliarServicio {

    @Autowired
    private FamiliarRepositorio familiarRepositorio;

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    @Transactional
    public void agregarMiembro(GrupoFamiliar familiar, Paciente miembro, String vinculo) throws Exception{

        try{
            if (familiar == null){
                throw new Exception("El familiar no puede ser nulo");
            }

            if (familiar.getMiembros() == null){
                familiar.setMiembros(new ArrayList<>());
            }

            miembro.setGrupoFamiliar(familiar);
            familiar.setVinculo(Vinculo.valueOf(vinculo));
            familiar.getMiembros().add(miembro);

            familiarRepositorio.save(familiar);
            pacienteRepositorio.save(miembro);


        }catch (Exception e){
            throw new Exception("Error" + e.getMessage());
        }

    }

}
