package com.unla.grupo4.services;

import java.time.LocalDate;
import java.util.List;

import com.unla.grupo4.entities.PermisoPeriodo;
import com.unla.grupo4.entities.Person;
import com.unla.grupo4.entities.Rodado;
import com.unla.grupo4.models.PermisoPeriodoModel;
import com.unla.grupo4.models.PersonModel;
import com.unla.grupo4.models.RodadoModel;

public interface IPermisoPeriodoService {
	public PermisoPeriodoModel insertOrUpdate(PermisoPeriodoModel permisoPeriodoModel);
	
	public abstract List<PermisoPeriodo> findPermisosxRodado(String dominio);
	
	public abstract List<PermisoPeriodo> getAll();
	
	public List<PermisoPeriodo> findAllPermisosPeriodo();
	
	public abstract List<PermisoPeriodo> traerPermisosPorPersona(int id);
	
	public abstract List<PermisoPeriodo> findByFechaAFecha(LocalDate fechaInicio, LocalDate fechaFinal);
	public abstract List<PermisoPeriodo> findByFechaAFechaAndFetchDesde(LocalDate fechaInicio, 
			LocalDate fechaFinal, int lugar);
	public abstract List<PermisoPeriodo> findByFechaAFechaAndFetchHasta(LocalDate fechaInicio, 
			LocalDate fechaFinal, int lugar);
	public abstract String modelToURL(PermisoPeriodoModel permisoPeriodoModel, RodadoModel rodado, PersonModel person);
}
