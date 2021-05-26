package com.unla.grupo4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/loginsucecss")
	public String loginChech() {
		return "redirect:/index";
	}
	
	@GetMapping("/olvidemicontraseña")
	public String olvidoContraseña() {
		return ViewRouteHelper.OLVIDOCONTRASEÑA;
	}
}
