package com.dad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdvertController {

	@Autowired
	private AdvertRepository anuncioRepositorio;
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
	public String addAdvert (Model model, Anuncios anuncio){
		anuncioRepositorio.save(anuncio);
		return "successAdvert_template";
	}

	@RequestMapping ("/advert+added")
	public String volverTablon (Model model){
		model.addAttribute("anuncios", anuncioRepositorio.findAll());
		return "boardParent_template";
	}
}
