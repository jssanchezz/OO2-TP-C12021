package com.unla.grupo4.services;

import java.util.List;

import com.unla.grupo4.entities.Rodado;
import com.unla.grupo4.models.RodadoModel;

public interface IRodadoService {
	
	public List<Rodado> getAll();
	
	public RodadoModel findById(int id);
	
	public Rodado findByDominio(String dominio);
	
	public RodadoModel insertOrUpdate(RodadoModel rodadoModel);
	
	public boolean esValida(String dominio);
}
