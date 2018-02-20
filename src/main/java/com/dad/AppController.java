
package com.dad;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.ModelAndView;


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
	public String verificacionInicioSesion (HttpServletRequest request,Model model, @RequestParam String usuario, @RequestParam String contrasena){
		List<Usuarios> list_u = usuarioRepositorio.findByLogin(usuario);
		if (list_u.isEmpty()) {
			// Usuario no encontrado
			model.addAttribute("correcto",true);
			return "inicioSesion_template";
		}
		Usuarios usuario_encontrado = list_u.get(0);
		// Comparacion contraseña
		if (usuario_encontrado.getPassword().equals(contrasena)) {
			Long id_user = usuario_encontrado.getId();
			List<Relusuariosperfiles> list_rel = upRepositorio.findByIdusuario(id_user);
			Relusuariosperfiles rel = list_rel.get(0);
			Long id_perfil = rel.getIdperfil();
			List<Perfiles> list_perf = perfilRepositorio.findById(id_perfil);
			Perfiles perfil = list_perf.get(0);
			
			UsuarioSesion usuario_actual = new UsuarioSesion(usuario_encontrado,perfil);
			request.getSession().setAttribute("usuario_actual",usuario_actual);
			String mynameis = usuario_actual.getUsuario().getNombre();
			String myprofileis = usuario_actual.getPerfil().getNombre();
			model.addAttribute("mynameis", mynameis);
			model.addAttribute("myprofileis", myprofileis);
			
			//UsuarioSesion usuario_actual = (UsuarioSesion) request.getSession.getAttribute("usuario_actual");
			
			
			if (usuario_actual.getPerfil().getNombre().toUpperCase().equals("PADRE")) {
				model.addAttribute("padre", true);
			} else if (usuario_actual.getPerfil().getNombre().toUpperCase().equals("SITTER")) {
				model.addAttribute("sitter", true);
			} else if (usuario_actual.getPerfil().getNombre().toUpperCase().equals("STAR SITTER")) {
				model.addAttribute("star_sitter", true);
			} else if (usuario_actual.getPerfil().getNombre().toUpperCase().equals("CENTRO")) {
				model.addAttribute("centro", true);
			}
			
			
			return "boardUser_template";
		}
		model.addAttribute("correcto",true);
		return "inicioSesion_template";
	}
	
	@RequestMapping ("/prueba-sesion")
	public String pruebaSesion (HttpServletRequest request, Model model) {
		UsuarioSesion usuario_actual = (UsuarioSesion) request.getSession().getAttribute("usuario_actual");
		if (usuario_actual != null) {
			String nombre = usuario_actual.getUsuario().getNombre();
			String perfil = usuario_actual.getPerfil().getNombre();
			model.addAttribute("nombreusuario", nombre);
			model.addAttribute("perfilusuario", perfil);
			return "boardUser_template";
		}
		return "inicioSesion_template";
	}
	

	@RequestMapping ("/registro")
	public String registrarUsuario (Model model){
		return "registro_template";
	}
	
		
	@RequestMapping ("/exito+registro")
	public String redireccionRegistro(Model model){
		//nombre_provisional
		return "continuacionRegistro_template";
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
	
	
	private Usuarios giveMeUser(List<Usuarios> list, Long id) {
		for (Usuarios x : list) {
			if(id.equals(x.getId())){
				list.remove(x);
				return x;
			}
		}
		return null;
		
	}
	
	@RequestMapping("/busqueda-avanzada-sitters")
	public String busquedaAvanzada(Model model,
			@RequestParam String provincia,
			@RequestParam String tarifa_max) {
		/*
		String prov_q = new String();
		String tar_q = new String();
		*/
		int tarifa_h;
		List<Usuarios> resultado = new ArrayList<>();
		List<Usuarios> sitters = new ArrayList<>();
		
		if ((provincia == null) || (provincia == "")) {// si la provincia es null
			if ((tarifa_max == null) || (tarifa_max == "")) {// si la tarifa es null también
				sitters = (List<Usuarios>) usuarioRepositorio.findAll();
			} else {
				tarifa_h = Integer.parseInt(tarifa_max);
				sitters = usuarioRepositorio.findByTarifa(tarifa_h);
			}
		} else { // si tengo provincia
			if ((tarifa_max == null) || (tarifa_max == "")) {// si la tarifa es null también
				sitters = usuarioRepositorio.findByProvinciaIsLike(provincia);
			} else { // si no
				tarifa_h = Integer.parseInt(tarifa_max);
				sitters = usuarioRepositorio.findByProvinciaAndTarifa(provincia, tarifa_h);
			}
		}
		
		if (!sitters.isEmpty()) {//se han hallado resultados
			model.addAttribute("encontrado",true);
			List<Perfiles> list_s = perfilRepositorio.findByNombre("Sitter");
			Perfiles sitt = list_s.get(0);
			Long idperfils = sitt.getId();
			
			List<Relusuariosperfiles> list_sitters = upRepositorio.findByIdperfil(idperfils);//sitter
			
			for (Relusuariosperfiles s : list_sitters) {
				Long idusuario = s.getIdusuario(); // consigo un id de usuario
				// buscar en la lista de "sitters" el usuario que tenga ese id
				Usuarios u = giveMeUser(sitters,idusuario);
				if (u != null) {
					resultado.add(u);
				}
			}
			if (resultado.isEmpty()) {
				model.addAttribute("vacio",true);
				return "resultadoBusquedasSitters_template";
			}
			model.addAttribute("resultadofinal",resultado);
			
		} else {// no hay resultados
			model.addAttribute("vacio",true);
		}
		return "resultadoBusquedasSitters_template";
	}
	
	
	
	@RequestMapping ("/perfil-usuario")
	public String perfilUsuario (HttpServletRequest request,Model model){
		UsuarioSesion usuario_actual = (UsuarioSesion) request.getSession().getAttribute("usuario_actual");
		String mynameis = usuario_actual.getUsuario().getNombre();
		String myprofileis = usuario_actual.getPerfil().getNombre();
		model.addAttribute("mynameis", mynameis);
		model.addAttribute("myprofileis", myprofileis);
		
		if (usuario_actual.getPerfil().getNombre().toUpperCase().equals("PADRE")) {
			model.addAttribute("padre", true);
		} else if (usuario_actual.getPerfil().getNombre().toUpperCase().equals("SITTER")) {
			model.addAttribute("sitter", true);
		} else if (usuario_actual.getPerfil().getNombre().toUpperCase().equals("STAR SITTER")) {
			model.addAttribute("star_sitter", true);
		} else if (usuario_actual.getPerfil().getNombre().toUpperCase().equals("CENTRO")) {
			model.addAttribute("centro", true);
		}
		
		return "boardUser_template";
		
	}
	
	
	
	}
