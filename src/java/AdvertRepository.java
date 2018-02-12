package com.dad;

import java.util.List;




public interface AdvertRepository {
	List<Advert> findByNombreAndAsunto(String nombre, String asunto);
}
