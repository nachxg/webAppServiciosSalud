package com.egg.webApp.repositorios;

import com.egg.webApp.entidades.GrupoFamiliar;
import com.egg.webApp.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliarRepositorio extends JpaRepository<GrupoFamiliar, Long> {
    @Query("SELECT p FROM GrupoFamiliar p WHERE p.id = :id")
    public GrupoFamiliar buscarPorId(@Param("id")Long id);
    
    
}
