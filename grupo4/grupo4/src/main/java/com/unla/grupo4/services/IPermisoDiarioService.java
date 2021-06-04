package com.unla.grupo4.services;

import java.time.LocalDate;
import java.util.List;

import com.unla.grupo4.entities.PermisoDiario;
import com.unla.grupo4.models.PermisoDiarioModel;

public interface IPermisoDiarioService {
	public PermisoDiarioModel insertOrUpdate(PermisoDiarioModel permisoDiarioModel);
	public abstract List<PermisoDiario> findByFechaAFecha(LocalDate fechaInicio, LocalDate fechaFinal);
}
