package com.prueba.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {
	@RequestMapping("/")
	public String hola(Model model) {
		model.addAttribute("name", "World");
		return "hola_mundo";
	}
}
