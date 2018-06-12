package com.dad;
import com.dad.AdvertRepository;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestRepository{

	//@Override
	@Cacheable("anuncios")
	public List<Anuncio> getAnuncios(String fecha) throws JSONException {
		RestTemplate restTemplate = new RestTemplate();
		List<Anuncio> anuncios = new ArrayList<>();
		String url = "http://localhost:8449/busqueda/anuncios/"+fecha;
		//http://localhost:8449/busqueda/anuncios/2018-04-30
		String resultados = restTemplate.getForObject(url, String.class);
		
		JSONArray jsonarr = new JSONArray(resultados);
		int numS = jsonarr.length();
	
		for(int i=0;i<numS;i++) {
			JSONObject obj = jsonarr.getJSONObject(i);
			Anuncio a = new Anuncio();
			a.setAsunto(obj.getString("asunto"));
			a.setCuerpo(obj.getString("cuerpo"));
			a.setFecha(obj.getString("fecha"));
			a.setLoginUsuario(obj.getString("loginUsuario"));
			anuncios.add(a);
		}
		return anuncios;
	}

	
	
}
