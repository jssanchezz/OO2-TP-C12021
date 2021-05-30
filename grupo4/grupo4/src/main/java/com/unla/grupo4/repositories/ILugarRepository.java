package com.unla.grupo4.repositories;

import org.springframework.stereotype.Repository;

import com.unla.grupo4.entities.Lugar;

@Repository("lugarRepository")
public interface ILugarRepository {
	public abstract Lugar findById(int id);
}
