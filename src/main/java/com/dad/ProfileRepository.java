package com.dad;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dad.Perfiles;

public interface ProfileRepository extends CrudRepository<Perfiles,Long>{
	List<Perfiles> findById(Long id);
}
