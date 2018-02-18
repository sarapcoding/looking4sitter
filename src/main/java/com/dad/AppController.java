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
	@Autowired
	private ProfileRepository perfilRepositorio;
	@Autowired
	private RelUPRepository upRepositorio;
	
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
	@PostMapping ("/verificacion+registro")
	public String verificarRegistro (Model model, @RequestParam String usuario, @RequestParam String contrasena,
			@RequestParam String contrasena2,@RequestParam String email, @RequestParam String tipo ){
		//"select Id from Usuarios where Login=usuario"
		List<Usuarios> coincidenciasNombre = usuarioRepositorio.findByLogin(usuario);
		List<Usuarios> coincidenciasEmail = usuarioRepositorio.findByEmail(email);
		if (coincidenciasNombre.isEmpty()){
			if (coincidenciasEmail.isEmpty()){
				if (contrasena.equals(contrasena2)){
					
					model.addAttribute("nombre_provisional",usuario);
					model.addAtribute ("email_provisional", email)
					model.addAtribute ("password_provisional", contrasena)
					model.addAtribute ("tipo_provisional", tipo)
					if (tipo==2) then {return "registroParent_template";}
					else{return "registroSitter_template"}
					
				}
				model.addAttribute("password",true);
				return "registro_template";
			}
			model.addAttribute("correo",true);
			return "registro_template";
		}
		model.addAttribute("nombre",true);
		return "registro_template";
	}	
		
	@RequestMapping ("/continuacion+registro")
	public String redireccionRegistro(Model model,@RequestParam String usuario, @RequestParam String contrasena,
			@RequestParam String email, @RequestParam String tipo, @RequestParam String nombre, @RequestParam String provincia
			, @RequestParam int tarifa, @RequestParam String descripcion){
		Usuarios nuevoUsuario = new Usuarios();
		// usuario,"Nombre",email,contrasena,"Provincia","Descrpcion"
		nuevoUsuario.setLogin(usuario);
		nuevoUsuario.setEmail(email);
		nuevoUsuario.setPassword(contrasena);
		nuevoUsuario.setNombre(nombre);
		nuevoUsuario.setProvincia (provincia);
		nuevoUsuario.setTarifa(tarifa);
		nuevoUsuario.setDescripcion(descripcion);
		
		Usuarios usuario_guardado = new Usuarios();
		usuario_guardado = usuarioRepositorio.save(nuevoUsuario);
		// Creación de la nueva relación entre un usuario y su perfil
		Relusuariosperfiles nuevaRelacion = new Relusuariosperfiles();
		nuevaRelacion.setId_Perfil(Long.parseLong(tipo));
		nuevaRelacion.setId_Usuario(usuario_guardado.getId());
		System.out.println(nuevaRelacion.toString());
		upRepositorio.save(nuevaRelacion);
		return "registroExitoso_template";
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
