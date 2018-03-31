package com.dad;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AgendaController {
	@Autowired
	private AgendaRepository agendaRepositry;
	
	@Autowired
	private HoraRepository horaRepository;
	
	@Autowired
	private UserRepository usuarioRepositorio;
	
	// comprobación de la agenda del sitter loggueado
	@RequestMapping("/ver-mi-agenda")
	public String checkMyAgenda(HttpServletRequest request, Model model){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario user = usuarioRepositorio.findByLogin(username);
		Agenda myAgenda = user.getAgenda();
		// conseguimos toda la lista de horas del sitter para mostrar por página
		// int page, int size, direction, string properties
		PageRequest pagereq = new PageRequest(0,10,Sort.Direction.ASC,"fecha");
		Page<Hora> myHours = horaRepository.findByAgenda(myAgenda,pagereq);
		
		if (myHours.getTotalElements()==0){
			model.addAttribute("fail", true);
		} else {
			model.addAttribute("encontrado", true);
			model.addAttribute("horas",myHours);
		}
		return "agendaSitter_template";
	}
	
	@RequestMapping("/alta-horas")
	public String hourRegistry(HttpServletRequest request, Model model) {
		return "altaHora_template"; // este template muestra la lista de horas y un form
	}
	
	@RequestMapping("/alta-horas-agregada")
	public String successHour(HttpServletRequest request, Model model) {
		// depende de si es libre o si es asignada (por defecto, libre)
		// en caso de fallo, carga el mismo template con el mensaje de fallo
		return "altaHora_template"; // enseña todas las horas y un mensaje de éxito
	}
	

}
