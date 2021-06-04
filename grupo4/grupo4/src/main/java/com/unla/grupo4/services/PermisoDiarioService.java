package com.unla.grupo4.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo4.converters.PermisoConverter;
import com.unla.grupo4.entities.PermisoDiario;
import com.unla.grupo4.models.PermisoDiarioModel;
import com.unla.grupo4.repositories.IPermisoDiarioRepository;
import com.unla.grupo4.services.IPermisoDiarioService;

@Service("permisoDiarioService")
public class PermisoDiarioService implements IPermisoDiarioService{

	@Autowired
	@Qualifier("permisoDiarioRepository")
	private IPermisoDiarioRepository permisoDiarioRepository;
	
	@Autowired
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;
	
	@Override
	public PermisoDiarioModel insertOrUpdate(PermisoDiarioModel permisoDiarioModel) {
		PermisoDiario permisoDiario = permisoDiarioRepository.save(permisoConverter.modelToEntity(permisoDiarioModel));
		return permisoConverter.entityToModel(permisoDiario);
	}

	@Override
	public List<PermisoDiario> findByFechaAFecha(LocalDate fechaInicio, LocalDate fechaFinal) {
		return permisoDiarioRepository.findByFechaAFecha(fechaInicio, fechaFinal);
	}
	
}
