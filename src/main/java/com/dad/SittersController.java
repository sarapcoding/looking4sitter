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
	
	@JsonView(Usuario.SitterResultado.class)
	@GetMapping(value="/{id}")
	public ResponseEntity<Usuario> getPerfilSitter(@PathVariable Long id) {
		Usuario sitterID = usuarioRepositorio.findById(id);
		if (sitterID==null) { // si no se halla el sitter
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(sitterID, HttpStatus.OK);
		}
	}
	
	@JsonView(Usuario.SitterResultado.class)
	@GetMapping(value="/")
	public List<Usuario> getListaSitters(){
		List<Usuario> sitters = usuarioRepositorio.findByRol("ROLE_sitter");
		return sitters;
	}

}
