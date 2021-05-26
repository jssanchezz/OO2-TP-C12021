package com.unla.grupo4.controllers;

import java.util.List;

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
import com.unla.grupo4.models.TypeDoc;
import com.unla.grupo4.models.UserModel;
import com.unla.grupo4.models.UserRoleModel;
import com.unla.grupo4.services.IUserRoleService;
import com.unla.grupo4.services.IUserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@Autowired
	@Qualifier("userRoleService")
	private IUserRoleService userRoleService;

	@GetMapping("/")
	public String index() {
		return ViewRouteHelper.USER_INDEX;
	}

	@GetMapping("/newUser")
	public ModelAndView form() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_FORM);//"/user/prueba"
		mAV.addObject("roles", userRoleService.getAll());
		mAV.addObject("users", userService.getAll());
		mAV.addObject("typeDoc", TypeDoc.values());
		mAV.addObject("user", new UserModel());
		return mAV;
	}

	@PostMapping("/newUser")
	public RedirectView createUser(@ModelAttribute("user") UserModel userModel) {
		userService.insertOrUpdate(userModel);
		return new RedirectView(ViewRouteHelper.ROUTE_USER_FORM);
	}

	@GetMapping("/updateUser")
	public ModelAndView update() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_UPDATE);
		mAV.addObject("users", userService.getAll());
		mAV.addObject("user", new UserModel());
		return mAV;
	}

	@PostMapping("/updateUser")
	public RedirectView updateUser(@ModelAttribute("user") UserModel userModel) {
		userService.insertOrUpdate(userModel);
		return new RedirectView(ViewRouteHelper.ROUTE_USER_UPDATE);
	}

	@GetMapping("/deleteUser")
	public ModelAndView delete() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_DELETE);
		mAV.addObject("users", userService.findByEnabled(true));
		mAV.addObject("user", new UserModel());
		return mAV;
	}

	@PostMapping("/deleteUser")
	public RedirectView deleteUser(@ModelAttribute("user") UserModel userModel) {
		userService.insertOrUpdate(userModel);
		return new RedirectView(ViewRouteHelper.ROUTE_USER_DELETE);
	}

}
