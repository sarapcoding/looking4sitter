package com.dad;
import java.util.List;
import com.dad.Relusuarioperfil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface RelUPRepository extends CrudRepository<Relusuarioperfil,Long>{
	List<Relusuarioperfil> findByIdusuario(Long idusuario);
	List<Relusuarioperfil> findByIdperfil(Long idperfil);
}
