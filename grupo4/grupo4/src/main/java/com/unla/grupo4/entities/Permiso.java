package com.unla.grupo4.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Permiso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	protected LocalDate fecha;
	
	@CreationTimestamp
	protected LocalDateTime createdAt;
	
	@UpdateTimestamp
	protected LocalDateTime updatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="person_id", nullable=false)
	protected Person person;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "permiso_lugar",
	        joinColumns = @JoinColumn(name = "fk_permiso", nullable = false),
	        inverseJoinColumns = @JoinColumn(name="fk_lugar", nullable = false)
	)
	protected Set<Lugar> desdeHasta = new HashSet<Lugar>();

	protected Permiso() {}

	protected Permiso(int id, LocalDate fecha, Person person, Set<Lugar> desdeHasta) {
		super();
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
