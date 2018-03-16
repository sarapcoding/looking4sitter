package com.dad;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.dad.Usuario;


@Repository
public interface UserRepository extends JpaRepository<Usuario,Long>{

	List<Usuario> findByLogin(String login);
	List<Usuario> findByEmail(String email);
	List<Usuario> findByProvincia(String provincia);
	List<Usuario> findByProvinciaIsLike(String provincia);
	List<Usuario> findById(Long id);
	
	@Query(
			value="select * from usuario where Tarifa <= ?1",
			nativeQuery = true)
	List<Usuario> findByTarifa(int tarifa);
	
	@Query(
			value="select * from usuario where Provincia like ?1 and Tarifa <= ?2",
			nativeQuery = true)
	List<Usuario> findByProvinciaAndTarifa(String provincia,int tarifa);

}


/*
@Repository
public interface UserRepository extends CrudRepository<Usuario,Long>{
List<Usuario> findByLogin(String login);
	List<Usuario> findByEmail (String email);
	List<Usuario> findByProvincia(String provincia);
	List<Usuario> findByProvinciaIsLike(String provincia);
	List<Usuario> findById(Long id);
	
	@Query(
			value="select * from usuarios where Tarifa <= ?1",
			nativeQuery = true)
	List<Usuario> findByTarifa(int tarifa);
	
	
	//@Query(
	//		value="select * from usuarios where 1=1 ?1 ?2",
	//		nativeQuery = true)
	//List<Usuarios> findByProvinciaAndTarifa(String provincia,String tarifa);
	
	@Query(
			value="select * from usuarios where Provincia like ?1 and Tarifa <= ?2",
			nativeQuery = true)
	List<Usuario> findByProvinciaAndTarifa(String provincia,int tarifa);
	
}
*/

