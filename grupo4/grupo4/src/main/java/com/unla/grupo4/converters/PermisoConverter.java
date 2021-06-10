package com.unla.grupo4.converters;

import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.unla.grupo4.entities.Lugar;
import com.unla.grupo4.entities.PermisoDiario;
import com.unla.grupo4.entities.PermisoPeriodo;
import com.unla.grupo4.models.PermisoDiarioModel;
import com.unla.grupo4.models.PermisoPeriodoModel;

@Component("permisoConverter")
public class PermisoConverter {
	
	public PermisoDiarioModel entityToModel(PermisoDiario permisoDiario) {
		return new PermisoDiarioModel(permisoDiario.getId(), permisoDiario.getFecha(),
				permisoDiario.getPerson(), permisoDiario.getDesdeHasta(), permisoDiario.getMotivo());
	}
	
	public PermisoDiario modelToEntity(PermisoDiarioModel permisoDiarioModel) {
		return new PermisoDiario(permisoDiarioModel.getId(), permisoDiarioModel.getFecha(),
				permisoDiarioModel.getPerson(), permisoDiarioModel.getDesdeHasta(), permisoDiarioModel.getMotivo());
	}
	
	public PermisoPeriodoModel entityToModel(PermisoPeriodo permisoPeriodo) {
		return new PermisoPeriodoModel(permisoPeriodo.getId(), permisoPeriodo.getFecha(),
				permisoPeriodo.getPerson(), permisoPeriodo.getDesdeHasta(), permisoPeriodo.getCantDias(),
				permisoPeriodo.isVacaciones(), permisoPeriodo.getRodado());
	}
	
	public PermisoPeriodo modelToEntity(PermisoPeriodoModel permisoPeriodoModel) {
		return new PermisoPeriodo(permisoPeriodoModel.getId(), permisoPeriodoModel.getFecha(),
				permisoPeriodoModel.getPerson(), permisoPeriodoModel.getDesdeHasta(), permisoPeriodoModel.getCantDias(),
				permisoPeriodoModel.isVacaciones(), permisoPeriodoModel.getRodado());
	}
}
