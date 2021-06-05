package com.unla.grupo4.services;

import java.util.List;

import com.unla.grupo4.entities.Person;
import com.unla.grupo4.models.PersonModel;

public interface IPersonService {
	
	public List<Person> getAll();
	
	public PersonModel findById(int id);
	
	public Person findByDni(long dni);
	
	public PersonModel insertOrUpdate(PersonModel personModel);
	
}
