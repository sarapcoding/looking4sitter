package com.dad;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.dad.AppController;
import com.dad.UserRepository;
import com.dad.Usuario;
import com.dad.Perfil;
import com.dad.ProfileRepository;
import com.dad.Relusuarioperfil;
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
	
	
	
	

}
