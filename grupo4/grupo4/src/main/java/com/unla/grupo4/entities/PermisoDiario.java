package com.unla.grupo4.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class PermisoDiario extends Permiso{
	private String motivo;

	public PermisoDiario() {}

	public PermisoDiario(int id, LocalDate fecha, Person person, Set<Lugar> desdeHasta, String motivo) {
		super(id, fecha, person, desdeHasta);
		this.motivo = motivo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	
}
