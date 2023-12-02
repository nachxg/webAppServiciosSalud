package com.egg.webApp.repositorios;

import com.egg.webApp.entidades.Turno;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnoRepositorio extends JpaRepository<Turno, Long>, JpaSpecificationExecutor<Turno> {
	List<Turno> findAll(Specification<Turno> spec);
}
