package com.dad;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonView;

@Controller
public class AdvertController {

	@Autowired
	private AdvertRepository anuncioRepositorio;
	
	@Autowired
	private UserRepository usuarioRepositorio;
	
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
		
			
		return "resultadoBusquedasAnuncios_template";
	}
	
	@RequestMapping ("/publicar+anuncio")
	public String publicarAnuncio (Model model){
		return "enviarAnuncio";
		
	}
	
//	@RequestMapping ("/tablon-anuncios")
//	public String allAdverts (Model model, @RequestParam int num_pag) {
//		int sig_pag=num_pag+1;
//		Page<Anuncio> todos = anuncioRepositorio.findAll(new PageRequest(num_pag, 2));
//		int elementos = todos.getNumberOfElements();
//		model.addAttribute("encontrado", true);
//		model.addAttribute("anuncios",todos);
//		model.addAttribute("numPag",0);
//		model.addAttribute("numPag", sig_pag);
//		if (sig_pag >= elementos) {
//			model.addAttribute("final", true);
//		}
//		return "resultadoBusquedasAnuncios_template";
//	}
	
	
	@PostMapping ("/add+anuncio")
	public String addAdvert (Model model,
			@RequestParam String asunto,
			@RequestParam String fecha,
			@RequestParam String cuerpo){
		
		if ((asunto.equals("")) || (cuerpo.equals("")) || (fecha.equals(""))) {
			model.addAttribute("campovacio", true);
			return "enviarAnuncio";
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioRepositorio.findByLogin(username);
		Anuncio anuncio = new Anuncio(usuario,asunto,cuerpo,fecha);
		anuncioRepositorio.save(anuncio);
		return "successAdvert_template";
	}

	@RequestMapping ("/advert+added")
	public String volverTablon (Model model){
		//model.addAttribute("anuncios", anuncioRepositorio.findAll());
		return "boardUser_template";
	}
	
	@RequestMapping ("/edit+addvert")
	public String editAdvert (Model model,
			@RequestParam String id){
			long longID=Long.parseLong(id);
			Anuncio anuncio=anuncioRepositorio.findById(longID);
			model.addAttribute("asunto", anuncio.getAsunto());
			model.addAttribute("cuerpo", anuncio.getCuerpo());
			System.out.println(anuncio.getFecha());
			model.addAttribute("fecha", anuncio.getFecha());
			model.addAttribute("id", longID);
			return "edicionAnuncio_template";
		}
	@RequestMapping ("/edited+advert")
	public String editedAdvert (Model model,
			@RequestParam String asunto,
			@RequestParam String fecha,
			@RequestParam String cuerpo,
			@RequestParam Long id){
		
		if ((asunto.equals("")) || (cuerpo.equals("")) || (fecha.equals(""))) {
			model.addAttribute("campovacio", true);
			return "enviarAnuncio";
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioRepositorio.findByLogin(username);
		Anuncio anuncio = new Anuncio(usuario,asunto,cuerpo,fecha);
		anuncioRepositorio.save(anuncio);
		anuncioRepositorio.delete(id);
		return "successAdvert_template";
	}
	
//	@Autowired
//	private AdvertRepository anuncioRepositorio;
//	
//	@JsonView(Anuncio.AnuncioResultado.class)
//	@GetMapping (value="/anuncio/{id}", produces =MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Anuncio> anuncioCompleto(@PathVariable Long id){
//		Anuncio anuncioID = anuncioRepositorio.findById(id);
//		if (anuncioID==null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		} else {
//			
//			return ResponseEntity.accepted().body(anuncioID);
//		}
//		
//	}
	
	
}