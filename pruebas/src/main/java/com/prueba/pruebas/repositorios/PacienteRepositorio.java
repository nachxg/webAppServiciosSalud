/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.pruebas.repositorios;

import com.prueba.pruebas.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Nacho
 */
public interface PacienteRepositorio extends JpaRepository<Paciente,Integer>{
    
}
