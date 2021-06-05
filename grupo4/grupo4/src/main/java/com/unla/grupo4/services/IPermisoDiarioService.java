package com.unla.grupo4.services;

import java.time.LocalDate;
import java.util.List;

import com.unla.grupo4.entities.PermisoDiario;
import com.unla.grupo4.entities.Person;
import com.unla.grupo4.models.PermisoDiarioModel;

public interface IPermisoDiarioService {
	public PermisoDiarioModel insertOrUpdate(PermisoDiarioModel permisoDiarioModel);
	public abstract PermisoDiario findById(int id);
	public abstract PermisoDiario findByPerson(Person person);
	public abstract List<PermisoDiario> findByFechaAFecha(LocalDate fechaInicio, LocalDate fechaFinal);
	public abstract List<PermisoDiario> findByFechaAFechaAndFetchDesde(LocalDate fechaInicio, 
			LocalDate fechaFinal, int lugar);
	public abstract List<PermisoDiario> findByFechaAFechaAndFetchHasta(LocalDate fechaInicio, 
			LocalDate fechaFinal, int idLugar);
}
