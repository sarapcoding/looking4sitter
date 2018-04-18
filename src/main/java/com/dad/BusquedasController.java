package com.dad;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class BusquedasController {
	@Autowired 
	private UserRepository usuarioRepositorio;
	/*
	static {
		//for localhost testing only
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
		new javax.net.ssl.HostnameVerifier(){

			public boolean verify(String hostname,
					javax.net.ssl.SSLSession sslSession) {
				if (hostname.equals("localhost")) {
					return true;
				}
				return false;
			}
		});
	}
*/

/*
	static {
//	    HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> hostname.equals("127.0.0.1"));
	    HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> hostname.equals("localhost"));
	}
*/

	
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
		System.out.println("------------------> Se ha comprobado los parámetros de entrada (prov="+provincia+", tarif="+tarifamax+")");
		RestTemplate restTemplate = new RestTemplate();
		//System.out.println("Creación del restTemplate correcta");
		String url = "https://localhost:8443/sitters/resultados/"+provincia+"/"+tarifamax;
		
//		final String uri = "https://localhost:8443/sitters/resultados/{provincia}/{tarifamax}";
//		Map<String,String> params = new HashMap<String,String>();
//		params.put(arg0, arg1)
//		
		System.out.println("------------------> Mi url: ["+url+"]");
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
		//ObjectNode data = restTemplate.getForObject(url, ObjectNode.class);
		
		ResponseEntity<JSONObject[]> responseEntity = restTemplate.getForEntity(url, JSONObject[].class);
		JSONObject[] resultados = responseEntity.getBody();
		System.out.println(resultados.toString());
		System.out.println("Se ha hecho la llamada getForObject de la url para recuperar un ObjectNode");
		//List<Usuario> resultadositters = new ArrayList<>();
		List<String> sits = new ArrayList<>();
		
		for (JSONObject jo : resultados) {
			sits.add(jo.toString());
		}
		//ArrayNode items = (ArrayNode) data.get("items");
//		for (int i = 0; i < items.size(); i++) {
//			JsonNode item = items.get(i); // se obtiene el item
//			//Usuario s = new Usuario();
//			String mylogin = item.get("login").asText();
////			String mynombre;
////			String myapellido;
////			String myprovincia;
////			int mytarifa;
////			String mydescripcion;
//			
//			sits.add(mylogin);
//			
//		}
		
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
	
}
