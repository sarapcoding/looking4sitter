package com.dad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.dad.Anuncios;

public interface AdvertRepository extends JpaRepository<Anuncios,Long>{

}
