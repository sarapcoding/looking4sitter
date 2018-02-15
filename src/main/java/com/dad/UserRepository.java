package com.dad;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dad.Usuarios;


public interface UserRepository extends CrudRepository<Usuarios,Long>{
//	@Query("select u.* from Usuarios u where u.Login = :login")
	List<Usuarios> findByLogin(@Param("login") String login);
	
	
//	@Query("select u.* from Usuarios u where u.Email = :email")
	List<Usuarios> findByEmail (@Param("email") String email);
	//List<User> findByLoginOrEmail (String Login, String Email);
}
