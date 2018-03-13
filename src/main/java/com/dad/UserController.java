package com.dad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@Autowired 
	private UserRepository usuarioRepositorio;
	@Autowired
	private ProfileRepository perfilRepositorio;

	/*
	@PostMapping ("/verificacion+registro")
	public String verificarRegistro (Model model, @RequestParam String usuario, @RequestParam String contrasena,
			@RequestParam String contrasena2,@RequestParam String email, @RequestParam String tipo ){
		//"select Id from Usuarios where Login=usuario"
		List<Usuarios> coincidenciasNombre = usuarioRepositorio.findByLogin(usuario);
		List<Usuarios> coincidenciasEmail = usuarioRepositorio.findByEmail(email);
		if (coincidenciasNombre.isEmpty()){
			if (coincidenciasEmail.isEmpty()){
				if (contrasena.equals(contrasena2)){
					Usuarios nuevoUsuario = new Usuarios();
					// usuario,"Nombre",email,contrasena,"Provincia","Descrpcion"
					nuevoUsuario.setLogin(usuario);
					nuevoUsuario.setEmail(email);
					nuevoUsuario.setPassword(contrasena);
					Usuarios usuario_guardado = new Usuarios();
					usuario_guardado = usuarioRepositorio.save(nuevoUsuario);
					// Creación de la nueva relación entre un usuario y su perfil
					Relusuariosperfiles nuevaRelacion = new Relusuariosperfiles();
					nuevaRelacion.setIdperfil(Long.parseLong(tipo));
					nuevaRelacion.setIdusuario(usuario_guardado.getId());
					System.out.println(nuevaRelacion.toString());
					upRepositorio.save(nuevaRelacion);
					model.addAttribute("nombre_provisional",usuario);
					return "registroExitoso_template";
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
	*/
	
	@PostMapping ("/verificacion+registro")
	public String verificarRegistro (Model model, @RequestParam String usuario, @RequestParam String contrasena,
			@RequestParam String contrasena2,@RequestParam String email, @RequestParam String tipo ){
		//"select Id from Usuarios where Login=usuario"
		List<Usuario> coincidenciasNombre = usuarioRepositorio.findByLogin(usuario);
		List<Usuario> coincidenciasEmail = usuarioRepositorio.findByEmail(email);
		if (coincidenciasNombre.isEmpty()){
			if (coincidenciasEmail.isEmpty()){
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
}