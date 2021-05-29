package com.unla.grupo4.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo4.helpers.ViewRouteHelper;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/")
public class IndexController {
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav= new ModelAndView(ViewRouteHelper.INDEX);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("username", user.getPassword());
		return mav;
	}
}
