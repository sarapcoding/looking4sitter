package com.dad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@Autowired 
	private UserRepository usuarioRepositorio;
	
	@Autowired
	private AdvertRepository anuncioRepositorio;
	
	@PostMapping ("/verificacion-registro")
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
		
	@RequestMapping ("/continuacion-registro")
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
		if (tipo.equals("2")) { // padre
			nuevoUsuario.setRol("ROLE_padre");
		} else if (tipo.equals("3")) {
			nuevoUsuario.setRol("ROLE_sitter");
		}
		
		Usuario usuario_guardado = new Usuario();
		usuario_guardado = usuarioRepositorio.save(nuevoUsuario);
		return "registroExitoso_template";
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
	
	@GetMapping("/mis-anuncios-publicados")
	public String perfilAnuncios(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		List<Anuncio> misanuncios = anuncioRepositorio.findByLoginUsuario(username);
		
		if (misanuncios.isEmpty()) {
			model.addAttribute("vacio", true);
		} else {
			List<String> fechas = new ArrayList();
			List<String> asuntos = new ArrayList();
			List<String> cuerpos = new ArrayList();
			List<Long> ids = new ArrayList<Long>();
			for (Anuncio a : misanuncios) {
				fechas.add(a.getFecha());
				asuntos.add(a.getAsunto());
				cuerpos.add(a.getCuerpo());
				ids.add(a.getId());
			}
			model.addAttribute("fechas",fechas);
			model.addAttribute("asuntos",asuntos);
			model.addAttribute("cuerpos",cuerpos);
			model.addAttribute("ids", ids);
			//model.addAttribute("misanuncios",misanuncios);
			model.addAttribute("encontrado", true);
		}
		
		return "perfilAnuncios_template";
	}
	
	
	
}