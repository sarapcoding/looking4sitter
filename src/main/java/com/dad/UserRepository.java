package com.dad;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.dad.Usuarios;

@Repository
public interface UserRepository extends CrudRepository<Usuarios,Long>{

	List<Usuarios> findByLogin(String login);
	List<Usuarios> findByEmail (String email);
	List<Usuarios> findByProvincia(String provincia);
	List<Usuarios> findByTarifa(int tarifa);
	List<Usuarios> findById(Long id);
	/*
	 * @Query("select u from usuarios u where u.Tarifa > :tarifa")
	 * List<Usuarios> findByMaxTarifa(@Param("tarifa") int tarifa);
	 * 
	 * */
	
	/*
	@Query("select u from usuarios u where u.Tarifa > :tarifa and upper(u.Provincia) = upper(:provincia)")
	public List<Usuarios> findByMaxTarifaAndProvincia(@Param("tarifa") int tarifa,@Param("provincia") String provincia);
	*/

}
