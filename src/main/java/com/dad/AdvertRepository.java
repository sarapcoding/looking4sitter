package com.dad;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dad.Anuncio;
/*
public interface AdvertRepository extends CrudRepository<Anuncios,Long>{

}*/

@Repository
public interface AdvertRepository extends JpaRepository<Anuncio,Long>{
	//Page<Anuncios> findByCiudad (String ciudad, Pageable page);
	//Page<Anuncios> findByTarifa (String tarifa, Pageable page);
	Page<Anuncio> findByFecha (String fecha, Pageable page);
	//Page<Anuncios> findByCiudadAndTarifa (String ciudad, String tarifa, Pageable page);
	//Page<Anuncios> findByCiudadAndFecha (String ciudad, String fecha, Pageable page);
	//Page<Anuncios> findByFechaAndTarifa (String fecha, String tarifa, Pageable page);
	//Page<Anuncios> findByCiudadAndTarifaAndFecha (String ciudad, String tarifa,String fecha, Pageable page);
}
