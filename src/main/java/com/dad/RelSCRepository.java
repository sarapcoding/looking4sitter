package com.dad;
import org.springframework.data.repository.CrudRepository;

import com.dad.Relsittercentros;
public interface RelSCRepository extends CrudRepository<Relsittercentros,Long>{
	//Lista de sitters segun el centro
	//centro según sitter
}
