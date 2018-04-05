//package com.dad;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.annotation.JsonView;
//
//@RestController
//public class AnuncioRestResultado {
//	@Autowired
//	private AdvertRepository anuncioRepositorio;
//	
//	@JsonView(Anuncio.AnuncioResultado.class)
//	@GetMapping ("/anuncio/{id}")
//	public ResponseEntity<Anuncio> anuncioCompleto(@PathVariable Long id){
//		Anuncio anuncioID = anuncioRepositorio.findById(id);
//		if (anuncioID==null) { // si no se halla el sitter
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		} else {
//			
//			return ResponseEntity.accepted().body(anuncioID);
//		}
//		
//	}
//}
