
package com.dad;

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
			/*Búsqueda del tipo de usuario:
			 * carga id del usuario
			 * buscar en relacion usuario-perfil el perfil
			 * cargar el tipo de perfil de la tabla de perfiles
			 * pasar el tipo con model.addattribute para la carga del boarduser_template
			 * 
			 * */
			Long id_user = usuario_encontrado.getId();
			List<Relusuariosperfiles> list_rel = upRepositorio.findByIdusuario(id_user);
			Relusuariosperfiles rel = list_rel.get(0);
			Long id_perfil = rel.getIdperfil();
			List<Perfiles> list_perf = perfilRepositorio.findById(id_perfil);
			Perfiles perfil = list_perf.get(0);
			String nombre = usuario_encontrado.getNombre();
			String perfil_n = perfil.getNombre();
			model.addAttribute("mynameis", nombre.toString());
			model.addAttribute("myprofileis", perfil_n.toString());
			return "pruebaSesion_template";
			//UsuarioSesion usuario_actual = new UsuarioSesion(usuario_encontrado,perfil);
			//request.getSession().setAttribute("usuario_actual",usuario_actual);
			//UsuarioSesion usuario_actual = (UsuarioSesion) request.getSession.getAttribute("usuario_actual");
			//return "boardUser_template";
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
			return "pruebaSesion_template";
		}
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
	
	}
