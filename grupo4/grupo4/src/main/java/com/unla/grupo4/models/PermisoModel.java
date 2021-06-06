package com.unla.grupo4.models;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.unla.grupo4.entities.Lugar;
import com.unla.grupo4.entities.Person;

public class PermisoModel {
	protected int id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected LocalDate fecha;
	
	protected Person person;
	protected Set<Lugar> desdeHasta;
	
	protected PermisoModel() {}

	protected PermisoModel(int id, LocalDate fecha, Person person, Set<Lugar> desdeHasta) {
		this.id = id;
		this.fecha = fecha;
		this.person = person;
		this.desdeHasta = desdeHasta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Set<Lugar> getDesdeHasta() {
		return desdeHasta;
	}

	public void setDesdeHasta(Set<Lugar> desdeHasta) {
		this.desdeHasta = desdeHasta;
	}
	
	
}
