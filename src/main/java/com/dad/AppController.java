package com.dad;

import java.util.List;
import com.dad.UserRepository;
import com.dad.Usuarios;
import com.dad.Perfiles;
import com.dad.ProfileRepository;
import com.dad.Relusuariosperfiles;
import com.dad.RelUPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller 
public class AppController {
	@Autowired 
	private UserRepository usuarioRepositorio;
	/*@Autowired
	private ProfileRepository perfilRepositorio;
	@Autowired
	private RelUPRepository upRepositorio;
	*/
	@RequestMapping ("/")
	public String arranque (Model model){
		return "welcome_template";
	}
	@RequestMapping ("/inicio")
	public String iniciarSesion (Model model){
		return "inicioSesion_template";
	}
	@PostMapping ("/inicio+sesion")
	public String verificacionInicioSesion (Model model, @RequestParam String usuario, @RequestParam String contrasena){
		List<Usuarios> list_u = usuarioRepositorio.findByLogin(usuario);
		if (list_u.isEmpty()) {
			// Usuario no encontrado
			model.addAttribute("correcto",true);
			return "inicioSesion_template";
		}
		Usuarios usuario_encontrado = list_u.get(0);
		// Comparacion contraseña
		if (usuario_encontrado.getPassword().equals(contrasena)) {
			return "boardParent_template";
		}
		model.addAttribute("correcto",true);
		return "inicioSesion_template";
	}

	@RequestMapping ("/registro")
	public String registrarUsuario (Model model){
		return "registro_template";
	}
		
		
	/*
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such order")	
	@ExceptionHandler(OrderNotFoundException.class)
	public String errorPagina() {
		return "error_pagina";
	}
	*/
	
	@RequestMapping ("/entrar")
	public String entrarInvitado (Model model){
		return "principal_template";
	}
	
	}
