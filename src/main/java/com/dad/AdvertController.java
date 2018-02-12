package com.dad;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdvertController {

	@RequestMapping ("/encontrar+anuncio")
	public String encontrarAnuncio (Model model, @RequestParam String  ciudad, @RequestParam String fecha,
			@RequestParam String salario){
		return "boardSitter_template";
	}
	
	@RequestMapping ("/publicar+anuncio")
	public String publicarAnuncio (Model model){
		return "enviarAnuncio";
		
	}
	@RequestMapping ("/add+anuncio")
	public String addAdvert (Model model){
		return "successAdvert_template";
	}
}
