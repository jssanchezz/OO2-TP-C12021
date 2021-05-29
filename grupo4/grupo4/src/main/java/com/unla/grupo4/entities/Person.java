package com.unla.grupo4.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Person")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPerson;
	
	@Column(name = "name", length = 45, nullable = false)
	private String name;
	
	@Column(name = "surname", length = 45, nullable = false)
	private String surname;
	
	@Column(name = "dni", length = 8, nullable = false)
	private long dni;
	
	public Person() {
		super();
	}

	public Person(int idPerson, String name, String surname, long dni) {
		super();
		this.idPerson = idPerson;
		this.name = name;
		this.surname = surname;
		this.dni = dni;
	}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public long getDni() {
		return dni;
	}

	public void setDni(long dni) {
		this.dni = dni;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPerson;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.idPerson == ((Person)obj).getIdPerson();
	}

	@Override
	public String toString() {
		return "Person [idPerson=" + idPerson + ", name=" + name + ", surname=" + surname + ", dni=" + dni + "]";
	}
	
}
