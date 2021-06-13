package com.unla.grupo4.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo4.entities.PermisoPeriodo;
import com.unla.grupo4.entities.Person;

@Repository("permisoPeriodoRepository")
public interface IPermisoPeriodoRepository extends JpaRepository<PermisoPeriodo, Serializable>{
	public abstract PermisoPeriodo findById(int id);
	public abstract List<PermisoPeriodo> findAll();
	
	@Query("SELECT pp FROM PermisoPeriodo pp JOIN FETCH pp.person pe "
			+ "JOIN FETCH pp.rodado pr WHERE pr.dominio LIKE (:dominio)")
	public abstract List<PermisoPeriodo> findPermisosxRodado(String dominio);	
	
	@Query("SELECT pp FROM PermisoPeriodo pp JOIN FETCH pp.person pe JOIN FETCH pp.rodado pr "
			+ "JOIN FETCH pp.desdeHasta dh")
	public abstract List<PermisoPeriodo> findAllPermisosPeriodo();
	
	@Query("SELECT pp FROM PermisoPeriodo pp JOIN FETCH pp.person pe JOIN FETCH pp.rodado ro WHERE pe.id = :id")
	public abstract List<PermisoPeriodo> traerPermisosPorPersona(int id);
	
	@Query("SELECT pp FROM PermisoPeriodo pp JOIN FETCH pp.person pe JOIN FETCH pp.rodado pr "
			+ "WHERE pp.fecha BETWEEN (:fechaInicio) and (:fechaFinal)")
	public abstract List<PermisoPeriodo> findByFechaAFecha(@Param("fechaInicio")LocalDate fechaInicio,
														  @Param("fechaFinal")LocalDate fechaFinal);
}
