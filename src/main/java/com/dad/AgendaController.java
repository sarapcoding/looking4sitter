package com.dad;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	// comprobación de la agenda del sitter loggueado
	@RequestMapping("/ver-mi-agenda")
	public String checkMyAgenda(HttpServletRequest request, Model model){
		UsuarioSesion usuario_actual = (UsuarioSesion) request.getSession().getAttribute("usuario_actual");
		Usuario usuario = usuario_actual.getUsuario();
		Agenda myAgenda = usuario.getAgenda();
		// conseguimos toda la lista de horas del sitter para mostrar por página
		// int page, int size, direction, string properties
		PageRequest pagereq = new PageRequest(0,10,Sort.Direction.ASC,"fecha");
		Page<Hora> myHours = horaRepository.findByAgenda(myAgenda,pagereq);
		
		if (myHours.getTotalElements()==0){
			model.addAttribute("fail", true);
		} else {
			model.addAttribute("encontrado", true);
			model.addAttribute("horas",myHours);
			//model.addAttribute("numPag",sig_pag);
			//model.addAttribute("fechaBusqueda", fecha);
		}
		
		
		return "agendaSitter_template";
	}

}
