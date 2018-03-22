
package com.dad;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.dad.UserRepository;
import com.dad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	private AdvertRepository anuncioRepositorio;
	@Autowired
	private RemarkRepository comentarioRepositorio;
	
	
	@GetMapping ("/")
	public String arranque (Model model){
		return "welcome_template";
	}
	@GetMapping ("/inicio")
	public String iniciarSesion (Model model){

		return "inicioSesion_template";
	}
	
	@GetMapping ("/inicio+sesion")
	public String verificacionInicioSesion (Model model, HttpServletRequest request){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario user = usuarioRepositorio.findByLogin(username);
		
		String mynameis = user.getNombre();
		String myprofileis = user.getRoles().get(0);
		model.addAttribute("mynameis", mynameis);
		model.addAttribute("myprofileis", myprofileis);

		model.addAttribute("padre", request.isUserInRole("ROLE_padre"));
		model.addAttribute("sitter", request.isUserInRole("ROLE_sitter"));
		model.addAttribute("star_sitter", request.isUserInRole("ROLE_starSitter"));
		model.addAttribute("centro", request.isUserInRole("ROLE_centro"));
		
		return "boardUser_template";
	}
	
	@GetMapping ("/inicio-failed")
	public String iniciarSesionFail (Model model) {
		model.addAttribute("correcto",true);
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
		
	
	
	@RequestMapping ("/entrar")
	public String entrarInvitado (Model model){
		return "principal_template";
	}
	
	
	private Usuario giveMeUser(List<Usuario> list, Long id) {
		for (Usuario x : list) {
			if(id.equals(x.getId())){
				list.remove(x);
				return x;
			}
		}
		return null;
		
	}
	
	
	// MODIFICAR
	@RequestMapping("/busqueda-avanzada-sitters")
	public String busquedaAvanzada(Model model,
			@RequestParam String provincia,
			@RequestParam String tarifa_max) {
		
		int tarifa_h;
		List<Usuario> resultado = new ArrayList();
		List<Usuario> sitters = new ArrayList();
		
		if ((provincia == null) || (provincia == "")) {// si la provincia es null
			if ((tarifa_max == null) || (tarifa_max == "")) {// si la tarifa es null también
				sitters = (List<Usuario>) usuarioRepositorio.findAll();
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
			// Sacamos los sitters
			
			 
			for (Usuario u : sitters) {
				if (u.getRoles().contains("ROLE_sitter")){
					resultado.add(u);
				}
			}
			
			if (resultado.isEmpty()) { // no hay sitters
				model.addAttribute("vacio",true);
				return "resultadoBusquedasSitters_template";
			}
			model.addAttribute("resultadofinal",resultado);
			
		} else {// no hay resultados
			model.addAttribute("vacio",true);
		}
		return "resultadoBusquedasSitters_template";
	}
	
	
	
	@GetMapping ("/perfil-usuario")
	public String perfilUsuario (Model model){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
	
		Usuario user = usuarioRepositorio.findByLogin(username);
		
		String mynameis = user.getNombre();
		List<String> myrolesare = user.getRoles();
		model.addAttribute("mynameis", mynameis);
		

		if (myrolesare.contains("ROLE_padre")) {
			model.addAttribute("padre", true);
			model.addAttribute("myprofileis", "PADRE");
			
		} else if (myrolesare.contains("ROLE_sitter")) {
			model.addAttribute("sitter", true);
			model.addAttribute("myprofileis","SITTER" );
			
		} else if (myrolesare.contains("ROLE_starsitter")) {
			model.addAttribute("star_sitter", true);
			model.addAttribute("myprofileis","STAR SITTER" );
		
		} else if (myrolesare.contains("ROLE_centro")) {
			model.addAttribute("centro", true);
			model.addAttribute("myprofileis","CENTRO" );
		}
		return "boardUser_template";
		
		
	}
	
	@RequestMapping ("/cerrar-sesion")
	public String cerrarSesion (HttpServletRequest request,Model model) {
		return "cerrarSesion_template";
		
	}
	
}
