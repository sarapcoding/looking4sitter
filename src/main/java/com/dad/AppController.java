
package com.dad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import com.dad.UserRepository;
import com.dad.Usuario;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

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
	
	

//	@RequestMapping ("/registro")
//	public String registrarUsuario (Model model){
//		return "registro_template";
//	}
//	
//		
//	@RequestMapping ("/exito+registro")
//	public String redireccionRegistro(Model model){
//		//nombre_provisional
//		return "continuacionRegistro_template";
//	}	
		
	
	
	@RequestMapping ("/entrar")
	public String entrarInvitado (Model model){
		return "principal_template";
	}
	
	@RequestMapping("/busqueda-avanzada-sitters")
	public String busquedaAvanzada(Model model, @RequestParam String provincia,@RequestParam String tarifa_max,
		@RequestParam int num_pag){
		int sig_pag=num_pag+1;
		int tarifa_h;
		Page<Usuario> sitters;
		
		// conversion: si tarifa no es un número y provincia no tiene a-z
		if (!tarifa_max.matches("[0-9]+")) {
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
				sitters = usuarioRepositorio.findByRolAndProvinciaIsLike("ROLE_sitter",provincia,new PageRequest(num_pag, 2));
				
			} else { // si no
				tarifa_h = Integer.parseInt(tarifa_max);
				sitters = usuarioRepositorio.findByProvinciaAndRolAndTarifaLessThan(provincia, "ROLE_sitter",tarifa_h, new PageRequest(num_pag, 2));
				
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
	
//	static {
//	    //for localhost testing only
//	    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
//	    new javax.net.ssl.HostnameVerifier(){
//	
//	        public boolean verify(String hostname,
//	                javax.net.ssl.SSLSession sslSession) {
//	            if (hostname.equals("localhost")) {
//	                return true;
//	            }
//	            return false;
//	        }
//	    });
//	}
	
	
//	static {
////	    HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> hostname.equals("127.0.0.1"));
//	    HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> hostname.equals("localhost"));
//	}
	
	
	@RequestMapping("/search-sitters")
	public String searchSitter(Model model,
			@RequestParam String provincia,
			@RequestParam String tarifamax){
		
		if (!tarifamax.matches("[0-9]+")) {
			tarifamax = "";
		}
		
		if(!provincia.matches("[A-Za-z]+")) {
			provincia = "";
		}
		System.out.println("Se ha comprobado los parámetros de entrada (prov="+provincia+", tarif="+tarifamax+")");
		RestTemplate restTemplate = new RestTemplate();
		//System.out.println("Creación del restTemplate correcta");
		String url = "https://localhost:8443/sitters/resultados/"+provincia+"/"+tarifamax;
		
//		final String uri = "https://localhost:8443/sitters/resultados/{provincia}/{tarifamax}";
//		Map<String,String> params = new HashMap<String,String>();
//		params.put(arg0, arg1)
//		
		System.out.println("Mi url: ["+url+"]");
		//String url = "https://localhost:8444/sitters/resultados?provincia="+provincia+"&tarifamax="+tarifamax;
//		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
//			    new javax.net.ssl.HostnameVerifier(){
//			        public boolean verify(String hostname,
//			                javax.net.ssl.SSLSession sslSession) {
//			            if ((hostname.equals("localhost"))|| (hostname.equals("127.0.0.1")) ){
//			                return true;
//			            }
//			            return false;
//			        }
//			    });
		ObjectNode data = restTemplate.getForObject(url, ObjectNode.class);
		System.out.println("Se ha hecho la llamada getForObject de la url para recuperar un ObjectNode");
		//List<Usuario> resultadositters = new ArrayList<>();
		List<String> sits = new ArrayList<>();
		ArrayNode items = (ArrayNode) data.get("items");
		for (int i = 0; i < items.size(); i++) {
			JsonNode item = items.get(i); // se obtiene el item
			//Usuario s = new Usuario();
			String mylogin = item.get("login").asText();
//			String mynombre;
//			String myapellido;
//			String myprovincia;
//			int mytarifa;
//			String mydescripcion;
			
			sits.add(mylogin);
			
		}
		
		model.addAttribute("resultadofinal",sits);
		
//		List<String> bookTitles = new ArrayList<String>();
//		ArrayNode items = (ArrayNode) data.get("items");
//		for (int i = 0; i < items.size(); i++) {
//		JsonNode item = items.get(i);
//		String bookTitle = item.get("volumeInfo").get("title").asText();
//		bookTitles.add(bookTitle);
//		}
		
		
		
		return "resultadoSearchSitters_template";
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
