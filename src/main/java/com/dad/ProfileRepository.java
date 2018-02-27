package com.dad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.Perfil;


public interface ProfileRepository extends JpaRepository<Perfil,Long>{
	List<Perfil> findById(Long id);
	List<Perfil> findByNombre(String nombre);
}
/*
public interface ProfileRepository extends CrudRepository<Perfil,Long>{
	List<Perfil> findById(Long id);
	List<Perfil> findByNombre(String nombre);
}
*/
