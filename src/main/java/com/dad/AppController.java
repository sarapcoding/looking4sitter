
package com.dad;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import com.dad.UserRepository;
import com.dad.Usuario;
import com.mysql.jdbc.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
public class AppController {
	@Autowired 
	private UserRepository usuarioRepositorio;
	
	
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
		String myprofileis = user.getRol();
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
	

//	@RequestMapping("/busqueda-avanzada-sitters")
//	public String busquedaAvanzada(Model model,
//			@RequestParam String provincia,
//			@RequestParam String tarifa_max) {
//		
//		int tarifa_h;
//		List<Usuario> resultado = new ArrayList<>();
//		List<Usuario> sitters = new ArrayList<>();
//		List<String> logins = new ArrayList<>();
//		List<String> ids = new ArrayList<>();
//		List<String> tarifas = new ArrayList<>();
//		List<String> provincias = new ArrayList<>();
//		
//		if ((provincia == null) || (provincia == "")) {// si la provincia es null
//			if ((tarifa_max == null) || (tarifa_max == "")) {// si la tarifa es null también
//				sitters = (List<Usuario>) usuarioRepositorio.findAll();
//			} else {
//				tarifa_h = Integer.parseInt(tarifa_max);
//				sitters = usuarioRepositorio.findByTarifaAndRol(tarifa_h,"ROLE_sitter");
//			}
//		} else { // si tengo provincia
//			if ((tarifa_max == null) || (tarifa_max == "")) {// si la tarifa es null también
//				sitters = usuarioRepositorio.findByProvinciaAndRol(provincia,"ROLE_sitter");
//			} else { // si no
//				tarifa_h = Integer.parseInt(tarifa_max);
//				sitters = usuarioRepositorio.findByProvinciaAndTarifaAndRol(provincia, tarifa_h,"ROLE_sitter");
//			}
//		}
//		
//		if (!sitters.isEmpty()) {//se han hallado resultados
//			model.addAttribute("encontrado",true);
//			for (Usuario s : sitters) {
//				logins.add(s.getLogin());
//				ids.add(s.getId().toString());
//				tarifas.add(Integer.toString(s.getTarifa()));
//				provincias.add(s.getProvincia());
//			}
//			// lista provincias
//			model.addAttribute("resultadoprovincias",provincias);
//			// lista tarifas
//			model.addAttribute("resultadotarifas",tarifas);
//			// lista logins
//			model.addAttribute("resultadologins",logins);
//			// lista ids
//			model.addAttribute("resultadoids",ids);
//			// lista objetos
//			model.addAttribute("resultadofinal",sitters);
//			
//		} else {// no hay resultados
//			model.addAttribute("vacio",true);
//		}
//		return "resultadoBusquedasSitters_template";
//	}
	
	@RequestMapping("/busqueda-avanzada-sitters")
	public String busquedaAvanzada(Model model, @RequestParam String provincia,@RequestParam String tarifa_max,
		@RequestParam int num_pag){
		int sig_pag=num_pag+1;
		int tarifa_h;
		Page<Usuario> sitters;
		
		// conversion: si tarifa no es un número y provincia no tiene a-z
		if (!StringUtils.isStrictlyNumeric(tarifa_max)) {
			tarifa_max = "";
		}
		
		if(!provincia.matches("[A-Za-z]+")) {
			provincia = "";
		}
		
		if ((provincia == null) || (provincia == "")) {// si la provincia es null
			if ((tarifa_max == null) || (tarifa_max == "")) {// si la tarifa es null también
				sitters = usuarioRepositorio.findByRol("ROLE_sitter",new PageRequest(num_pag,2));
			} else {
				tarifa_h = Integer.parseInt(tarifa_max);
				sitters = usuarioRepositorio.findByRolAndTarifaLessThan("ROLE_sitter",tarifa_h,new PageRequest(num_pag, 2));
			}
		} else { // si tengo provincia
			if ((tarifa_max == null) || (tarifa_max == "")) {// si la tarifa es null también
				sitters = usuarioRepositorio.findByProvinciaIsLike(provincia,new PageRequest(num_pag, 2));
				
			} else { // si no
				tarifa_h = Integer.parseInt(tarifa_max);
				sitters = usuarioRepositorio.findByProvinciaAndRolAndTarifaLessThan(provincia, "ROLE_sitter",tarifa_h,
						new PageRequest(num_pag, 2));
				
			}
		}
		
		if (sitters.getTotalElements()!=0) {//se han hallado resultados
			model.addAttribute("encontrado",true);
			// Enviamos los sitters resultantes
			model.addAttribute("resultadofinal",sitters);
			
		} else {// no hay resultados
			model.addAttribute("vacio",true);
		}
		model.addAttribute("numPag",sig_pag);
		model.addAttribute("provincia", provincia);
		model.addAttribute("tarifa_max", tarifa_max);
		return "resultadoBusquedasSitters_template";
	}
	
	
	
	@GetMapping ("/perfil-usuario")
	public String perfilUsuario (Model model){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
	
		Usuario user = usuarioRepositorio.findByLogin(username);
		
		String mynameis = user.getNombre();
		String myrolis = user.getRol();
		
		model.addAttribute("mynameis", mynameis);
		

		if (myrolis.contains("ROLE_padre")) {
			model.addAttribute("padre", true);
			model.addAttribute("myprofileis", "PADRE");
			
		} else if (myrolis.contains("ROLE_sitter")) {
			model.addAttribute("sitter", true);
			model.addAttribute("myprofileis","SITTER" );
			
		} else if (myrolis.contains("ROLE_starsitter")) {
			model.addAttribute("star_sitter", true);
			model.addAttribute("myprofileis","STAR SITTER" );
		
		} else if (myrolis.contains("ROLE_centro")) {
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
