package com.unla.grupo4.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo4.entities.Permiso;
import com.unla.grupo4.entities.PermisoDiario;
import com.unla.grupo4.entities.PermisoPeriodo;

@Repository("permisoPeriodoRepository")
public interface IPermisoPeriodoRepository extends JpaRepository<PermisoPeriodo, Serializable>{
	public abstract PermisoPeriodo findById(int id);
	
	@Query("SELECT pe FROM PermisoPeriodo pe JOIN FETCH pe.rodado ro WHERE ro.id = :id")//  
	public abstract List<PermisoPeriodo> findPermisosxRodado(int id);	
	
	@Query("SELECT pp FROM PermisoPeriodo pp JOIN FETCH pp.person pe  JOIN FETCH pp.rodado pr "
			+ "JOIN FETCH pp.desdeHasta dh WHERE pp.fecha BETWEEN (:fechaInicio) and "
			+ "(:fechaFinal)")
	public abstract List<PermisoPeriodo> findByFechaAFecha(@Param("fechaInicio")LocalDate fechaInicio,
														  @Param("fechaFinal")LocalDate fechaFinal);
}
