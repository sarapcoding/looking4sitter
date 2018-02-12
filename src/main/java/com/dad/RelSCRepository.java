package com.dad;
import org.springframework.data.repository.CrudRepository;

import com.dad.RelSitterCenter;
public interface RelSCRepository extends CrudRepository<RelSitterCenter,Long>{
	//Lista de sitters segun el centro
	//centro según sitter
}
