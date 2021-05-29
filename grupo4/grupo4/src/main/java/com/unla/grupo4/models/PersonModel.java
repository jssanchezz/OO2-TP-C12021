package com.unla.grupo4.models;

public class PersonModel {
	
	private int idPerson;
	private String name;
	private String surname;
	private long dni;
	
	public PersonModel() {
		super();
	}

	public PersonModel(int idPerson, String name, String surname, long dni) {
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
		return this.idPerson == ((PersonModel)obj).getIdPerson();
	}

	@Override
	public String toString() {
		return "Person [idPerson=" + idPerson + ", name=" + name + ", surname=" + surname + ", dni=" + dni + "]";
	}
	
}
