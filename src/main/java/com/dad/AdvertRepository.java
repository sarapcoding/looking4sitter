package com.dad;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.dad.Anuncios;

public interface AdvertRepository extends JpaRepository<Anuncios,Long>{
	Page<Anuncios> findByCiudad (String ciudad, Pageable page);
	Page<Anuncios> findByTarifa (String tarifa, Pageable page);
	Page<Anuncios> findByFecha (String fecha, Pageable page);
	Page<Anuncios> findByCiudadAndTarifa (String ciudad, String tarifa, Pageable page);
	Page<Anuncios> findByCiudadAndFecha (String ciudad, String fecha, Pageable page);
	Page<Anuncios> findByFechaAndTarifa (String fecha, String tarifa, Pageable page);
	Page<Anuncios> findByCiudadAndTarifaAndFecha (String ciudad, String tarifa,String fecha, Pageable page);
}
