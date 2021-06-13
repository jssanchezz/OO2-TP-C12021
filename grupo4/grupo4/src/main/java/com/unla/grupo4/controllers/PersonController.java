package com.unla.grupo4.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo4.helpers.ViewRouteHelper;
import com.unla.grupo4.models.PersonModel;
import com.unla.grupo4.services.IPersonService;

@Controller
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	@Qualifier("personService")
	private IPersonService personService;
	
	@GetMapping("/newPerson")
	public ModelAndView form(Model model) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERSON_FORM);
		if(!model.containsAttribute("person"))
		mAV.addObject("person", new PersonModel());
		
		return mAV;
	}
	
	@PostMapping("/createPerson")
	public RedirectView createPerson(@Valid @ModelAttribute("person") PersonModel personModel, BindingResult bindingResult, RedirectAttributes attribute) {
		if(bindingResult.hasErrors()) {
			attribute.addFlashAttribute("org.springframework.validation.BindingResult.person", bindingResult);
			attribute.addFlashAttribute("person", personModel);
		}else {
		if(personService.findByDni(personModel.getDni()) == null) {
			personService.insertOrUpdate(personModel);
			attribute.addFlashAttribute("mensaje", "Guardado correctamente");
	        attribute.addFlashAttribute("clase", "success");
		}else {
			attribute.addFlashAttribute("mensaje", "Persona ya existente con dni: " + personModel.getDni());
	        attribute.addFlashAttribute("clase", "warning");
		}
		}
		return new RedirectView(ViewRouteHelper.ROUTE_PERSON_FORM);
	}

}
