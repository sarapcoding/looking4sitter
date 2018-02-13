package com.dad;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.dad.User;


public interface UserRepository extends CrudRepository<User,Long>{
	@Query("select U from Usuarios U where U.Login=?1")
	List<User> findByLogin(String Login);
	@Query("select U from Usuarios U where U.Email=?1")
	List<User> findByEmail (String Email);
	//List<User> findByLoginOrEmail (String Login, String Email);
}
