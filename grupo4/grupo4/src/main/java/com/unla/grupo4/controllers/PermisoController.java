package com.unla.grupo4.controllers;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo4.converters.PersonConverter;
import com.unla.grupo4.converters.RodadoConverter;
import com.unla.grupo4.entities.Lugar;
import com.unla.grupo4.entities.Permiso;
import com.unla.grupo4.entities.PermisoDiario;
import com.unla.grupo4.entities.PermisoPeriodo;
import com.unla.grupo4.entities.Person;
import com.unla.grupo4.helpers.ViewRouteHelper;
import com.unla.grupo4.models.LugarModel;
import com.unla.grupo4.models.PermisoDiarioModel;
import com.unla.grupo4.models.PermisoPeriodoModel;
import com.unla.grupo4.models.PersonModel;
import com.unla.grupo4.services.ILugarService;
import com.unla.grupo4.services.IPermisoDiarioService;
import com.unla.grupo4.services.IPermisoPeriodoService;
import com.unla.grupo4.services.IPersonService;
import com.unla.grupo4.services.IRodadoService;
import com.unla.grupo4.services.IUserService;
import com.unla.grupo4.services.UserService;

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

	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	@GetMapping("/newPermisoDiario")
	public ModelAndView nuevoPermisoDiario(@RequestParam(name = "dni", defaultValue = "0") long dni, Model model) {
		ModelAndView mav = new ModelAndView(ViewRouteHelper.PERMISO_DIARIO_NEW);
		mav.addObject("lugares", lugarService.getAll());

		PermisoDiarioModel permisoDiario = new PermisoDiarioModel();

		if (personService.findByDni(dni) != null) {
			permisoDiario.setPerson(personService.findByDni(dni));
			mav.addObject("existePersona", "OK");
		} else if (dni != 0) {
			mav.addObject("mensaje", "Persona inexistente, por favor dar de alta.");
			mav.addObject("clase", "warning");
		}
		mav.addObject("permisoDiario", permisoDiario);
		mav.addObject("userlogrole", userService.getRoleOfUserLog());
		return mav;
	}

	@PostMapping("/permisoDiarioProcess")
	public RedirectView toNuevoPermisoDiario(@ModelAttribute("permisoDiario") PermisoDiarioModel permisoDiarioModel,
			BindingResult bindingResult, RedirectAttributes attribute, @RequestParam("idDesde") int idDesde,
			@RequestParam("idHasta") int idHasta) {
		if (bindingResult.hasErrors()) {
			attribute.addFlashAttribute("org.springframework.validation.BindingResult.permisoDiario", bindingResult);
			attribute.addFlashAttribute("permisoDiario", permisoDiarioModel);
		} else {
			Set<Lugar> lugares = new HashSet<Lugar>();
			lugares.add(lugarService.findById(idDesde));
			lugares.add(lugarService.findById(idHasta));
			permisoDiarioModel.setDesdeHasta(lugares);
			permisoDiarioService.insertOrUpdate(permisoDiarioModel);
		}
		return new RedirectView(ViewRouteHelper.PERMISO_DIARIO_ROOT);
	}

	@GetMapping("/newPermisoPeriodo")
	public ModelAndView nuevoPermisoPeriodo(@RequestParam(name="dni", defaultValue = "0") long dni) {
		ModelAndView mav = new ModelAndView(ViewRouteHelper.PERMISO_PERIODO_NEW);
		mav.addObject("lugares", lugarService.getAll());
		
		PermisoPeriodoModel permisoPeriodo = new PermisoPeriodoModel();
		
		if(personService.findByDni(dni) != null) {
			permisoPeriodo.setPerson(personService.findByDni(dni));
			mav.addObject("existePersona", "OK");
		}else if (dni != 0) {
			mav.addObject("mensaje", "Persona inexistente, por favor dar de alta.");
	        mav.addObject("clase", "warning");
		}
		
		mav.addObject("permisoPeriodo", permisoPeriodo);		
		mav.addObject("userlogrole", userService.getRoleOfUserLog());
		return mav;
	}
	
	@PostMapping("/permisoPeriodoProcess")
	public RedirectView toNuevoPermisoPeriodo(@ModelAttribute("permisoPeriodo") PermisoPeriodoModel permisoPeriodoModel, 
			@RequestParam("idDesde") int idDesde, @RequestParam("idHasta") int idHasta, @RequestParam("dominioRodado") String dominioRodado, RedirectAttributes attribute) {
		
		if(rodadoService.findByDominio(dominioRodado) != null) {
			permisoPeriodoModel.setRodado(rodadoService.findByDominio(dominioRodado));
			Set<Lugar> lugares = new HashSet<Lugar>();
			lugares.add(lugarService.findById(idDesde));
			lugares.add(lugarService.findById(idHasta));
			permisoPeriodoModel.setDesdeHasta(lugares);
			permisoPeriodoService.insertOrUpdate(permisoPeriodoModel);
		} else {
			attribute.addFlashAttribute("mensaje", "Rodado inexistente, por favor dar de alta.");
			attribute.addFlashAttribute("clase", "warning");
		}		
		return new RedirectView(ViewRouteHelper.PERMISO_PERIODO_ROOT);
	}
	
	@GetMapping("/requestListPermisosRodado")
	public ModelAndView requestRodado(@RequestParam(name = "dominio", required = false) String dominio) {
		ModelAndView mav = new ModelAndView("/permiso/listPermisosRodado");
		List<PermisoPeriodo> permisos = permisoPeriodoService.findPermisosxRodado(dominio);		
		mav.addObject("permisos", permisos);
		mav.addObject("userlogrole", userService.getRoleOfUserLog());
		return mav;
	}
	
	/**
	@PostMapping("/listPermisosRodado")
	public ModelAndView mostrarListaRodado(@RequestParam(name="dominio", required = false) String dominio) {
		ModelAndView mav = new ModelAndView("/permiso/listPermisosRodado");
		List<PermisoPeriodo> permisos = permisoPeriodoService.findPermisosxRodado(dominio);
		mav.addObject("permisos", permisos);
		
		return mav;
	}*/
	
	@GetMapping("/listPermisosPorPersona")
	public ModelAndView mostrarListaRodado(@RequestParam(name="dni",defaultValue = "0") long dni) {
		ModelAndView mav = new ModelAndView("/permiso/listPermisosPersona");
		Person persona = personService.findByDni(dni);
		if(persona != null) {
			List<PermisoPeriodo> permisosPeriodo = permisoPeriodoService.traerPermisosPorPersona(persona.getId());
			mav.addObject("permisosPeriodo", permisosPeriodo);
			List<PermisoDiario> permisosDiarios = permisoDiarioService.traerPermisosPorPersona(persona.getId());
			mav.addObject("permisosDiarios", permisosDiarios);			
		}
		mav.addObject("userlogrole", userService.getRoleOfUserLog());
		return mav;
	}	
	
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
	
	@GetMapping("/requestFechasYDestino")
	public ModelAndView formFechasYDestino() {
		ModelAndView mav = new ModelAndView("permiso/formFechasYDestino");
		mav.addObject("lugares", lugarService.getAll());
		return mav;
	}
	
	@PostMapping("/listPermisosFechaAFechaYDestino")
	public ModelAndView mostrarListaPermisoFechaAFechaYDestino(@RequestParam("fechaInicio") String fechaInicio,
															   @RequestParam("fechaFinal") String fechaFinal,
															   @RequestParam("idHasta") int idHasta) {
		LocalDate fechaInicioConvert = LocalDate.parse(fechaInicio);
		LocalDate fechaFinalConvert = LocalDate.parse(fechaFinal);
		ModelAndView mav = new ModelAndView("permiso/listPermisosFechaAFechaYDestino");
		
		List<PermisoDiario> permisosDiarios = permisoDiarioService.findByFechaAFechaAndFetchHasta
				(fechaInicioConvert, fechaFinalConvert, idHasta);
		List<PermisoPeriodo> permisosPeriodos = permisoPeriodoService.findByFechaAFechaAndFetchHasta
		(fechaInicioConvert, fechaFinalConvert, idHasta);
		
		mav.addObject("permisosDiarios", permisosDiarios);
		mav.addObject("permisosPeriodos", permisosPeriodos);
		return mav;
	}
}
