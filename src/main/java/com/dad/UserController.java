package com.dad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@Autowired 
	private UserRepository usuarioRepositorio;

	@Autowired
	private AgendaRepository agendaRepositry;
	
	@Autowired
	private HoraRepository horaRepository;
	
	@PostMapping ("/verificacion+registro")
	public String verificarRegistro (Model model, @RequestParam String usuario, @RequestParam String contrasena,
			@RequestParam String contrasena2,@RequestParam String email, @RequestParam String tipo ){
		//"select Id from Usuarios where Login=usuario"
		Usuario coincidenciasNombre = usuarioRepositorio.findByLogin(usuario);
		Usuario coincidenciasEmail = usuarioRepositorio.findByEmail(email);
		if (coincidenciasNombre == null){
			if (coincidenciasEmail==null){
				if (contrasena.equals(contrasena2)){
					
					model.addAttribute("nombre_provisional",usuario);
					model.addAttribute ("email_provisional", email);
					model.addAttribute ("password_provisional", contrasena);
					model.addAttribute ("tipo_provisional", tipo);
					if (tipo.equals("2"))  {return "registroParent_template";}
					else{return "registroSitter_template";}
					
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
	public String redireccionRegistro(Model model,@RequestParam String login, @RequestParam String password,
			@RequestParam String email, @RequestParam String tipo, @RequestParam String nombre, @RequestParam String provincia
			, @RequestParam int tarifa, @RequestParam String descripcion){
		Usuario nuevoUsuario = new Usuario();
		//String perfil = perfilRepositorio.findOne(Long.parseLong(tipo));
		// usuario,"Nombre",email,contrasena,"Provincia","Descrpcion"
		nuevoUsuario.setLogin(login);
		nuevoUsuario.setEmail(email);
		nuevoUsuario.setPasswordHash(password);
		nuevoUsuario.setNombre(nombre);
		nuevoUsuario.setProvincia (provincia);
		nuevoUsuario.setTarifa(tarifa);
		nuevoUsuario.setDescripcion(descripcion);
		nuevoUsuario.setRol(tipo);
		Usuario usuario_guardado = new Usuario();
		usuario_guardado = usuarioRepositorio.save(nuevoUsuario);
		// Creación de la nueva relación entre un usuario y su perfil
		//perfil.getUsuario().add(nuevoUsuario);
		return "registroExitoso_template";
	}
	
	@RequestMapping("/perfil-sitter")
	public String checkSitterProfile() {
		return "perfilSitter_template";
	}
	
	@RequestMapping ("/anuncio/{login}")
	public String usuarioPerfil(Model model,@PathVariable String login ){
		Usuario usuario = usuarioRepositorio.findByLogin(login);
		if (usuario.getRol().equals("ROLE_sitter")){
			model.addAttribute("sitter", true);
		}
		
		model.addAttribute("usuario", usuario);
		return "perfilUser_template";
	}
	
	
	
}