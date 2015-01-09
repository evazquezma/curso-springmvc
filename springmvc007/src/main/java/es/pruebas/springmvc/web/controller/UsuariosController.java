package es.pruebas.springmvc.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.pruebas.springmvc.dao.base.InstanceNotFoundException;
import es.pruebas.springmvc.modelo.Rol;
import es.pruebas.springmvc.modelo.Usuario;
import es.pruebas.springmvc.negocio.service.RolService;
import es.pruebas.springmvc.negocio.service.TipoUsuarioService;
import es.pruebas.springmvc.negocio.service.UsuarioService;
import es.pruebas.springmvc.web.form.usuario.BuscadorUsuariosForm;
import es.pruebas.springmvc.web.form.usuario.UsuarioForm;


@Controller
@RequestMapping("/usuarios")
public class UsuariosController extends AbstractBaseController {
	private final Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired @Qualifier("usuarioFormValidator")
	private Validator usuarioFormValidator;

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
					@Override
					protected Object convertElement(Object element) {
						Rol rol = null;
						if (!StringUtils.isEmpty(element)) {
							rol = new Rol();
							rol.setId(Long.parseLong((String) element));
						}
						return rol;
					}

				});

		//De esta forma intentará validar todos los ModelAttribute recibidos como parámetros. Obliga a que exista
		//un validador específico para cada modelo, sino suelta una excepción
		//binder.setValidator(usuarioFormValidator);
	}

	/**
	 * Se restringe el initBinder para que únicamente aplique a los métodos del controller que tenga
	 * un atributo de ese nombre
	 * @param binder
	 */
	@InitBinder("usuarioForm")
	protected void initBinderUsuarioForm(WebDataBinder binder) {
		//Registro el/los validadores manuales aplicables a usuarioForm
		binder.setValidator(usuarioFormValidator);
	}






	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listado() {
		return crearVistaListado(new BuscadorUsuariosForm());
	}


	@RequestMapping(params="accion=buscar", method=RequestMethod.POST)
	public ModelAndView listadoBuscar(@ModelAttribute("buscadorUsuariosForm") BuscadorUsuariosForm buscadorUsuariosForm) {
		logger.debug("Acción desde el listado buscar usuarios");
		return crearVistaListado(new BuscadorUsuariosForm());
	}

	@RequestMapping(params="accion=crear", method=RequestMethod.POST)
	public ModelAndView listadoCrear() {
		logger.debug("Acción desde el listado para crear usuario");
		return new ModelAndView("redirect:/usuarios/nuevo");
	}










	@RequestMapping(value="/nuevo", method=RequestMethod.GET)
	public ModelAndView formNuevo() {
		return crearVistaNuevoUsuario(new UsuarioForm());
	}


	@RequestMapping(value="/nuevo", params="accion=crear", method=RequestMethod.POST)
	public ModelAndView formNuevoCrear(@Valid @ModelAttribute("usuarioForm") UsuarioForm usuarioForm, BindingResult result, RedirectAttributes redirectAttributes) {
		//Anotando el model attribute con @Valid se fuerza a que se lanze su validación automática antes de entrar en el método.
		//En caso de detectarse errores se añaden al objeto errors.
		logger.debug("Acción de crear usuario");

		if (result.hasErrors()) {
			return crearVistaNuevoUsuario(usuarioForm);
		}

		Usuario usuario = usuarioForm.getUsuario();
		usuario = usuarioService.save(usuario);

		//Mostrar un aviso en el listado indicando que el usuario se creó correctamente
		redirectAttributes.addFlashAttribute("mensajeInfo", "Usuario creado correctamente");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/usuarios");
		return mav;
	}


	@RequestMapping(value="/nuevo", params="accion=volver", method=RequestMethod.POST)
	public ModelAndView formNuevoVolver() {
		return new ModelAndView("redirect:/usuarios");
	}








	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView formDetalle(@PathVariable("id") Long idUsuario, RedirectAttributes redirectAttributes) {
		UsuarioForm usuarioForm = new UsuarioForm();
		Usuario usuario = null;
		try {
			usuario = usuarioService.find(idUsuario);
		}
		catch (InstanceNotFoundException e) {
			redirectAttributes.addFlashAttribute("mensajeInfo", "Usuario no encontrado");
			return new ModelAndView("redirect:/usuarios");
		}

		usuarioForm.setUsuario(usuario);
		return crearVistaDetalleUsuario(usuarioForm);
	}


	@RequestMapping(value="/{id}", params="accion=actualizar", method=RequestMethod.POST)
	public ModelAndView formDetalleActualizar(@Valid @ModelAttribute("usuarioForm") UsuarioForm usuarioForm, BindingResult result, RedirectAttributes redirectAttributes) {
		//Anotando el model attribute con @Valid se fuerza a que se lanze su validación automática antes de entrar en el método.
		//En caso de detectarse errores se añaden al objeto errors.
		logger.debug("Acción de actualizar usuario");

		if (result.hasErrors()) {
			return crearVistaDetalleUsuario(usuarioForm);
		}

		Usuario usuario = usuarioForm.getUsuario();
		usuario = usuarioService.save(usuario);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/usuarios/" + usuario.getId());
		return mav;
	}


	@RequestMapping(value="/{id}", params="accion=eliminar", method=RequestMethod.POST)
	public ModelAndView formDetalleEliminar(@PathVariable("id") Long idUsuario, RedirectAttributes redirectAttributes) {
		try {
			usuarioService.remove(idUsuario);
		}
		catch (InstanceNotFoundException e) {
			logger.error("Usuario no existe");
			redirectAttributes.addFlashAttribute("mensajeInfo", "Usuario no encontrado");
			return new ModelAndView("redirect:/usuarios");
		}

		redirectAttributes.addFlashAttribute("mensajeInfo", "Usuario eliminado correctamente");
		return new ModelAndView("redirect:/usuarios");
	}

	@RequestMapping(value="/{id}", params="accion=volver", method=RequestMethod.POST)
	public ModelAndView formDetalleVolver() {
		return new ModelAndView("redirect:/usuarios");
	}





	/**
	 * Preparara el modelAndView de listado de usuarios
	 * @param usuarioForm
	 * @return
	 */
	private ModelAndView crearVistaListado(BuscadorUsuariosForm buscadorUsuariosForm) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("usuarios", usuarioService.buscar(buscadorUsuariosForm.getFiltro()));
		mav.addObject("buscadorUsuariosForm", buscadorUsuariosForm);
		mav.setViewName("app/usuarios/listadoUsuarios");
		return mav;
	}


	/**
	 * Preparara el modelAndView de nuevo usuario
	 * @param usuarioForm
	 * @return
	 */
	private ModelAndView crearVistaNuevoUsuario(UsuarioForm usuarioForm) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("tiposUsuario", tipoUsuarioService.getAll());
		mav.addObject("roles", roleService.getAll());

		mav.addObject("usuarioForm", usuarioForm);
		mav.setViewName("app/usuarios/nuevoUsuario");
		return mav;
	}


	/**
	 * Preparara el modelAndView de detalle de usuario
	 * @param usuarioForm
	 * @return
	 */
	private ModelAndView crearVistaDetalleUsuario(UsuarioForm usuarioForm) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("tiposUsuario", tipoUsuarioService.getAll());
		mav.addObject("roles", roleService.getAll());

		mav.addObject("usuarioForm", usuarioForm);
		mav.setViewName("app/usuarios/detalleUsuario");
		return mav;
	}

}
