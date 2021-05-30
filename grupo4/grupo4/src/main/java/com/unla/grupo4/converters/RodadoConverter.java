package com.unla.grupo4.converters;

import org.springframework.stereotype.Component;

import com.unla.grupo4.entities.Rodado;
import com.unla.grupo4.models.RodadoModel;

@Component("rodadoConverter")
public class RodadoConverter {
	
	public RodadoModel entityToModel(Rodado rodado) {
		return new RodadoModel(rodado.getId(), rodado.getDominio(), rodado.getVehiculo());
	}
	
	public Rodado modelToEntity(RodadoModel rodadoModel) {
		return new Rodado(rodadoModel.getId(), rodadoModel.getDominio(), rodadoModel.getVehiculo());
	}
}
