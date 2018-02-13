package com.dad;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.dad.User;


public interface UserRepository extends CrudRepository<User,Long>{
	List<User> findByLogin(String Login);
	List<User> findByEmail (String Email);
	List<User> findByLoginOrEmail (String Login, String Email);
}
