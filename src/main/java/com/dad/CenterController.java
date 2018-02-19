package com.dad;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.dad.AppController;
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
public class CenterController {
	@Autowired 
	private UserRepository usuarioRepositorio;
	@Autowired
	private ProfileRepository perfilRepositorio;
	@Autowired
	private RelUPRepository upRepositorio;
	
	private Usuarios giveMeUser(List<Usuarios> list, Long id) {
		for (Usuarios x : list) {
			if(id.equals(x.getId())){
				list.remove(x);
				return x;
			}
		}
		return null;
		
	}
	
	@RequestMapping("/all-sitters")
	public String dameSitters(HttpServletRequest request,Model model){
		List<Usuarios> sitters = (List<Usuarios>) usuarioRepositorio.findAll();
		List<Usuarios> resultado = new ArrayList<>();
		
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

}
