package com.unla.grupo4.converters;

import org.springframework.stereotype.Component;

import com.unla.grupo4.entities.Lugar;
import com.unla.grupo4.models.LugarModel;

@Component("lugarConverter")
public class LugarConverter {
	public LugarModel entityToModel(Lugar lugar) {
		return new LugarModel(lugar.getId(), lugar.getLugar(), lugar.getCodigoPostal());
	}
	
	public Lugar modelToEntity(LugarModel lugarModel) {
		return new Lugar(lugarModel.getId(), lugarModel.getLugar(), lugarModel.getCodigoPostal());
	}
}
