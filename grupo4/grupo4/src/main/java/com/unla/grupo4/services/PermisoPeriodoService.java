package com.unla.grupo4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo4.converters.PermisoConverter;
import com.unla.grupo4.entities.PermisoPeriodo;
import com.unla.grupo4.models.PermisoPeriodoModel;
import com.unla.grupo4.repositories.IPermisoPeriodoRepository;
import com.unla.grupo4.services.IPermisoPeriodoService;

@Service("permisoPeriodoService")
public class PermisoPeriodoService implements IPermisoPeriodoService{

	@Autowired
	@Qualifier("permisoPeriodoRepository")
	private IPermisoPeriodoRepository permisoPeriodoRepository;
	
	@Autowired
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;
	
	@Override
	public PermisoPeriodoModel insertOrUpdate(PermisoPeriodoModel permisoPeriodoModel) {
		PermisoPeriodo permisoPeriodo = permisoPeriodoRepository.save(permisoConverter.modelToEntity(permisoPeriodoModel));
		return permisoConverter.entityToModel(permisoPeriodo);
	}

}
