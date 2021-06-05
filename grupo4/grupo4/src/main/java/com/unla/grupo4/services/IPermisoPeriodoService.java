package com.unla.grupo4.services;

import java.time.LocalDate;
import java.util.List;

import com.unla.grupo4.entities.PermisoDiario;
import com.unla.grupo4.entities.PermisoPeriodo;
import com.unla.grupo4.entities.Person;
import com.unla.grupo4.models.PermisoPeriodoModel;

public interface IPermisoPeriodoService {
	public PermisoPeriodoModel insertOrUpdate(PermisoPeriodoModel permisoPeriodoModel);
	public abstract PermisoPeriodo findById(int id);
	public abstract PermisoPeriodo findByPerson(Person person);
	public abstract List<PermisoPeriodo> findPermisosxRodado(int id);
	public abstract List<PermisoPeriodo> findByFechaAFecha(LocalDate fechaInicio, LocalDate fechaFinal);
	public abstract List<PermisoPeriodo> findByFechaAFechaAndFetchDesde(LocalDate fechaInicio, 
			LocalDate fechaFinal, int lugar);
	public abstract List<PermisoPeriodo> findByFechaAFechaAndFetchHasta(LocalDate fechaInicio, 
			LocalDate fechaFinal, int lugar);
}
