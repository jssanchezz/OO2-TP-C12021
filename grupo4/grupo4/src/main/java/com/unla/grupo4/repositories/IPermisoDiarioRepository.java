package com.unla.grupo4.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo4.entities.PermisoDiario;
import com.unla.grupo4.entities.Person;

@Repository("permisoDiarioRepository")
public interface IPermisoDiarioRepository extends JpaRepository<PermisoDiario, Serializable>{
	public abstract PermisoDiario findById(int id);
	public abstract PermisoDiario findByPerson(Person person);
	
	@Query("SELECT pd FROM PermisoDiario pd JOIN FETCH pd.person pe WHERE pe.id = :id")
	public abstract List<PermisoDiario> traerPermisosPorPersona(int id);
	
	@Query("SELECT pd FROM PermisoDiario pd JOIN FETCH pd.person pe WHERE pd.fecha BETWEEN (:fechaInicio) and (:fechaFinal)")
	public abstract List<PermisoDiario> findByFechaAFecha(@Param("fechaInicio")LocalDate fechaInicio,
														  @Param("fechaFinal")LocalDate fechaFinal);
}
