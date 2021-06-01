package com.unla.grupo4.services;

import java.util.List;

import com.unla.grupo4.entities.Lugar;
import com.unla.grupo4.models.LugarModel;

public interface ILugarService {
	public List<Lugar> getAll();
	public LugarModel insertOrUpdate(LugarModel lugarModel);
}
