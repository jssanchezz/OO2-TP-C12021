package com.unla.grupo4.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.grupo4.entities.PermisoDiario;

@Repository("permisoDiarioRepository")
public interface IPermisoDiarioRepository extends JpaRepository<PermisoDiario, Serializable>{
	public abstract PermisoDiario findById(int id);
	
	@Query("SELECT pd FROM PermisoDiario pd JOIN FETCH pd.person pe "
			+ "JOIN FETCH pd.desdeHasta dh WHERE pe.id = :id")
	public abstract List<PermisoDiario> traerPermisosPorPersona(int id);
}
