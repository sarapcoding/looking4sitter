package com.dad;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdvertController {

	@Autowired
	private AdvertRepository anuncioRepositorio;
	
	int num_elem = 1;
	
	@RequestMapping ("/encontrar+anuncio")
	//public String encontrarAnuncio (Model model, @RequestParam String  ciudad, @RequestParam String fecha, @RequestParam String salario){
	public String encontrarAnuncio (Model model,@RequestParam String fecha, @RequestParam int num_pag){ 
		
		int sig_pag=num_pag+1;
		//Page<Anuncios> coincidencias_ciudad = anuncioRepositorio.findByCiudad(ciudad,new PageRequest(num_pag, num_elem));
		Page<Anuncio> coincidencias_fecha = anuncioRepositorio.findByFecha(fecha,new PageRequest(num_pag, num_elem));
		if (coincidencias_fecha.getTotalElements()==0){
			model.addAttribute("fail", true);
		} else {
			model.addAttribute("encontrado", true);
			model.addAttribute("anuncios",coincidencias_fecha);
			model.addAttribute("numPag",sig_pag);
			model.addAttribute("fechaBusqueda", fecha);
		}
		//Page<Anuncios> coincidencias_salario = anuncioRepositorio.findByTarifa(salario,new PageRequest(num_pag, num_elem));
		/*
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
		
		*/
			
		return "resultadoBusquedasAnuncios_template";
	}
	
	@RequestMapping ("/publicar+anuncio")
	public String publicarAnuncio (Model model){
		
		return "enviarAnuncio";
		
	}
	
	@RequestMapping ("/tablon-anuncios")
	public String allAdverts (Model model, @RequestParam int num_pag) {
		int sig_pag=num_pag+1;
		Page<Anuncio> todos = anuncioRepositorio.findAll(new PageRequest(num_pag, 2));
		model.addAttribute("encontrado", true);
		model.addAttribute("anuncios",todos);
		model.addAttribute("numPag",0);
		model.addAttribute("numPag", sig_pag);
		return "resultadoBusquedasAnuncios_template";
	}
	
	
	@RequestMapping ("/add+anuncio")
	public String addAdvert (HttpServletRequest request, Model model, @RequestParam String asunto,
			@RequestParam String fecha,	@RequestParam String cuerpo){
		UsuarioSesion usuario_actual = (UsuarioSesion) request.getSession().getAttribute("usuario_actual");
		Usuario usuario = usuario_actual.getUsuario();
		Anuncio anuncio = new Anuncio(usuario,asunto,cuerpo,fecha);
		//usuario.setAnuncio(anuncio);
		anuncioRepositorio.save(anuncio);
		return "successAdvert_template";
	}

	@RequestMapping ("/advert+added")
	public String volverTablon (Model model){
		//model.addAttribute("anuncios", anuncioRepositorio.findAll());
		return "boardUser_template";
	}
	@RequestMapping ("/anuncio/{id}")
	public String anuncioCompleto(Model model,@PathVariable long id){
		model.addAttribute("anuncio", anuncioRepositorio.findOne(id));
		return "advertComplete_template";
	}
}