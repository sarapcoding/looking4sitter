package com.dad;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dad.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda,Long>{
	List<Agenda> findById(Long id);
	List<Agenda> findBySitter(Usuario sitter);
}
