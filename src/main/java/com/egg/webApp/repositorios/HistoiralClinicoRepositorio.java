package com.egg.webApp.repositorios;

import com.egg.webApp.entidades.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoiralClinicoRepositorio extends JpaRepository<HistoriaClinica, Long> {
}
