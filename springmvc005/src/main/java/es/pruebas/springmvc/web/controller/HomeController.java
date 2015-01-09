package es.pruebas.springmvc.web.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/home")
	private String home(ModelMap model) {
		model.put("fecha", new Date());
		return "home";
	}

}
