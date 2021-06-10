package com.unla.grupo4.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.zxing.WriterException;
import com.unla.grupo4.entities.Lugar;
import com.unla.grupo4.entities.PermisoDiario;
import com.unla.grupo4.entities.PermisoPeriodo;
import com.unla.grupo4.entities.Person;
import com.unla.grupo4.helpers.ViewRouteHelper;
import com.unla.grupo4.models.PermisoDiarioModel;
import com.unla.grupo4.models.PermisoPeriodoModel;
import com.unla.grupo4.services.ILugarService;
import com.unla.grupo4.services.IPermisoDiarioService;
import com.unla.grupo4.services.IPermisoPeriodoService;
import com.unla.grupo4.services.IPersonService;
import com.unla.grupo4.services.IRodadoService;
import com.unla.grupo4.services.IUserService;
import com.unla.grupo4.services.QRCodeService;

@Controller
@RequestMapping("/permiso")
public class PermisoController {
	
	@Autowired
	@Qualifier("permisoDiarioService")
	private IPermisoDiarioService permisoDiarioService;
	
	@Autowired
	@Qualifier("permisoPeriodoService")
	private IPermisoPeriodoService permisoPeriodoService;
	
	@Autowired
	@Qualifier("lugarService")
	private ILugarService lugarService;
	
	@Autowired
	@Qualifier("personService")
	private IPersonService personService;
	
	@Autowired
	@Qualifier("rodadoService")
	private IRodadoService rodadoService;
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@Autowired
	@Qualifier("qrCodeService")
	QRCodeService qrCodeService; 
	
	@GetMapping("/newPermisoDiario")
	public ModelAndView nuevoPermisoDiario() {
		ModelAndView mav = new ModelAndView(ViewRouteHelper.PERMISO_DIARIO_NEW);
		//List<Permiso> pepe = permisoPeriodoService.getAll();
		mav.addObject("lugares", lugarService.getAll());
		mav.addObject("personas", personService.getAll());
		mav.addObject("permisoDiario", new PermisoDiario());
		mav.addObject("userlogrole", userService.getRoleOfUserLog());
		return mav;
	}
	
	@PostMapping("/permisoDiarioProcess")
	public RedirectView toNuevoPermisoDiario(@ModelAttribute("permisoDiario") PermisoDiarioModel permisoDiarioModel,
											 @RequestParam("idDesde") int idDesde, @RequestParam("idHasta") int idHasta) throws WriterException, IOException {
		Set<Lugar> lugares = new HashSet<Lugar>();
		lugares.add(lugarService.findById(idDesde));
		lugares.add(lugarService.findById(idHasta));
		permisoDiarioModel.setDesdeHasta(lugares);
		permisoDiarioService.insertOrUpdate(permisoDiarioModel);
		
//		int ultimoId = 1;
//		List<PermisoDiario> aux = permisoDiarioService.getAll();
//		ultimoId = aux.get(aux.size()-1).getId();
//		
//		qrCodeService.generateQRCodeImage("Permiso"+ ultimoId, 200, 200,
//				ViewRouteHelper.QR_CODE_IMAGE_PATH + ultimoId +".png");
		
		qrCodeService.generateQRCodeImage(ViewRouteHelper.LINK +
				permisoDiarioService.modelToURL(permisoDiarioModel,
				personService.findById(permisoDiarioModel.getPerson().getId())), 200, 200, 
				ViewRouteHelper.QR_CODE_IMAGE_PATH +"pruebaDiario.png");
		
		return new RedirectView(ViewRouteHelper.PERMISO_DIARIO_ROOT);
	}

	@GetMapping("/newPermisoPeriodo")
	public ModelAndView nuevoPermisoPeriodo() {
		ModelAndView mav = new ModelAndView(ViewRouteHelper.PERMISO_PERIODO_NEW);
		mav.addObject("lugares", lugarService.getAll());
		mav.addObject("personas", personService.getAll());
		mav.addObject("permisoPeriodo", new PermisoPeriodo());
		mav.addObject("rodados", rodadoService.getAll());
		mav.addObject("userlogrole", userService.getRoleOfUserLog());
		return mav;
	}
	
	@PostMapping("/permisoPeriodoProcess")
	public RedirectView toNuevoPermisoPeriodo(@ModelAttribute("permisoPeriodo") PermisoPeriodoModel permisoPeriodoModel, 
			@RequestParam("idDesde") int idDesde, @RequestParam("idHasta") int idHasta) throws WriterException, IOException {
		Set<Lugar> lugares = new HashSet<Lugar>();
		lugares.add(lugarService.findById(idDesde));
		lugares.add(lugarService.findById(idHasta));
		permisoPeriodoModel.setDesdeHasta(lugares);
		permisoPeriodoService.insertOrUpdate(permisoPeriodoModel);
		
//		int ultimoId = 1;
//		List<PermisoPeriodo> aux = permisoPeriodoService.getAll();
//		ultimoId = aux.get(aux.size()-1).getId();
//		
//		qrCodeService.generateQRCodeImage("Permiso"+ ultimoId, 200, 200,
//				ViewRouteHelper.QR_CODE_IMAGE_PATH + ultimoId +".png");
		
		qrCodeService.generateQRCodeImage(ViewRouteHelper.LINK +
				permisoPeriodoService.modelToURL(permisoPeriodoModel, 
				rodadoService.findById(permisoPeriodoModel.getRodado().getId()),
				personService.findById(permisoPeriodoModel.getPerson().getId())), 200, 200, 
				ViewRouteHelper.QR_CODE_IMAGE_PATH +"prueba.png");
		
		return new RedirectView(ViewRouteHelper.PERMISO_PERIODO_ROOT);
	}
	
