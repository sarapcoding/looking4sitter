package com.dad;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoraRepository extends JpaRepository<Hora,Long>{
	Page<Hora> findByAgenda(Agenda agenda, Pageable page);
	Page<Hora> findByAgendaAndFecha(Agenda agenda, String fecha, Pageable page);
	
	Page<Hora> findByAgendaAndFechaAndLibre(Agenda agenda,String fecha, boolean libre, Pageable page); // horas por fecha y libres
	Page<Hora> findByAgendaAndPadre(Agenda agenda,Usuario padre, Pageable page); // horas por padre al que se le ha asignado
	Page<Hora> findByAgendaAndTiempo(Agenda agenda,int tiempo, Pageable page); // horas por tiempo
	Page<Hora> findByAgendaAndFechaAndTiempo(Agenda agenda,String fecha,int tiempo, Pageable page);
	
}

/*
@Repository
public interface HoraRepository extends JpaRepository<Hora,Long>{
	List<Hora> findByAgenda(Agenda agenda);
	List<Hora> findByPadre(Usuario padre);
	List<Hora> findByAgendaAndFecha(Agenda agenda, String fecha);
	List<Hora> findByAgendaAndFechaAndTiempo(Agenda agenda, String fecha, int tiempo);
}
*/