package com.dad;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import com.google.gson.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/sitters")
public class SittersController {
	
	@Autowired
	private UserRepository usuarioRepositorio;
	
	@GetMapping("/")
	public String inicio(Model model) {
		return "indexRest";
	}
	
	// llamada a la aplicación rest
	
//	@JsonView(Usuario.SitterResultado.class)
//	@GetMapping(value="/{id}", produces =MediaType.APPLICATION_JSON_VALUE )
//	public ResponseEntity<Usuario> getPerfilSitter(Model model, @PathVariable Long id) {
//		Usuario sitterID = usuarioRepositorio.findById(id);
//		if (sitterID==null) { // si no se halla el sitter
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		} else {
//			
//			return ResponseEntity.accepted().body(sitterID);
//		}
//	}
	
	@JsonView(Usuario.SitterResultado.class)
	@GetMapping(value="/provincia/{provincia}/tarifamax/{tarifamax}",
				produces=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<Usuario>> getPerfilesSitter(Model model, @PathVariable String provincia, @PathVariable String tarifamax) {
		int tarifa_h;
		List<Usuario> sitters;
//		if (!tarifamax.matches("[0-9]+")) {
//			tarifamax = "";
//		}
//		
//		if(!provincia.matches("[A-Za-z]+")) {
//			provincia = "";
//		}
		
		if (provincia.equals("null")) {// si la provincia es null
			if (tarifamax == "1000") {// si la tarifa es null también
				sitters = usuarioRepositorio.findByRol("ROLE_sitter");
			} else {
				tarifa_h = Integer.parseInt(tarifamax);
				sitters = usuarioRepositorio.findByRolAndTarifaLessThan("ROLE_sitter",tarifa_h);
			}
		} else { // si tengo provincia
			if (tarifamax == "1000") {// si la tarifa es null también
				sitters = usuarioRepositorio.findByRolAndProvinciaIsLike("ROLE_sitter",provincia);
				
			} else { // si no
				tarifa_h = Integer.parseInt(tarifamax);
				sitters = usuarioRepositorio.findByRolAndProvinciaAndTarifaLessThan("ROLE_sitter",provincia,tarifa_h);
				
			}
		}
		
		if (!sitters.isEmpty()) {//se han hallado resultados
			return ResponseEntity.accepted().body(sitters);
		} else {// no hay resultados
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
//	@JsonView(Usuario.SitterResultado.class)
//	@GetMapping(value="/{provincia}/{tarifamax}/{numpag}",
//				produces=MediaType.APPLICATION_JSON_VALUE )
//	public ResponseEntity<Page<Usuario>> getPagesSitter(Model model, @PathVariable String provincia, @PathVariable String tarifamax, @PathVariable String numpag) {
//		int tarifa_h;
//		int numpagnow = Integer.parseInt(numpag);
//		int sigpag = numpagnow+1;
//		Page<Usuario> sitters;
//		//List<Usuario> sitters;
//		
//		
//		if (!tarifamax.matches("[0-9]+")) {
//			tarifamax = "";
//		}
//		
//		if(!provincia.matches("[A-Za-z]+")) {
//			provincia = "";
//		}
//		
//		if ((provincia == null) || (provincia == "")) {// si la provincia es null
//			if ((tarifamax == null) || (tarifamax == "")) {// si la tarifa es null también
//				sitters = usuarioRepositorio.findByRol("ROLE_sitter",new PageRequest(numpagnow,2));
//			} else {
//				tarifa_h = Integer.parseInt(tarifamax);
//				sitters = usuarioRepositorio.findByRolAndTarifaLessThan("ROLE_sitter",tarifa_h,new PageRequest(numpagnow,2));
//			}
//		} else { // si tengo provincia
//			if ((tarifamax == null) || (tarifamax == "")) {// si la tarifa es null también
//				sitters = usuarioRepositorio.findByRolAndProvinciaIsLike("ROLE_sitter",provincia,new PageRequest(numpagnow,2));
//				
//			} else { // si no
//				tarifa_h = Integer.parseInt(tarifamax);
//				sitters = usuarioRepositorio.findByRolAndProvinciaAndTarifaLessThan("ROLE_sitter",provincia,tarifa_h,new PageRequest(numpagnow,2));
//				
//			}
//		}
//		
//		if (sitters.getTotalElements()!=0) {//se han hallado resultados
//			return ResponseEntity.accepted().body(sitters);
//		} else {// no hay resultados
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		
//	}
	
	

}
