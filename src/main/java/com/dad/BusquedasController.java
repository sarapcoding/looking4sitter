package com.dad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Arrays.*;
import java.util.Base64;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class BusquedasController {
	@Autowired 
	private UserRepository usuarioRepositorio;
	
	@RequestMapping("/search-sitters")
	public String searchSitter(Model model,
			@RequestParam String provincia,
			@RequestParam String tarifamax) throws JSONException{
		
		if (!tarifamax.matches("[0-9]+")) {
			tarifamax = "1000";
		}
		
		if(!provincia.matches("[A-Za-z]+")) {
			provincia = "null";
		}
		
		if ((provincia == null) || (provincia.equals(""))) {
			provincia = "null";
		}
		
		if ((tarifamax == null) ||(tarifamax.equals(""))) {
			tarifamax = "1000";
		}
		
		//System.out.println("------------------> Se ha comprobado los parámetros de entrada (prov="+provincia+", tarif="+tarifamax+")");
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://localhost:8444/busqueda/sitters/provincia/"+provincia+"/tarifamax/"+tarifamax;//8443
		//System.out.println("------------------> Mi url: ["+url+"]");
		String resultados = restTemplate.getForObject(url, String.class);
		if ((resultados.equals("")) || (resultados == null)) {
			model.addAttribute("vacio",true);
		} else {
			List<Usuario> sittR = new ArrayList<>();
			List<String> logins = new ArrayList<>();
			List<String> nombres = new ArrayList<>();
			List<String> apellidos = new ArrayList<>();
			List<String> provincias = new ArrayList<>();
			List<String> descripciones = new ArrayList<>();
			List<Integer> tarifas = new ArrayList<>();
			//System.out.println(resultados);
			JSONArray jsonarr = new JSONArray(resultados);
			//System.out.println("MI JSONARRAY"+jsonarr);
			int numS = jsonarr.length();
		
			for(int i=0;i<numS;i++) {
				JSONObject obj = jsonarr.getJSONObject(i);
				Usuario u = new Usuario();
				//System.out.println("MI OBJETO JSONOBJECT"+obj);
				
				Long myid = obj.getLong("id");
				String mynombre = obj.getString("nombre");
				String mylogin = obj.getString("login");
				String myapellido = obj.getString("apellido");
				String myprovincia = obj.getString("provincia");
				String mydesc = obj.getString("descripcion");
				String myemail = obj.getString("email");
				int mytarifa = obj.getInt("tarifa");
				
				u.setId(myid);
				u.setNombre(mynombre);
				u.setLogin(mylogin);
				u.setApellido(myapellido);
				u.setProvincia(myprovincia);
				u.setDescripcion(mydesc);
				u.setEmail(myemail);
				u.setTarifa(mytarifa);
				
				sittR.add(u);
				logins.add(mylogin);
				nombres.add(mynombre);
				apellidos.add(myapellido);
				provincias.add(myprovincia);
				descripciones.add(mydesc);
				tarifas.add(mytarifa);
				//System.out.println("MI NOMBRE ES"+nombre);
			}
			model.addAttribute("encontrado",true);
			model.addAttribute("rlogins",logins);
			model.addAttribute("rnombres",nombres);
			model.addAttribute("rapellidos",apellidos);
			model.addAttribute("rprovincias",provincias);
			model.addAttribute("rdescripciones",descripciones);
			model.addAttribute("rtarifas",tarifas);
			model.addAttribute("resultadofinal",sittR);
		}
		
		return "resultadoSearchSitters_template";
	}
	
	@RequestMapping ("/search-adverts")
	public String encontrarAnuncio (Model model,@RequestParam String fecha) throws JSONException{ 
		
		if ((fecha == null) || (fecha.equals(""))) {
			fecha = "null";
			model.addAttribute("nohayfecha",true);
		} else {
			model.addAttribute("hayfecha",true);
			model.addAttribute("mifecha",fecha);
		}
		
		//System.out.println("------------------> Se ha comprobado los parámetros de entrada (prov="+provincia+", tarif="+tarifamax+")");
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://localhost:8444/busqueda/anuncios/"+fecha;
		//System.out.println("------------------> Mi url: ["+url+"]");
		String resultados = restTemplate.getForObject(url, String.class);
		if ((resultados.equals("")) || (resultados == null)) {
			model.addAttribute("vacio",true);
		} else {
			List<Anuncio> anuncios = new ArrayList<>();
			List<String> asuntos = new ArrayList<>();
			List<String> cuerpos = new ArrayList<>();
			List<String> logins = new ArrayList<>();
			List<String> fechas = new ArrayList<>();
			//System.out.println(resultados);
			JSONArray jsonarr = new JSONArray(resultados);
			//System.out.println("MI JSONARRAY"+jsonarr);
			int numS = jsonarr.length();
		
			for(int i=0;i<numS;i++) {
				JSONObject obj = jsonarr.getJSONObject(i);
				Anuncio a = new Anuncio();
				//System.out.println("MI OBJETO JSONOBJECT"+obj);

				String asunto = obj.getString("asunto");
				String cuerpo = obj.getString("cuerpo");
				String login = obj.getString("loginUsuario");
				String fechan = obj.getString("fecha");
				anuncios.add(a);
				asuntos.add(asunto);
				cuerpos.add(cuerpo);
				logins.add(login);
				fechas.add(fechan);
				//System.out.println("MI NOMBRE ES"+nombre);
			}
			
			
			model.addAttribute("encontrado",true);
			model.addAttribute("asuntos",asuntos);
			model.addAttribute("cuerpos",cuerpos);
			model.addAttribute("logins",logins);
			model.addAttribute("fechas", fechas);
			model.addAttribute("anuncios",anuncios);
			model.addAttribute("resultadofinal",anuncios);
		}
			
		return "resultadoSearchAnuncios_template";
	}
	
}
