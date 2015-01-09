package es.pruebas.springmvc.web.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends AbstractBaseController {

	@RequestMapping("/home")
	private String home(ModelMap model) {
		model.put("fecha", new Date());

		//Pese a tener registrado tiles como view resolver, también se pueden seguir
		//devolviendo vistas de jsp, al mantener este resolver.
		return "home";
	}

}
