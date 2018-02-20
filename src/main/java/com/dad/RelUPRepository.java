package com.dad;
import java.util.List;
import com.dad.Relusuariosperfiles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface RelUPRepository extends CrudRepository<Relusuariosperfiles,Long>{
	List<Relusuariosperfiles> findByIdusuario(Long idusuario);
	List<Relusuariosperfiles> findByIdperfil(Long idperfil);
}
