package com.unla.grupo4.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo4.helpers.ViewRouteHelper;

@Controller
public class LoginController {
	
	

	@GetMapping("/login")
	public String login(Model model, @RequestParam(name="error", required=false) String error,
									 @RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("logout", logout);
		model.addAttribute("error", error);
		return ViewRouteHelper.LOGIN;
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return ViewRouteHelper.LOGOUT;
	}
	
	@GetMapping("/loginsuccess")
	public String loginChech() {
		return "redirect:/index";
	}
	
	@GetMapping("/olvidemicontraseña")
	public String olvidoContraseña() {
		return ViewRouteHelper.OLVIDOCONTRASEÑA;
	}
	
	@PreAuthorize("hasRole('administrador')")
	@GetMapping("/index")
	public String index() {
		return ViewRouteHelper.INDEX;
	}
	
//	@PreAuthorize("hasRole('administrador')")
//	@GetMapping("/")
//	public ModelAndView index1() {
//		ModelAndView mav = new ModelAndView(ViewRouteHelper.INDEX);
//		return mav;
//	}
//	
//	@GetMapping("/index")
//	public ModelAndView index2() {
//		ModelAndView mav = new ModelAndView(ViewRouteHelper.INDEX2);
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		mav.addObject("username", user.getUsername());
//		return mav;
//	}
}
