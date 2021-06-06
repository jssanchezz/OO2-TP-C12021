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

import com.unla.grupo4.helpers.ViewRouteHelper;
import com.unla.grupo4.models.RodadoModel;
import com.unla.grupo4.services.IRodadoService;

@Controller
@RequestMapping("/rodado")
public class RodadoController {
	
	@Autowired
	@Qualifier("rodadoService")
	private IRodadoService rodadoService;
	
	@GetMapping("/newRodado")
	public ModelAndView createRodado() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.RODADO_INSERT);
		mAV.addObject("rodado", new RodadoModel());
		return mAV;
	}
	
	@PostMapping("/saveRodado")
	public RedirectView save(@ModelAttribute("rodado") RodadoModel rodadoModel) {
		rodadoService.insertOrUpdate(rodadoModel);
		return new RedirectView(ViewRouteHelper.RODADO_ROOT);
	}
	
}
