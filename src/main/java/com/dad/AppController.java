
package com.dad;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.dad.UserRepository;
import com.dad.Usuario;
import com.dad.Perfil;
import com.dad.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.dad.SecurityConfiguration;

@Controller 
public class AppController {
	@Autowired 
	private UserRepository usuarioRepositorio;
	@Autowired
	private ProfileRepository perfilRepositorio;
	@Autowired
	private AdvertRepository anuncioRepositorio;
	@Autowired
	private RemarkRepository comentarioRepositorio;

	@PostConstruct
	public void init() {
		// carga de datos al arrancar la aplicación
		Perfil administrador = new Perfil("administrador");
		Perfil padre = new Perfil("padre");
		Perfil sitter = new Perfil("sitter");
		Perfil starSitter = new Perfil("star sitter");
		Perfil centro = new Perfil("centro");
		
		Usuario us1 = new Usuario("padrede2","Antonio","García","padrede2123","padrede2@email.com","Madrid",0,"feliz",padre);
		Usuario us2 = new Usuario("mia","Amelia","G","mia123","mia@email.com","Madrid",15,"lala",sitter);
		Usuario us3 = new Usuario("flor","Flor","Blanca","flor123","flor@email.com","Barcelona",13,"po",sitter);
		Usuario us4 = new Usuario("caracoles","Caracoles Center","","caracoles123","caracoles@email.com","Madrid",0,"tinky",centro);
		Usuario us5 = new Usuario("kei","Kei","Wong","kei123","kei@email.com","Madrid",0,"Padre de un nene tranquilo",padre);
		
		
		Perfil administrador_guardado=perfilRepositorio.save(administrador);
		Perfil padre_guardado=perfilRepositorio.save(padre);
		Perfil sitter_guardado=perfilRepositorio.save(sitter);
		Perfil starSitter_guardado=perfilRepositorio.save(starSitter);
		Perfil centro_guardado=perfilRepositorio.save(centro);
		
		us1.setPerfil(padre_guardado);
		us2.setPerfil(sitter_guardado);
		us3.setPerfil(sitter_guardado);
		us4.setPerfil(centro_guardado);
		us5.setPerfil(padre_guardado);
		
		
		
		usuarioRepositorio.save(us1);
		usuarioRepositorio.save(us2);
		usuarioRepositorio.save(us3);
		usuarioRepositorio.save(us4);
		usuarioRepositorio.save(us5);
		
		
		Anuncio an1 = new Anuncio(us1,"Niñer@ para el sábado","Busco a alguien que pueda cuidar mi crío de 3 a 5 este viernes","2018-03-02");
		Anuncio an2 = new Anuncio(us5,"Busco persona","Tengo gemelos que necesitan ser vigilados","2018-03-02");
		Anuncio an3 = new Anuncio(us1,"AAAH","Aaaah","2018-03-03");
		Anuncio an4 = new Anuncio(us5,"Sábado por la noche","Busco persona responsable para cuidar de mi hijo de 6 años este viernes de 9 a 12 de la noche","2018-03-03");
		
		
		anuncioRepositorio.save(an1);
		anuncioRepositorio.save(an2);
		anuncioRepositorio.save(an3);
		anuncioRepositorio.save(an4);
		
		//usuarioRepositorio.
		/*
		us1.setAnuncio(an1_guardado);
		us1.setAnuncio(an3_guardado);
		us5.setAnuncio(an2_guardado);
		us5.setAnuncio(an4_guardado);
		
		sitter.setUsuario(us2);
		sitter.setUsuario(us3);
		padre.setUsuario(us1);
		padre.setUsuario(us5);
		centro.setUsuario(us4);
		*/
		
		
	}
	
	
	@GetMapping ("/")
	public String arranque (Model model){
		return "welcome_template";
	}
	@GetMapping ("/inicio")
	public String iniciarSesion (Model model){
		return "inicioSesion_template";
	}
	
	@GetMapping ("/inicio+sesion")
	public String verificacionInicioSesion (Model model){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Usuario> list_user = usuarioRepositorio.findByLogin(username);
		Usuario user = list_user.get(0);
		
		String mynameis = user.getNombre();
		String myprofileis = user.getPerfil().getNombre();
		model.addAttribute("mynameis", mynameis);
		model.addAttribute("myprofileis", myprofileis);

		if (myprofileis.toUpperCase().equals("PADRE")) {
			model.addAttribute("padre", true);
		} else if (myprofileis.toUpperCase().equals("SITTER")) {
			
			model.addAttribute("sitter", true);
		} else if (myprofileis.toUpperCase().equals("STAR SITTER")) {
			model.addAttribute("star_sitter", true);
		} else if (myprofileis.toUpperCase().equals("CENTRO")) {
			model.addAttribute("centro", true);
		}
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
			List<Perfil> list_p = perfilRepositorio.findByNombre("Sitter");
			Perfil p = list_p.get(0);
			for (Usuario u : sitters) {
				if (u.getPerfil().equals(p)) {
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
		List<Usuario> list_user = usuarioRepositorio.findByLogin(username);
		Usuario user = list_user.get(0);
		
		String mynameis = user.getNombre();
		String myprofileis = user.getPerfil().getNombre();
		model.addAttribute("mynameis", mynameis);
		model.addAttribute("myprofileis", myprofileis);

		if (myprofileis.toUpperCase().equals("PADRE")) {
			model.addAttribute("padre", true);
		} else if (myprofileis.toUpperCase().equals("SITTER")) {
			
			model.addAttribute("sitter", true);
		} else if (myprofileis.toUpperCase().equals("STAR SITTER")) {
			model.addAttribute("star_sitter", true);
		} else if (myprofileis.toUpperCase().equals("CENTRO")) {
			model.addAttribute("centro", true);
		}
		return "boardUser_template";
		
		
	}
	
	@RequestMapping ("/cerrar-sesion")
	public String cerrarSesion (Model model) {
		return "cerrarSesion_template";
		
	}
	
	}
