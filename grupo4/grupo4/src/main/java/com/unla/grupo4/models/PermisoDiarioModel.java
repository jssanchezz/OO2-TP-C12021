package com.unla.grupo4.models;

import java.time.LocalDate;
import java.util.Set;

import com.unla.grupo4.entities.Lugar;
import com.unla.grupo4.entities.Person;

public class PermisoDiarioModel extends PermisoModel{
	private String motivo;

	public PermisoDiarioModel() {}
	
	public PermisoDiarioModel(int id, LocalDate fecha, Person person, Set<Lugar> desdeHasta, String motivo) {
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
