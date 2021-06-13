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
	
	@GetMapping("/logoutsuccess")
	public String logout(Model model) {
		return ViewRouteHelper.LOGOUT;
	}
	
	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/";
	}
}
