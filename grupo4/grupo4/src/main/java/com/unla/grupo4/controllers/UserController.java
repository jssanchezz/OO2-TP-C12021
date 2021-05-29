package com.unla.grupo4.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.lowagie.text.DocumentException;
import com.unla.grupo4.entities.User;
import com.unla.grupo4.entities.UserRole;
import com.unla.grupo4.helpers.ViewRouteHelper;
import com.unla.grupo4.miscelaneo.UserPDFExporter;
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
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_FORM);
		mAV.addObject("roles", userRoleService.getAll());
		mAV.addObject("typeDoc", TypeDoc.values());
		mAV.addObject("user", new UserModel());
		return mAV;
	}

	@PostMapping("/newUser")
	public RedirectView createUser(@ModelAttribute("user") UserModel userModel, RedirectAttributes attribute) {
		userModel.setRole(userRoleService.findById(userModel.getRole().getId()));
		if(userService.findByDni(userModel.getDni()) != null && (userService.findByDni(userModel.getDni())).getId() != userModel.getId()) {
			attribute.addFlashAttribute("mensaje", "El DNI ya existe");
	        attribute.addFlashAttribute("clase", "warning");
		}else if(userService.findByEmail(userModel.getEmail()) != null && userService.findByEmail(userModel.getEmail()).getId() != userModel.getId()) {
			attribute.addFlashAttribute("mensaje", "el Email ya esta tomado");
	        attribute.addFlashAttribute("clase", "warning");
		}else if(userService.findByUserName(userModel.getUserName()) != null && userService.findByUserName(userModel.getUserName()).getId() != userModel.getId()){
			attribute.addFlashAttribute("mensaje", "el Usuario ya esta tomado");
	        attribute.addFlashAttribute("clase", "warning");
		}
		else if(userModel.getUserPassword() == null) {
			attribute.addFlashAttribute("mensaje", "el password es nulo");
	        attribute.addFlashAttribute("clase", "warning");
		}else {
			attribute.addFlashAttribute("mensaje", "Guardado correctamente");
	        attribute.addFlashAttribute("clase", "success");
		}
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		userModel.setUserPassword(pe.encode(userModel.getUserPassword()));
		userService.insertOrUpdate(userModel);
		return new RedirectView(ViewRouteHelper.ROUTE_USER_FORM);
	}

	@GetMapping("/updateUser")
	public ModelAndView update() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_UPDATE);
		mAV.addObject("users", userService.findByEnabled(false));
		return mAV;
	}
	
	@GetMapping("/updateUser/{id}")
	public ModelAndView updateUser(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_UPDATE_FORM);
		List<UserRole> roles = userRoleService.getAll();
		UserModel user = userService.findById(id);
		mAV.addObject("roles", roles);
		mAV.addObject("user", user);
		mAV.addObject("typeDoc", TypeDoc.values());		
		return mAV;
	}

	@GetMapping("/deleteUser")
	public ModelAndView delete() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_DELETE);
		mAV.addObject("users", userService.findByEnabled(false));
		return mAV;
	}

	@PostMapping("/deleteUser/{id}")
	public RedirectView deleteUser(@PathVariable("id") int id, RedirectAttributes attribute) {
		if(userService.remove(id)) {
			attribute.addFlashAttribute("mensaje", "Eliminado correctamente");
	        attribute.addFlashAttribute("clase", "success");
		}else {
			attribute.addFlashAttribute("mensaje", "Perfil no existente");
	        attribute.addFlashAttribute("clase", "danger");
		}
		return new RedirectView(ViewRouteHelper.ROUTE_USER_DELETE);
	}
	
	@GetMapping("/listUsers")
	public ModelAndView listsUser() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_LIST);
		mAV.addObject("users", userService.findByEnabled(false));
		return mAV;
	}
	
	@GetMapping("/exportPDF")
	public void pdfExporter(HttpServletResponse response)throws DocumentException, IOException {
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<User> listUsers = userService.getAll();
         
        UserPDFExporter exporter = new UserPDFExporter(listUsers);
        exporter.export(response);
	}
	
}
