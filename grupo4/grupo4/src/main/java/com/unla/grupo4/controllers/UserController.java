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
import com.unla.grupo4.models.UserModel;
import com.unla.grupo4.services.IUserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_INDEX);
		mAV.addObject("users", userService.getAll());
		mAV.addObject("user", new UserModel());
		return mAV;
	}
	
	@PostMapping("/")
	public RedirectView create(@ModelAttribute("user") UserModel userModel) {
		userService.insertOrUpdate(userModel);
		return new RedirectView(ViewRouteHelper.USER_ROOT);
	}
	
	
}
