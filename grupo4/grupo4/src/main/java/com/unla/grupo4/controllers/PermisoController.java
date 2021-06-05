package com.unla.grupo4.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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
	public ModelAndView nuevoPermisoDiario() {
		ModelAndView mav = new ModelAndView(ViewRouteHelper.PERMISO_DIARIO_NEW);
		//List<Permiso> pepe = permisoPeriodoService.getAll();
		mav.addObject("lugares", lugarService.getAll());
		mav.addObject("personas", personService.getAll());
		mav.addObject("permisoDiario", new PermisoDiario());
		mav.addObject("userlogrole", userService.getRoleOfUserLog());
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
		mav.addObject("rodados", rodadoService.getAll());
		mav.addObject("userlogrole", userService.getRoleOfUserLog());
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
	
	@GetMapping("/requestListPermisosRodado")
	public ModelAndView requestRodado() {
		ModelAndView mav = new ModelAndView("permiso/requestRodado");
		mav.addObject(mav);
		return mav;
	}
	
	@PostMapping("/listPermisosRodado")
	public ModelAndView mostrarListaRodado(@RequestParam(name="dominio", required = false) String dominio) {
		ModelAndView mav = new ModelAndView("/permiso/listPermisosRodado");
		List<PermisoPeriodo> permisos = permisoPeriodoService.findPermisosxRodado(dominio);
		mav.addObject("permisos", permisos);
		return mav;
	}	
	
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
}
