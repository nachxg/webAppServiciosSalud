package com.egg.webApp.servicios;

import com.egg.webApp.entidades.Turno;
import com.egg.webApp.enumeraciones.Especialidad;
import com.egg.webApp.especipicacion.BuscarTurnoEspecificado;
import com.egg.webApp.repositorios.TurnoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TurnoServicio {

	@Autowired
	private TurnoRepositorio turnoRepositorio;

	public List<Turno> buscarTurnos(String motivoConsulta, LocalDateTime fechaTurno, Especialidad especialidad, Boolean atendido, Boolean cancelado) {
		Specification<Turno> spec = BuscarTurnoEspecificado.buscarTurno(motivoConsulta, fechaTurno, especialidad, atendido, cancelado);
		return turnoRepositorio.findAll(spec);
	}
}
