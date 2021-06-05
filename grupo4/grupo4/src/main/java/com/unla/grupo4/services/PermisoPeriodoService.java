package com.unla.grupo4.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo4.converters.PermisoConverter;
import com.unla.grupo4.entities.Lugar;
import com.unla.grupo4.entities.Permiso;
import com.unla.grupo4.entities.PermisoPeriodo;
import com.unla.grupo4.models.PermisoPeriodoModel;
import com.unla.grupo4.repositories.IPermisoPeriodoRepository;
import com.unla.grupo4.repositories.IPersonRepository;
import com.unla.grupo4.services.IPermisoPeriodoService;

@Service("permisoPeriodoService")
public class PermisoPeriodoService implements IPermisoPeriodoService{

	@Autowired
	@Qualifier("permisoPeriodoRepository")
	private IPermisoPeriodoRepository permisoPeriodoRepository;
	
	@Autowired
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;
	
	@Override
	public PermisoPeriodoModel insertOrUpdate(PermisoPeriodoModel permisoPeriodoModel) {
		PermisoPeriodo permisoPeriodo = permisoPeriodoRepository.save(permisoConverter.modelToEntity(permisoPeriodoModel));
		return permisoConverter.entityToModel(permisoPeriodo);
	}
	

	@Override
	public List<PermisoPeriodo> findPermisosxRodado(int id) {
		return permisoPeriodoRepository.findPermisosxRodado(id);
	}


	@Override
	public List<PermisoPeriodo> findByFechaAFecha(LocalDate fechaInicio, LocalDate fechaFinal) {
		return permisoPeriodoRepository.findByFechaAFecha(fechaInicio, fechaFinal);
	}
	
	@Override
	public List<PermisoPeriodo> findByFechaAFechaAndFetchDesde(LocalDate fechaInicio, LocalDate fechaFinal, int idLugar) {
		List<PermisoPeriodo> permisosPeriodo = permisoPeriodoRepository.findByFechaAFecha(fechaInicio, fechaFinal);
		List<PermisoPeriodo> permisosPeriodoADevolver = new ArrayList<PermisoPeriodo>();
		int i=0;
		Lugar firstLugar = new Lugar();
		for(i=0;i<permisosPeriodo.size();i++) {
			//CON ESTO ENCUENTRO EL PRIMER ELEMENTO DEL SET
			firstLugar = permisosPeriodo.get(i).getDesdeHasta().iterator().next();
			//SI TIENEN EL MISMO ID LO AGREGO A LA LISTA QUE SE DEVOLVERÁ
			if(firstLugar.equals(idLugar)) {
				PermisoPeriodo pp = permisosPeriodo.get(i);
				permisosPeriodoADevolver.add(pp);
			}
		}
		return permisosPeriodoADevolver;
	}


	@Override
	public List<PermisoPeriodo> findByFechaAFechaAndFetchHasta(LocalDate fechaInicio, LocalDate fechaFinal, int idLugar) {
		List<PermisoPeriodo> permisosPeriodo = permisoPeriodoRepository.findByFechaAFecha(fechaInicio, fechaFinal);
		List<PermisoPeriodo> permisosPeriodoADevolver = new ArrayList<PermisoPeriodo>();
		int i=0;
		Lugar lastLugar = new Lugar();
		
		for(i=0;i<permisosPeriodo.size();i++) {
			//TRAIGO EL SET DEL PERMISOPERIODO
			Set<Lugar> desdeHasta = permisosPeriodo.get(i).getDesdeHasta();
			//DECLARO UN ITERADOR
			Iterator<Lugar> iterator = desdeHasta.iterator();
			
			//CON ESTE WHILE, CONSIGO EL ULTIMO ELEMENTO DEL SET
			while(iterator.hasNext()) {
				lastLugar = iterator.next();
			}
			//SI TIENE EL MISMO ID, SE LE AGREGA A LA LISTA QUE SE DEVOLVERÁ
			if(lastLugar.equals(idLugar)) {
				PermisoPeriodo pp = permisosPeriodo.get(i);
				permisosPeriodoADevolver.add(pp);
			}
		}
		return permisosPeriodoADevolver;
	}
	
	
}