	@GetMapping("/requestListPermisosRodado")
	public ModelAndView requestRodado() {
		ModelAndView mav = new ModelAndView("permiso/requestRodado");
		mav.addObject(mav);
		return mav;
	}
	
	@PostMapping("/listPermisosRodado")
	public ModelAndView mostrarListaRodado(@RequestParam(name="dominio", required = false) String dominio) {
		ModelAndView mav = new ModelAndView("/permiso/listPermisosRodado");
		List<PermisoPeriodo> permisos = permisoPeriodoService.findPermisosxRodado(dominio);
		mav.addObject("permisos", permisos);
		return mav;
	}	
	
	@GetMapping("/listPermisosPorPersona")
	public ModelAndView mostrarListaRodado(@RequestParam(name="dni",defaultValue = "0") long dni) {
		ModelAndView mav = new ModelAndView("/permiso/listPermisosPersona");
		Person persona = personService.findByDni(dni);
		if(persona != null) {
			List<PermisoPeriodo> permisosPeriodo = permisoPeriodoService.traerPermisosPorPersona(persona.getId());
			mav.addObject("permisosPeriodo", permisosPeriodo);
			List<PermisoDiario> permisosDiarios = permisoDiarioService.traerPermisosPorPersona(persona.getId());
			mav.addObject("permisosDiarios", permisosDiarios);			
		}
		mav.addObject("userlogrole", userService.getRoleOfUserLog());
		return mav;
	}	
	
	@GetMapping("/requestFechas")
	public String formFechas() {
		return "permiso/formFechas";
	}
	
	@PostMapping("/listPermisosFechaAFecha")
	public ModelAndView mostrarListaPermisoFechaAFecha(@RequestParam("fechaInicio") String fechaInicio,
										   @RequestParam("fechaFinal") String fechaFinal) {
		LocalDate fechaInicioConvert = LocalDate.parse(fechaInicio);
		LocalDate fechaFinalConvert = LocalDate.parse(fechaFinal);
		ModelAndView mav = new ModelAndView("permiso/listPermisosFechaAFecha");
		
		List<PermisoDiario> permisosDiarios = permisoDiarioService.findByFechaAFecha
				(fechaInicioConvert, fechaFinalConvert);
		List<PermisoPeriodo> permisosPeriodos = permisoPeriodoService.findByFechaAFecha
				(fechaInicioConvert, fechaFinalConvert);
		
		mav.addObject("permisosDiarios", permisosDiarios);
		mav.addObject("permisosPeriodos", permisosPeriodos);
		return mav;
	}
	
	@GetMapping("/requestFechasYPartida")
	public ModelAndView formFechasYPartida() {
		ModelAndView mav = new ModelAndView("permiso/formFechasYDesde");
		mav.addObject("lugares", lugarService.getAll());
		return mav;
	}
	
	@PostMapping("/listPermisosFechaAFechaYPartida")
	public ModelAndView mostrarListaPermisoFechaAFechaYPartida(@RequestParam("fechaInicio") String fechaInicio,
															   @RequestParam("fechaFinal") String fechaFinal,
															   @RequestParam("idDesde") int idDesde) {
		LocalDate fechaInicioConvert = LocalDate.parse(fechaInicio);
		LocalDate fechaFinalConvert = LocalDate.parse(fechaFinal);
		ModelAndView mav = new ModelAndView("permiso/listPermisosFechaAFechaYPartida");
		
		List<PermisoDiario> permisosDiarios = permisoDiarioService.findByFechaAFechaAndFetchDesde
				(fechaInicioConvert, fechaFinalConvert, idDesde);
		List<PermisoPeriodo> permisosPeriodos = permisoPeriodoService.findByFechaAFechaAndFetchDesde
		(fechaInicioConvert, fechaFinalConvert, idDesde);
		
		mav.addObject("permisosDiarios", permisosDiarios);
		mav.addObject("permisosPeriodos", permisosPeriodos);
		return mav;
	}
	
	@GetMapping("/requestFechasYDestino")
	public ModelAndView formFechasYDestino() {
		ModelAndView mav = new ModelAndView("permiso/formFechasYDestino");
		mav.addObject("lugares", lugarService.getAll());
		return mav;
	}
	
	@PostMapping("/listPermisosFechaAFechaYDestino")
	public ModelAndView mostrarListaPermisoFechaAFechaYDestino(@RequestParam("fechaInicio") String fechaInicio,
															   @RequestParam("fechaFinal") String fechaFinal,
															   @RequestParam("idHasta") int idHasta) {
		LocalDate fechaInicioConvert = LocalDate.parse(fechaInicio);
		LocalDate fechaFinalConvert = LocalDate.parse(fechaFinal);
		ModelAndView mav = new ModelAndView("permiso/listPermisosFechaAFechaYDestino");
		
		List<PermisoDiario> permisosDiarios = permisoDiarioService.findByFechaAFechaAndFetchHasta
				(fechaInicioConvert, fechaFinalConvert, idHasta);
		List<PermisoPeriodo> permisosPeriodos = permisoPeriodoService.findByFechaAFechaAndFetchHasta
		(fechaInicioConvert, fechaFinalConvert, idHasta);
		
		mav.addObject("permisosDiarios", permisosDiarios);
		mav.addObject("permisosPeriodos", permisosPeriodos);
		return mav;
	}
}
