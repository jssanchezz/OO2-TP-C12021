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
import com.unla.grupo4.entities.PermisoDiario;
import com.unla.grupo4.entities.Person;
import com.unla.grupo4.models.PermisoDiarioModel;
import com.unla.grupo4.repositories.IPermisoDiarioRepository;
import com.unla.grupo4.services.IPermisoDiarioService;

@Service("permisoDiarioService")
public class PermisoDiarioService implements IPermisoDiarioService{

	@Autowired
	@Qualifier("permisoDiarioRepository")
	private IPermisoDiarioRepository permisoDiarioRepository;
	
	@Autowired
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;
	
	@Override
	public PermisoDiarioModel insertOrUpdate(PermisoDiarioModel permisoDiarioModel) {
		PermisoDiario permisoDiario = permisoDiarioRepository.save(permisoConverter.modelToEntity(permisoDiarioModel));
		return permisoConverter.entityToModel(permisoDiario);
	}

	@Override
	public List<PermisoDiario> traerPermisosPorPersona(int id) {
		return permisoDiarioRepository.traerPermisosPorPersona(id);
	}

	@Override
	public PermisoDiario findById(int id) {
		return permisoDiarioRepository.findById(id);
	}

	@Override
	public List<PermisoDiario> findByFechaAFecha(LocalDate fechaInicio, LocalDate fechaFinal) {
		return permisoDiarioRepository.findByFechaAFecha(fechaInicio, fechaFinal);
	}

	@Override
	public List<PermisoDiario> findByFechaAFechaAndFetchDesde(LocalDate fechaInicio, LocalDate fechaFinal, int idLugar) {
		List<PermisoDiario> permisosDiario = permisoDiarioRepository.findByFechaAFecha(fechaInicio, fechaFinal);
		List<PermisoDiario> permisosDiarioADevolver = new ArrayList<PermisoDiario>();
		int i=0;
		Lugar firstLugar = new Lugar();
		
		for(i=0;i<permisosDiario.size();i++) {
			//CON ESTO ENCUENTRO EL PRIMER ELEMENTO DEL SET
			firstLugar = permisosDiario.get(i).getDesdeHasta().iterator().next();
			//SI TIENEN EL MISMO NOMBRE LO AGREGO A LA LISTA QUE SE DEVOLVERÁ
			if(firstLugar.equals(idLugar)) {
				PermisoDiario pd = permisosDiario.get(i);
				permisosDiarioADevolver.add(pd);
			}
		}
		return permisosDiarioADevolver;
	}

	@Override
	public List<PermisoDiario> findByFechaAFechaAndFetchHasta(LocalDate fechaInicio, LocalDate fechaFinal,
			int idLugar) {
		List<PermisoDiario> permisosDiario = permisoDiarioRepository.findByFechaAFecha(fechaInicio, fechaFinal);
		List<PermisoDiario> permisosDiarioADevolver = new ArrayList<PermisoDiario>();
		int i=0;
		Lugar lastLugar = new Lugar();
		
		for(i=0;i<permisosDiario.size();i++) {
			//TRAIGO EL SET DEL PERMISODIARIO
			Set<Lugar> desdeHasta = permisosDiario.get(i).getDesdeHasta();
			//DECLARO UN ITERADOR
			Iterator<Lugar> iterator = desdeHasta.iterator();
			//CON ESTO CONSIGO EL ULTIMO ELEMENTO DEL SET
			while(iterator.hasNext()) {
				lastLugar = iterator.next();
			}
			//SI TIENE EL MISMO ID, SE LE AGREGA A LA LISTA QUE SE DEVOLVERÁ
			if(lastLugar.equals(idLugar)) {
				PermisoDiario pd = permisosDiario.get(i);
				permisosDiarioADevolver.add(pd);
			}
		}
		return permisosDiarioADevolver;
	}

	@Override
	public List<PermisoDiario> getAll() {
		return permisoDiarioRepository.findAll();
	}
	
}
