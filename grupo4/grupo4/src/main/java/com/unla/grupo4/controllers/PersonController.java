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
import com.unla.grupo4.models.PersonModel;
import com.unla.grupo4.services.IPersonService;

@Controller
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	@Qualifier("personService")
	private IPersonService personService;

	@GetMapping("/newPerson")
	public ModelAndView form() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERSON_FORM);
		mAV.addObject("person", new PersonModel());
		return mAV;
	}
	
	@PostMapping("/createPerson")
	public RedirectView createPerson(@ModelAttribute("person") PersonModel personModel) {
		personService.insertOrUpdate(personModel);
		return new RedirectView(ViewRouteHelper.ROUTE_PERSON_FORM);
	}

}
