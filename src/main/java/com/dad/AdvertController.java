package com.dad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdvertController {

	@Autowired
	private AdvertRepository anuncioRepositorio;
	@RequestMapping ("/encontrar+anuncio")
	public String encontrarAnuncio (Model model, @RequestParam String  ciudad, @RequestParam String fecha,
			@RequestParam String salario){
		int num_pag = 0;
		int num_elem = 4;
		Page<Anuncios> coincidencias_ciudad = anuncioRepositorio.findByCiudad(ciudad,new PageRequest(num_pag, num_elem));
		Page<Anuncios> coincidencias_fecha = anuncioRepositorio.findByFecha(fecha,new PageRequest(num_pag, num_elem));
		Page<Anuncios> coincidencias_salario = anuncioRepositorio.findByTarifa(salario,new PageRequest(num_pag, num_elem));
		if (coincidencias_ciudad.getTotalElements()==0){
			if (coincidencias_fecha.getTotalElements()==0){
				if (coincidencias_salario.getTotalElements()==0){
					model.addAttribute("fail", true);
				}
				model.addAttribute("anuncios", coincidencias_salario);
			}if (coincidencias_salario.getTotalElements()==0){
				model.addAttribute("anuncios",coincidencias_fecha);
			}
			Page<Anuncios> coincidencia_combinada = anuncioRepositorio.findByFechaAndTarifa(fecha, salario, new PageRequest(num_pag, num_elem));
			model.addAttribute("anuncios", coincidencia_combinada);
		}if (coincidencias_fecha.getTotalElements()==0){
			if (coincidencias_salario.getTotalElements()==0){
				model.addAttribute("anuncios", coincidencias_ciudad);
			}Page<Anuncios> coincidencia_combinada = anuncioRepositorio.findByCiudadAndTarifa(ciudad, salario, new PageRequest(num_pag, num_elem));
			model.addAttribute("anuncios",coincidencia_combinada);
		}if (coincidencias_salario.getTotalElements()==0){
			Page<Anuncios> coincidencia_combinada = anuncioRepositorio.findByCiudadAndFecha(ciudad, fecha, new PageRequest(num_pag, num_elem));
			model.addAttribute("anuncios",coincidencia_combinada);
		}
		Page<Anuncios> coincidencia_combinada = anuncioRepositorio.findByCiudadAndTarifaAndFecha(ciudad, salario, fecha, new PageRequest(num_pag, num_elem));
		model.addAttribute("anuncios", coincidencia_combinada);
			
		return "boardSitter_template";
	}
	
	@RequestMapping ("/publicar+anuncio")
	public String publicarAnuncio (Model model){
		return "enviarAnuncio";
		
	}
	@RequestMapping ("/add+anuncio")
	public String addAdvert (Model model,@RequestParam String  login, @RequestParam String asunto,
			@RequestParam String fecha,	@RequestParam String cuerpo){
		Anuncios anuncio = new Anuncios();
		anuncio.setNombre(login);
		anuncio.setAsunto(asunto);
		anuncio.setFecha(fecha);
		anuncio.setCuerpo(cuerpo);
		anuncioRepositorio.save(anuncio);
		return "successAdvert_template";
	}

	@RequestMapping ("/advert+added")
	public String volverTablon (Model model){
		model.addAttribute("anuncios", anuncioRepositorio.findAll());
		return "boardParent_template";
	}
	@RequestMapping ("/anuncio/{id}")
	public String anuncioCompleto(Model model,@PathVariable long id){
		model.addAttribute("anuncio", anuncioRepositorio.findOne(id));
		return "advertComplete_template";
	}
}
