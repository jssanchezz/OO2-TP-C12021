package com.unla.grupo4.services;

import java.util.List;

import com.unla.grupo4.entities.Permiso;
import com.unla.grupo4.entities.PermisoPeriodo;
import com.unla.grupo4.models.PermisoPeriodoModel;

public interface IPermisoPeriodoService {
	public PermisoPeriodoModel insertOrUpdate(PermisoPeriodoModel permisoPeriodoModel);
	
	public abstract List<PermisoPeriodo> findPermisosxRodado(String dominio);
	
	public List<PermisoPeriodo> getAll();
	
	public List<PermisoPeriodo> findAllPermisosPeriodo();
	
}
