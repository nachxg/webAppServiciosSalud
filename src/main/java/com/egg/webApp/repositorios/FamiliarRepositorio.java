package com.egg.webApp.repositorios;

import com.egg.webApp.entidades.GrupoFamiliar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamiliarRepositorio extends JpaRepository<GrupoFamiliar, Long> {
}
