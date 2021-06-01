package com.unla.grupo4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo4.entities.PermisoDiario;
import com.unla.grupo4.helpers.ViewRouteHelper;
import com.unla.grupo4.models.PermisoDiarioModel;
import com.unla.grupo4.services.ILugarService;
import com.unla.grupo4.services.IPermisoDiarioService;
import com.unla.grupo4.services.IPermisoPeriodoService;

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
	
	/*@Autowired
	@Qualifier("personaService")
	private IPersonService personService;*/
	
	@GetMapping("/newPermisoDiario")
	public ModelAndView nuevoPermisoDiario() {
		ModelAndView mav = new ModelAndView(ViewRouteHelper.PERMISO_DIARIO_NEW);
		//mav.addObject("lugar", );
		mav.addObject("permisoDiario", new PermisoDiario());
		return mav;
	}
	
	@PostMapping("/permisoDiarioProcess")
	public RedirectView toNuevoPermisoDiario(@ModelAttribute("permisoDiario") PermisoDiarioModel permisoDiarioModel) {
		permisoDiarioService.insertOrUpdate(permisoDiarioModel);
		return new RedirectView(ViewRouteHelper.PERMISO_DIARIO_ROOT);
	}
}
