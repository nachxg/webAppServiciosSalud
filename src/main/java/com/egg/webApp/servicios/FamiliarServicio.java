package com.egg.webApp.servicios;

import com.egg.webApp.entidades.GrupoFamiliar;
import com.egg.webApp.entidades.Paciente;
import com.egg.webApp.entidades.Usuario;
import com.egg.webApp.repositorios.FamiliarRepositorio;
import com.egg.webApp.repositorios.PacienteRepositorio;
import com.egg.webApp.repositorios.UsuarioRepositorio;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FamiliarServicio {

    @Autowired
    private FamiliarRepositorio familiarRepositorio;

    @Autowired
    private PacienteRepositorio pacienteRepositorio;
    
    @Autowired
    private PacienteServicio pacienteServicio;
    
     @Autowired
     private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void registrarMiembro(Paciente miembro, Paciente heredero, String parentesco) throws Exception{

        try {
            GrupoFamiliar familiar = new GrupoFamiliar();
            familiar.setId(heredero.getId());
            familiar.setParentesco(parentesco);
            familiar.setPacienteTitular(miembro);
            familiarRepositorio.save(familiar);
            miembro.setGrupoFamiliar(familiar);
        } catch (Exception e) {
            throw new Exception("Error" + e.getMessage());
        }
    }
    @Transactional
    public GrupoFamiliar buscarPorId(Long id){
        return familiarRepositorio.buscarPorId(id);
    }
    
    public List<Usuario> listarFamiliares(Long idTitular) {
        List<Usuario> familiares = new ArrayList();
        familiares = usuarioRepositorio.buscarPorIdtitular(idTitular);
        return familiares;
    }
    
    public LocalDate convertirStringALocalDate(String fechaNacimiento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(fechaNacimiento, formatter);
    }
}
