package com.unla.grupo4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo4.converters.RodadoConverter;
import com.unla.grupo4.entities.Rodado;
import com.unla.grupo4.entities.User;
import com.unla.grupo4.models.RodadoModel;
import com.unla.grupo4.repositories.IRodadoRepository;

@Service("rodadoService")
public class RodadoService implements IRodadoService{
	
	@Autowired
	@Qualifier("rodadoRepository")
	public IRodadoRepository rodadoRepository;
	
	@Autowired
	@Qualifier("rodadoConverter")
	public RodadoConverter rodadoConverter;

	@Override
	public List<Rodado> getAll() {
		return rodadoRepository.findAll();
	}

	@Override
	public RodadoModel findById(int id) {
		return rodadoConverter.entityToModel(rodadoRepository.findById(id));
	}

	@Override
	public RodadoModel findByDominio(String dominio) {
		return rodadoConverter.entityToModel(rodadoRepository.findByDominio(dominio));
	}

	@Override
	public RodadoModel insertOrUpdate(RodadoModel rodadoModel) {
		Rodado rodado = rodadoRepository.save(rodadoConverter.modelToEntity(rodadoModel));
		return rodadoConverter.entityToModel(rodado);
	}
}
