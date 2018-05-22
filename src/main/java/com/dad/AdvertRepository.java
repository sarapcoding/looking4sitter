package com.dad;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dad.Anuncio;
/*
public interface AdvertRepository extends CrudRepository<Anuncios,Long>{

}*/

//@CacheConfig(cacheNames="anuncios")
public interface AdvertRepository extends JpaRepository<Anuncio,Long>{
	//Page<Anuncios> findByCiudad (String ciudad, Pageable page);
	//Page<Anuncios> findByTarifa (String tarifa, Pageable page);
	Page<Anuncio> findByFecha (String fecha, Pageable page);
	
	//@CacheEvict(allEntries=true)
	Anuncio save(Anuncio anuncio);
	
	
	//@Cacheable
	List<Anuncio> findByFecha(String fecha);
	
	//@Cacheable
	Anuncio findById(Long id);
	
	//@Cacheable
	List<Anuncio> findAll();
	
	//Page<Anuncios> findByCiudadAndTarifa (String ciudad, String tarifa, Pageable page);
	//Page<Anuncios> findByCiudadAndFecha (String ciudad, String fecha, Pageable page);
	//Page<Anuncios> findByFechaAndTarifa (String fecha, String tarifa, Pageable page);
	//Page<Anuncios> findByCiudadAndTarifaAndFecha (String ciudad, String tarifa,String fecha, Pageable page);
}
