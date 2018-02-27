package com.dad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoraRepository extends JpaRepository<Hora,Long>{
	List<Hora> findByAgenda(Agenda agenda);
	List<Hora> findByPadre(Usuario padre);
	List<Hora> findByAgendaAndFecha(Agenda agenda, String fecha);
	List<Hora> findByAgendaAndFechaAndTiempo(Agenda agenda, String fecha, int tiempo);
}
