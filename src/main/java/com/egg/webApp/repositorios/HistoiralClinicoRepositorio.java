package com.egg.webApp.repositorios;

import com.egg.webApp.entidades.HistoriaClinica;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoiralClinicoRepositorio extends JpaRepository<HistoriaClinica, Long> {
    @Query("SELECT h FROM historias_clinicas h  WHERE h.fecha_visita = :fecha")
    public List<HistoriaClinica> buscarListaPorFecha(@Param("fecha") LocalDateTime fecha);
    
    @Query("SELECT h FROM historias_clinicas h  WHERE h.fecha_visita = :fecha")
    public List<HistoriaClinica> buscarListaPorFecha(@Param("fecha") LocalDateTime fecha);
}
