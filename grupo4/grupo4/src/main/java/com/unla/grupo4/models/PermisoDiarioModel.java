package com.unla.grupo4.models;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Size;

import com.unla.grupo4.entities.Lugar;
import com.unla.grupo4.entities.Person;

public class PermisoDiarioModel extends PermisoModel{
	@Size(min = 4,max = 50, message = "el motivo debe tener entre 4 y 50 caracteres de largo")
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
