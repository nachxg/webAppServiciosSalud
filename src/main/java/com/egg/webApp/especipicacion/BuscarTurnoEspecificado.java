package com.egg.webApp.especipicacion;

import com.egg.webApp.entidades.Turno;
import com.egg.webApp.enumeraciones.Especialidad;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BuscarTurnoEspecificado {
	private LocalDateTime fechaTurno;
	private Especialidad especialidad;
	private Long idPaciente;

	public BuscarTurnoEspecificado(LocalDateTime fechaTurno, Especialidad especialidad, Long idPaciente) {
		this.fechaTurno = fechaTurno;
		this.especialidad = especialidad;
		this.idPaciente = idPaciente;
	}

	public static Specification<Turno> buscarTurno(String motivoConsulta, LocalDateTime fechaTurno, Especialidad especialidad, Boolean atendido, Boolean cancelado) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (motivoConsulta != null && !motivoConsulta.isEmpty()) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("motivoConsulta")), "%" + motivoConsulta.toLowerCase() + "%"));
			}

			if (fechaTurno != null) {
				predicates.add(criteriaBuilder.equal(root.get("fechaTurno"), fechaTurno));
			}

			if (especialidad != null) {
				predicates.add(criteriaBuilder.equal(root.get("especialidad"), especialidad));
			}

			if (atendido != null) {
				predicates.add(criteriaBuilder.equal(root.get("atendido"), atendido));
			}

			if (cancelado != null) {
				predicates.add(criteriaBuilder.equal(root.get("cancelado"), cancelado));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}