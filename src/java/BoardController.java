package com.dad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

	//@Autowired
	//private AdvertRepository anuncioRepositorio;
	@PostMapping("/nuevoAnuncio")
	public String publicarAnuncio(Model model, Advert anuncio) {

		/*anuncioRepositorio.save(anuncio);
		model.addAttribute("anuncios", anuncioRepositorio.findAll());
		*/
		model.addAttribute("anuncios", anuncio);
		return "advert_list";

	}

	@PostMapping("/busquedaAnuncio")
	public String anuncios(Model model, @RequestParam String nombre, @RequestParam String asunto) {
		List <Advert>anuncios=anuncioRepositorio.findByNombreAndAsunto(nombre, asunto);
		//implementar una funcion para cada atributo que devuelva su valor tipo name: this.getName, etc...
		model.addAttribute("anuncios", anuncios);
		return "advert_list";
	}
}
