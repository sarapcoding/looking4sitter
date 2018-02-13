package com.dad;
// imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
// Entidades
import com.dad.Usuarios;
import com.dad.Comentarios;
import com.dad.Mensajes;
import com.dad.Perfiles;
import com.dad.Anuncios;
import com.dad.Relusuariosperfiles;
import com.dad.Relsittercentros;
// Repositorios
import com.dad.UserRepository;
import com.dad.RemarkRepository;
import com.dad.MessageRepository;
import com.dad.ProfileRepository;
import com.dad.AdvertRepository;
import com.dad.RelUPRepository;
import com.dad.RelSCRepository;


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
	@Autowired
	private RelUPRepository relupRepository;
	@Autowired
	private RelSCRepository relscRepository;
	
	@GetMapping(path="/add")
	public @ResponseBody String addNewUser(@RequestParam String login,
											@RequestParam String nombre,
											@RequestParam String email,
											@RequestParam String pass,
											@RequestParam String provincia,
											@RequestParam String descripcion) {
		Usuarios n = new Usuarios();
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
	public @ResponseBody Iterable<Usuarios> getAllUsers(){
		return userRepository.findAll();
	}

}
