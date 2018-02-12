package com.dad;
import org.springframework.data.repository.CrudRepository;

import com.dad.User;
public interface UserRepository extends CrudRepository<User,Long>{

}
