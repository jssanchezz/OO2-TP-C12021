package com.unla.grupo4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.unla.grupo4.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/home")
public class HomeController {
	
@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.INDEX);
		return modelAndView;
	}
}
