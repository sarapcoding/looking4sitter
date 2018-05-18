package com.dad;



import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Arrays.*;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	
//	@CrossOrigin(origins = "http://localhost:8449")
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
		String url = "http://localhost:8449/busqueda/sitters/provincia/"+provincia+"/tarifamax/"+tarifamax;//8443
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
			JSONArray jsonarr = new JSONArray(resultados);
			int numS = jsonarr.length();
		
			for(int i=0;i<numS;i++) {
				JSONObject obj = jsonarr.getJSONObject(i);
				Usuario u = new Usuario();
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
	
	
//	@CrossOrigin(origins = "http://localhost:8449")
	@RequestMapping ("/search-adverts")
	public String encontrarAnuncio (Model model,@RequestParam String fecha) throws JSONException, NoSuchAlgorithmException, KeyManagementException{ 
		
		
		
		
		if ((fecha == null) || (fecha.equals(""))) {
			fecha = "null";
			model.addAttribute("nohayfecha",true);
		} else {
			model.addAttribute("hayfecha",true);
			model.addAttribute("mifecha",fecha);
		}
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8449/busqueda/anuncios/"+fecha;
		//https://localhost:8449/busqueda/anuncios/2018-04-30
//		Set<HttpMethod> optionsForAllow = restTemplate.optionsForAllow(url);
//		HttpMethod[] supportedMethods = {HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};
//		assertTrue(optionsForAllow.containsAll(Arrays.asList(supportedMethods)));
		String resultados = restTemplate.getForObject(url, String.class);
		
		if ((resultados.equals("")) || (resultados == null)) {
			model.addAttribute("vacio",true);
		} else {
			List<Anuncio> anuncios = new ArrayList<>();
			List<String> asuntos = new ArrayList<>();
			List<String> cuerpos = new ArrayList<>();
			List<String> logins = new ArrayList<>();
			List<String> fechas = new ArrayList<>();
			JSONArray jsonarr = new JSONArray(resultados);
			int numS = jsonarr.length();
		
			for(int i=0;i<numS;i++) {
				JSONObject obj = jsonarr.getJSONObject(i);
				Anuncio a = new Anuncio();
				String asunto = obj.getString("asunto");
				String cuerpo = obj.getString("cuerpo");
				String login = obj.getString("loginUsuario");
				String fechan = obj.getString("fecha");
				anuncios.add(a);
				asuntos.add(asunto);
				cuerpos.add(cuerpo);
				logins.add(login);
				fechas.add(fechan);
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
