package com.dad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/sitters")
public class SittersController {
	
	@Autowired
	private UserRepository usuarioRepositorio;
	
	@GetMapping(value="/{id}")
    public ResponseEntity<Usuario> getPerfilSitter(@PathVariable Long id) {
        Usuario sitterID = usuarioRepositorio.findById(id);
        if (sitterID==null) { // si no se halla el sitter
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
        	return new ResponseEntity<>(sitterID, HttpStatus.OK);
        }
    }
	
	@GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
	public String getPerfilSitterHTML(Model model,@PathVariable Long id) {
		Usuario sitterID = usuarioRepositorio.findById(id);
		if (sitterID==null) { // si no se halla el sitter
        	model.addAttribute("noEncontrado", true);
        } else {
        	model.addAttribute("encontrado", true);
        	model.addAttribute("nombreSitter", sitterID.getNombre());
        	model.addAttribute("apellidoSitter", sitterID.getApellido());
        	model.addAttribute("tarifaSitter", sitterID.getTarifa());
        	model.addAttribute("loginSitter", sitterID.getLogin());
        	model.addAttribute("provinciaSitter", sitterID.getProvincia());
        	model.addAttribute("descSitter", sitterID.getDescripcion());
        	model.addAttribute("nohorasLibres",true);
        	// horas libres
//        	Agenda sitterAgenda = sitterID.getAgenda();
//        	List<Hora> horas = sitterAgenda.getHora();
//        	List<Hora> horasLibres = new ArrayList<>();
//        	for (Hora h : horas) {
//        		if (h.isLibre()) {
//        			horasLibres.add(h);
//        		}
//        	}
//        	if (horasLibres.isEmpty()) {
//        		model.addAttribute("nohorasLibres",true);
//        	} else {
//        		model.addAttribute("horasLibres", true);
//        		model.addAttribute("horasSitter", horasLibres);
//        	}
        }
	    return "perfilSitter_template";
	}
	
	@JsonView(Usuario.SitterResultado.class)
	@GetMapping(value="/")
	public List<Usuario> getListaSitters(){
		List<Usuario> sitters = usuarioRepositorio.findByRol("ROLE_sitter");
		return sitters;
	}

}
