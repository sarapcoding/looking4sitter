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
		if (!tarifamax.matches("[0-9]+")) {
			tarifamax = "";
		}
		
		if(!provincia.matches("[A-Za-z]+")) {
			provincia = "";
		}
		
		if ((provincia == null) || (provincia == "")) {// si la provincia es null
			if ((tarifamax == null) || (tarifamax == "")) {// si la tarifa es null también
				sitters = usuarioRepositorio.findByRol("ROLE_sitter");
			} else {
				tarifa_h = Integer.parseInt(tarifamax);
				sitters = usuarioRepositorio.findByRolAndTarifaLessThan("ROLE_sitter",tarifa_h);
			}
		} else { // si tengo provincia
			if ((tarifamax == null) || (tarifamax == "")) {// si la tarifa es null también
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
//	@GetMapping(value="/provincia/{provincia}/tarifamax/{tarifamax}",
//					//method = RequestMethod.GET,
//					produces=MediaType.APPLICATION_JSON_VALUE
//					)
//	public String getPerfilesBusqueda(
//			@PathParam("provincia") String provincia,
//			@PathParam("tarifamax") String tarifamax) {
//		//System.out.println("Me han llegado los parámetros: "+provincia+" - "+tarifamax);
//		System.console().writer().println("######################## "+provincia+tarifamax+" ########################");
//		int tarifa_h;
//		List<Usuario> sitters;
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
//				sitters = usuarioRepositorio.findByRol("ROLE_sitter");
//			} else {
//				tarifa_h = Integer.parseInt(tarifamax);
//				sitters = usuarioRepositorio.findByRolAndTarifaLessThan("ROLE_sitter",tarifa_h);
//			}
//		} else { // si tengo provincia
//			if ((tarifamax == null) || (tarifamax == "")) {// si la tarifa es null también
//				sitters = usuarioRepositorio.findByRolAndProvinciaIsLike("ROLE_sitter",provincia);
//				
//			} else { // si no
//				tarifa_h = Integer.parseInt(tarifamax);
//				sitters = usuarioRepositorio.findByRolAndProvinciaAndTarifaLessThan("ROLE_sitter",provincia,tarifa_h);
//				
//			}
//		}
//		String json = new Gson().toJson(sitters);
//		return json;
//		
//	}
	

}
