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
			tarifamax = "";
		}
		
		if(!provincia.matches("[A-Za-z]+")) {
			provincia = "";
		}
		System.out.println("------------------> Se ha comprobado los parámetros de entrada (prov="+provincia+", tarif="+tarifamax+")");
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://localhost:8443/sitters/provincia/"+provincia+"/tarifamax/"+tarifamax;
		System.out.println("------------------> Mi url: ["+url+"]");
		String resultados = restTemplate.getForObject(url, String.class);
		if ((resultados.equals("")) || (resultados == null)) {
			model.addAttribute("vacio",true);
		} else {
			List<Usuario> sittR = new ArrayList<>();
			//System.out.println(resultados);
			JSONArray jsonarr = new JSONArray(resultados);
			//System.out.println("MI JSONARRAY"+jsonarr);
			int numS = jsonarr.length();
		
			for(int i=0;i<numS;i++) {
				JSONObject obj = jsonarr.getJSONObject(i);
				Usuario u = new Usuario();
				//System.out.println("MI OBJETO JSONOBJECT"+obj);
				u.setId(obj.getLong("id"));
				u.setNombre(obj.getString("nombre"));
				u.setLogin(obj.getString("login"));
				u.setApellido(obj.getString("apellido"));
				u.setProvincia(obj.getString("provincia"));
				u.setDescripcion(obj.getString("descripcion"));
				u.setEmail(obj.getString("email"));
				u.setTarifa(obj.getInt("tarifa"));
				sittR.add(u);
				//System.out.println("MI NOMBRE ES"+nombre);
			}
			model.addAttribute("encontrado",true);
			model.addAttribute("resultadofinal",sittR);
		}
		
		return "resultadoSearchSitters_template";
	}
	
}
