package com.unla.grupo4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo4.converters.PermisoConverter;
import com.unla.grupo4.entities.Permiso;
import com.unla.grupo4.entities.PermisoPeriodo;
import com.unla.grupo4.models.PermisoPeriodoModel;
import com.unla.grupo4.repositories.IPermisoPeriodoRepository;
import com.unla.grupo4.services.IPermisoPeriodoService;

@Service("permisoPeriodoService")
public class PermisoPeriodoService implements IPermisoPeriodoService {

	@Autowired
	@Qualifier("permisoPeriodoRepository")
	private IPermisoPeriodoRepository permisoPeriodoRepository;

	@Autowired
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;

	@Override
	public PermisoPeriodoModel insertOrUpdate(PermisoPeriodoModel permisoPeriodoModel) {
		PermisoPeriodo permisoPeriodo = permisoPeriodoRepository
				.save(permisoConverter.modelToEntity(permisoPeriodoModel));
		return permisoConverter.entityToModel(permisoPeriodo);
	}

	@Override
	public List<PermisoPeriodo> getAll() {
		return permisoPeriodoRepository.findAll();
	}
	
	

	@Override
	public List<PermisoPeriodo> findAllPermisosPeriodo() {
		return permisoPeriodoRepository.findAllPermisosPeriodo();
	}

	@Override
	public List<PermisoPeriodo> findPermisosxRodado(String dominio) {
		return permisoPeriodoRepository.findPermisosxRodado(dominio);
	}

}
