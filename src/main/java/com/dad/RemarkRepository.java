package com.dad;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dad.Comentario;

@Repository
public interface RemarkRepository extends JpaRepository<Comentario,Long>{
	List<Comentario> findByDestino(Usuario destino);
}
