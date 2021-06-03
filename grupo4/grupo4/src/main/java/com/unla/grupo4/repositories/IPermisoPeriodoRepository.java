package com.unla.grupo4.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo4.entities.Permiso;
import com.unla.grupo4.entities.PermisoPeriodo;

@Repository("permisoPeriodoRepository")
public interface IPermisoPeriodoRepository extends JpaRepository<PermisoPeriodo, Serializable>{
	public abstract PermisoPeriodo findById(int id);
	
	@Query("SELECT pe FROM PermisoPeriodo pe JOIN FETCH pe.rodado ro WHERE ro.id = :id")//  
	public abstract List<PermisoPeriodo> findPermisosxRodado(int id);	
	
}
