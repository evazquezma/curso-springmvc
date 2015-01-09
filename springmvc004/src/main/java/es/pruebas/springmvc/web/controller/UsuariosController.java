package es.pruebas.springmvc.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.pruebas.springmvc.negocio.service.UsuarioService;
import es.pruebas.springmvc.web.form.usuario.BuscadorUsuariosForm;
import es.pruebas.springmvc.web.form.usuario.UsuarioForm;


@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private UsuarioService usuarioService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("usuarios", usuarioService.getAll());
		mav.addObject(new BuscadorUsuariosForm());
		mav.setViewName("app/usuarios/listado");
		return mav;
	}


	@RequestMapping(params="accion=buscar", method=RequestMethod.POST)
	public ModelAndView listadoBuscar(BuscadorUsuariosForm buscadorUsuariosForm) {
		logger.debug("Acción de buscar usuarios");
		
		ModelAndView mav = new ModelAndView();

		mav.addObject("usuarios", usuarioService.buscar(buscadorUsuariosForm.getFiltro()));

		mav.setViewName("app/usuarios/listado");
		return mav;
	}
	
	@RequestMapping(params="accion=crear", method=RequestMethod.POST)
	public ModelAndView listadoCrear() {
		logger.debug("Acción de crear usuario");
		
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("redirect:/usuarios/nuevo");
		return mav;
	}
	
	
	// redirectAttributes.addFlashAttribute("message", message); 

	
	
	
	
	
	@RequestMapping(value="/nuevo", method=RequestMethod.GET)
	public ModelAndView formNuevo() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("usuarioForm", new UsuarioForm());
		mav.setViewName("app/usuarios/nuevoUsuario");
		return mav;
	}

	
	
	

}
