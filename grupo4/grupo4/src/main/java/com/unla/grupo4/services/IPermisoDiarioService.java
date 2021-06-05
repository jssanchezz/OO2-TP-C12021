package com.unla.grupo4.services;

import java.util.List;

import com.unla.grupo4.entities.PermisoDiario;
import com.unla.grupo4.models.PermisoDiarioModel;

public interface IPermisoDiarioService {
	public abstract PermisoDiarioModel insertOrUpdate(PermisoDiarioModel permisoDiarioModel);
	public abstract List<PermisoDiario> traerPermisosPorPersona(int id);
}
