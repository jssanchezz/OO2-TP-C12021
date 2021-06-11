package com.unla.grupo4.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PersonModel {
	
	private int id;
	@Size(min = 8, max = 40, message = "el nombre tiene que tener entre 8 y 40 caracteres de largo")
	private String name;
	@Size(min = 5, max = 40, message = "el apellido tiene que tener entre 5 y 40 caracteres de largo")
	private String surname;
	// TODO preguntarle a los chicos : "Failed to convert property value of type java.lang.String to required type long for property dni; nested exception is java.lang.NumberFormatException: For input string: "" "
	// Es necesario que sea long, o puede ser String
	private long dni;
	
	public PersonModel() {
		super();
	}

	public PersonModel(int id, String name, String surname, long dni) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dni = dni;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.id == ((PersonModel)obj).getId();
	}

	@Override
	public String toString() {
		return "Person [idPerson=" + id + ", name=" + name + ", surname=" + surname + ", dni=" + dni + "]";
	}
	
}
