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

	Usuario findByLogin(String login);
	Usuario findByEmail (String email);
	List<Usuario> findByRoles(String rol);
	List<Usuario> findByProvincia(String provincia);
	List<Usuario> findByProvinciaIsLike(String provincia);
	Usuario findById(Long id);
	
	@Query(
			value="select * from usuario where tarifa <= ?1",
			nativeQuery = true)
	List<Usuario> findByTarifa(int tarifa);
	
	@Query(
			value="select * from usuario where provincia like ?1 and tarifa <= ?2",
			nativeQuery = true)
	List<Usuario> findByProvinciaAndTarifa(String provincia,int tarifa);
	
	/* búsqueda específica de sitters */
	
	// todos los sitters
	
//	@Query(
//			value="select * from usuario where roles in ?1",
//			nativeQuery = true)
//	List<Usuario> findByRoles(List<String> roles);
}


