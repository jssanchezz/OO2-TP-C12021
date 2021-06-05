package com.unla.grupo4.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo4.entities.Lugar;
import com.unla.grupo4.entities.Permiso;
import com.unla.grupo4.entities.PermisoDiario;
import com.unla.grupo4.entities.PermisoPeriodo;
import com.unla.grupo4.entities.Person;
import com.unla.grupo4.helpers.ViewRouteHelper;
import com.unla.grupo4.models.LugarModel;
import com.unla.grupo4.models.PermisoDiarioModel;
import com.unla.grupo4.models.PermisoPeriodoModel;
import com.unla.grupo4.services.ILugarService;
import com.unla.grupo4.services.IPermisoDiarioService;
import com.unla.grupo4.services.IPermisoPeriodoService;
import com.unla.grupo4.services.IPersonService;
import com.unla.grupo4.services.IRodadoService;

@Controller
@RequestMapping("/permiso")
public class PermisoController {
	
	@Autowired
	@Qualifier("permisoDiarioService")
	private IPermisoDiarioService permisoDiarioService;
	
	@Autowired
	@Qualifier("permisoPeriodoService")
	private IPermisoPeriodoService permisoPeriodoService;
	
	@Autowired
	@Qualifier("lugarService")
	private ILugarService lugarService;
	
	@Autowired
	@Qualifier("personService")
	private IPersonService personService;
	
	@Autowired
	@Qualifier("rodadoService")
	private IRodadoService rodadoService;
	
	@GetMapping("/newPermisoDiario")
	public ModelAndView nuevoPermisoDiario() {
		ModelAndView mav = new ModelAndView(ViewRouteHelper.PERMISO_DIARIO_NEW);
		mav.addObject("lugares", lugarService.getAll());
		mav.addObject("personas", personService.getAll());
		mav.addObject("permisoDiario", new PermisoDiario());
		return mav;
	}
	
	@PostMapping("/permisoDiarioProcess")
	public RedirectView toNuevoPermisoDiario(@ModelAttribute("permisoDiario") PermisoDiarioModel permisoDiarioModel,
											 @RequestParam("idDesde") int idDesde, @RequestParam("idHasta") int idHasta) {
		Set<Lugar> lugares = new HashSet<Lugar>();
		lugares.add(lugarService.findById(idDesde));
		lugares.add(lugarService.findById(idHasta));
		permisoDiarioModel.setDesdeHasta(lugares);
		permisoDiarioService.insertOrUpdate(permisoDiarioModel);
		return new RedirectView(ViewRouteHelper.PERMISO_DIARIO_ROOT);
	}

	@GetMapping("/newPermisoPeriodo")
	public ModelAndView nuevoPermisoPeriodo() {
		ModelAndView mav = new ModelAndView(ViewRouteHelper.PERMISO_PERIODO_NEW);
		mav.addObject("lugares", lugarService.getAll());
		mav.addObject("personas", personService.getAll());
		mav.addObject("permisoPeriodo", new PermisoPeriodo());
		mav.addObject("desde", new LugarModel());
		mav.addObject("hasta", new LugarModel());
		mav.addObject("rodados", rodadoService.getAll());
		return mav;
	}
	
	@PostMapping("/permisoPeriodoProcess")
	public RedirectView toNuevoPermisoPeriodo(@ModelAttribute("permisoPeriodo") PermisoPeriodoModel permisoPeriodoModel, 
			@RequestParam("idDesde") int idDesde, @RequestParam("idHasta") int idHasta) {
		Set<Lugar> lugares = new HashSet<Lugar>();
		lugares.add(lugarService.findById(idDesde));
		lugares.add(lugarService.findById(idHasta));
		permisoPeriodoModel.setDesdeHasta(lugares);
		permisoPeriodoService.insertOrUpdate(permisoPeriodoModel);
		return new RedirectView(ViewRouteHelper.PERMISO_PERIODO_ROOT);
	}
	
	@GetMapping("/listPermisosRodado/{id}")
	public ModelAndView mostrarListaRodado(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("/person/listPermisosRodado");
		List<PermisoPeriodo> permisos = permisoPeriodoService.findPermisosxRodado(id); 
		mav.addObject("permisos", permisos);
		return mav;
	}
	
	/***@GetMapping("/listPermisosPersona/{id}")
	public ModelAndView mostrarListaPersona(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("/person/listPermisosPersona");
		List<Person> permisos = personService.findPermisosxPersona(id); 
		mav.addObject("permisos", permisos);
		return mav;
	}*/
	@GetMapping("/requestFechas")
	public String formFechas() {
		return "permiso/formFechas";
	}
	
	@PostMapping("/listPermisosFechaAFecha")
	public ModelAndView mostrarListaPermisoFechaAFecha(@RequestParam("fechaInicio") String fechaInicio,
										   @RequestParam("fechaFinal") String fechaFinal) {
		LocalDate fechaInicioConvert = LocalDate.parse(fechaInicio);
		LocalDate fechaFinalConvert = LocalDate.parse(fechaFinal);
		ModelAndView mav = new ModelAndView("permiso/listPermisosFechaAFecha");
		
		List<PermisoDiario> permisosDiarios = permisoDiarioService.findByFechaAFecha
				(fechaInicioConvert, fechaFinalConvert);
		List<PermisoPeriodo> permisosPeriodos = permisoPeriodoService.findByFechaAFecha
				(fechaInicioConvert, fechaFinalConvert);
		
		mav.addObject("permisosDiarios", permisosDiarios);
		mav.addObject("permisosPeriodos", permisosPeriodos);
		return mav;
	}
	
	@GetMapping("/requestFechasYPartida")
	public ModelAndView formFechasYPartida() {
		ModelAndView mav = new ModelAndView("permiso/formFechasYDesde");
		mav.addObject("lugares", lugarService.getAll());
		return mav;
	}
	
	@PostMapping("/listPermisosFechaAFechaYPartida")
	public ModelAndView mostrarListaPermisoFechaAFechaYPartida(@RequestParam("fechaInicio") String fechaInicio,
															   @RequestParam("fechaFinal") String fechaFinal,
															   @RequestParam("idDesde") int idDesde) {
		LocalDate fechaInicioConvert = LocalDate.parse(fechaInicio);
		LocalDate fechaFinalConvert = LocalDate.parse(fechaFinal);
		ModelAndView mav = new ModelAndView("permiso/listPermisosFechaAFechaYPartida");
		
		List<PermisoDiario> permisosDiarios = permisoDiarioService.findByFechaAFechaAndFetchDesde
				(fechaInicioConvert, fechaFinalConvert, idDesde);
		List<PermisoPeriodo> permisosPeriodos = permisoPeriodoService.findByFechaAFechaAndFetchDesde
		(fechaInicioConvert, fechaFinalConvert, idDesde);
		
		mav.addObject("permisosDiarios", permisosDiarios);
		mav.addObject("permisosPeriodos", permisosPeriodos);
		return mav;
	}
}
