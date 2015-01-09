package es.pruebas.springmvc.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.pruebas.springmvc.modelo.Rol;
import es.pruebas.springmvc.modelo.Usuario;
import es.pruebas.springmvc.negocio.service.RolService;
import es.pruebas.springmvc.negocio.service.TipoUsuarioService;
import es.pruebas.springmvc.negocio.service.UsuarioService;
import es.pruebas.springmvc.web.form.usuario.BuscadorUsuariosForm;
import es.pruebas.springmvc.web.form.usuario.UsuarioForm;


@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TipoUsuarioService tipoUsuarioService;

	@Autowired
	private RolService roleService;
	
	
	

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		
		//Transformación de los ids recibidos de la vista para componer los objetos Rol
		binder.registerCustomEditor(Set.class, "usuario.roles",
				new CustomCollectionEditor(Set.class) {
					protected Object convertElement(Object element) {
						Rol rol = null;
						if (!StringUtils.isEmpty(element)) {
							rol = new Rol();
							rol.setId(Long.parseLong((String) element));
						}
						return rol;
					}

				});
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
	public ModelAndView listadoBuscar(@ModelAttribute("buscadorUsuariosForm") BuscadorUsuariosForm buscadorUsuariosForm) {
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
	
	

	
	
	
	
	
	@RequestMapping(value="/nuevo", method=RequestMethod.GET)
	public ModelAndView formNuevo() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("tiposUsuario", tipoUsuarioService.getAll());
		mav.addObject("roles", roleService.getAll());
		
		mav.addObject("usuarioForm", new UsuarioForm());
		mav.setViewName("app/usuarios/nuevoUsuario");
		return mav;
	}
	

	@RequestMapping(value="/nuevo", params="accion=crear", method=RequestMethod.POST)
	public ModelAndView formNuevoCrear(@ModelAttribute("usuarioForm") UsuarioForm usuarioForm, RedirectAttributes redirectAttributes) {
		logger.debug("Acción de crear usuario");
		
		Usuario usuario = usuarioForm.getUsuario();
		usuario = usuarioService.save(usuario);
		
		//Mostrar un aviso en el listado indicando que el usuario se creó correctamente
		redirectAttributes.addFlashAttribute("usuarioCreado", true); 
		
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("redirect:/usuarios");
		return mav;
	}
	
	
	@RequestMapping(value="/nuevo", params="accion=volver", method=RequestMethod.POST)
	public ModelAndView formNuevoVolver() {
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("redirect:/usuarios");
		return mav;
	}
	
	

}
