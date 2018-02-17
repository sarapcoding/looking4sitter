package com.dad;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dad.Usuarios;


public interface UserRepository extends CrudRepository<Usuarios,Long>{

	List<Usuarios> findByLogin(String login);
	

	List<Usuarios> findByEmail (String email);

}
