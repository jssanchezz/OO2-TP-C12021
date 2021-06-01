package com.unla.grupo4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo4.converters.LugarConverter;
import com.unla.grupo4.entities.Lugar;
import com.unla.grupo4.models.LugarModel;
import com.unla.grupo4.repositories.ILugarRepository;

@Service
public class LugarService implements ILugarService{

	@Autowired
	@Qualifier("lugarRepository")
	private ILugarRepository lugarRepository;
	
	@Autowired
	@Qualifier("lugarConverter")
	private LugarConverter lugarConverter;
	
	@Override
	public LugarModel insertOrUpdate(LugarModel lugarModel) {
		Lugar lugar = lugarRepository.save(lugarConverter.modelToEntity(lugarModel));
		return lugarConverter.entityToModel(lugar);
	}

	@Override
	public List<Lugar> getAll() {
		return lugarRepository.findAll();
	}
	
}
