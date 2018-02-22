package com.dad;
// imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
// Entidades
import com.dad.Usuario;
import com.dad.Comentario;
import com.dad.Mensaje;
import com.dad.Perfil;
import com.dad.Anuncio;
// Repositorios
import com.dad.UserRepository;
import com.dad.RemarkRepository;
import com.dad.MessageRepository;
import com.dad.ProfileRepository;
import com.dad.AdvertRepository;


@Controller
@RequestMapping(path="/demo")
public class MainController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RemarkRepository remarkRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private AdvertRepository advertRepository;

	
	@GetMapping(path="/add")
	public @ResponseBody String addNewUser(@RequestParam String login,
											@RequestParam String nombre,
											@RequestParam String email,
											@RequestParam String pass,
											@RequestParam String provincia,
											@RequestParam String descripcion) {
		Usuario n = new Usuario();
		n.setLogin(login);
		n.setNombre(nombre);
		n.setEmail(email);
		n.setPassword(pass);
		n.setProvincia(provincia);
		n.setDescripcion(descripcion);
		n.setTarifa(0);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Usuario> getAllUsers(){
		return userRepository.findAll();
	}

}
