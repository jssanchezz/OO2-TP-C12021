package com.unla.grupo4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo4.converters.PersonConverter;
import com.unla.grupo4.entities.Person;
import com.unla.grupo4.models.PersonModel;
import com.unla.grupo4.repositories.IPersonRepository;

@Service("personService")
public class PersonService implements IPersonService {

	@Autowired
	@Qualifier("personRepository")
	private IPersonRepository personRepository;

	@Autowired
	@Qualifier("personConverter")
	private PersonConverter personConverter;

	@Override
	public PersonModel findById(int id) {
		return personConverter.entityToModel(personRepository.findById(id));
	}

	@Override
	public List<Person> getAll() {
		return personRepository.findAll();
	}

	public PersonModel insertOrUpdate(PersonModel personModel) {
		Person person = personRepository.save(personConverter.modelToEntity(personModel));
		return personConverter.entityToModel(person);
	}

	@Override
	public Person findByDni(long dni) {
		return personRepository.findByDni(dni);
	}

}
