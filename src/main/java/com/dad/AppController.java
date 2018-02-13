package com.dad;

import java.util.List;
import com.dad.UserRepository;
import com.dad.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller 
public class AppController {
	@Autowired 
	private UserRepository usuarioRepositorio;
	
	@RequestMapping ("/")
	public String arranque (Model model){
		return "welcome_template";
	}
	@RequestMapping ("/inicio")
	public String iniciarSesion (Model model){
		return "inicioSesion_template";
	}
	@PostMapping ("/inicio+sesion")
	public String verificacionInicioSesion (Model model, @RequestParam String usuario, @RequestParam String contraseña){
		
		//return "boardSitter_template";
		return "boardParent_template";
	}

	@RequestMapping ("/registro")
	public String registrarUsuario (Model model){
		return "registro_template";
	}
	@PostMapping ("/verificacion+registro")
	public String verificarRegistro (Model model, @RequestParam String usuario, @RequestParam String contrasena,
			@RequestParam String contrasena2,@RequestParam String email, @RequestParam String tipo ){
		//"select Id from Usuarios where Login=usuario"
		List<User> coincidenciasNombre = usuarioRepositorio.findByLogin(usuario);
		List<User> coincidenciasEmail = usuarioRepositorio.findByEmail(email);
		if (coincidenciasNombre.isEmpty()){
			if (coincidenciasEmail.isEmpty()){
				if (contrasena.equals(contrasena2)){
					User nuevoUsuario = new User();
					// usuario,"Nombre",email,contrasena,"Provincia","Descrpcion"
					nuevoUsuario.setLogin(usuario);
					nuevoUsuario.setEmail(email);
					nuevoUsuario.setPassword(contrasena);
					usuarioRepositorio.save(nuevoUsuario);
					return "registroExistoso_template";
				}
				boolean password = false;
				return "registro_template";
			}
			boolean correo = false;
			return "registro_template";
		}
		boolean nombre=false;
		return "registro_template";
	}	
		
	//@RequestMapping ("/registro+exito")
	//public String redireccionRegistro(Model model){}	
		
		
		
	
	@RequestMapping ("/entrar")
	public String entrarInvitado (Model model){
		return "principal_template";
	}
	}
