package com.dad;
import java.util.List;
import com.dad.Agenda;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Hora {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//@OneToMany(mappedBy="misHoras")
	//private Usuario sitter;
	
	private int tiempo;// [0..23]
	private String fecha; //YYYY-MM-DD
	private String notas;
	// UNA SOLA VARIABLE PARA COMPROBAR SI ES UNA HORA LIBRE O ASIGNADA
	private boolean libre; // true=libre, false=asignada
	
	@ManyToOne
	private Agenda agenda;
	
	@ManyToOne
	private Usuario padre;
	
	// padre que ha solicitado la hora
	private String padreSolicita;
	
	public Hora() {
		this.setNotas("");
		this.setFecha("");
		this.setTiempo(0);
		this.setLibre(true);// por defecto las horas de alta son libres
		
	}
	
	public void horaLibre(String fechaalta,int horaalta) {
		this.setFecha(fechaalta);
		this.setTiempo(horaalta);
		this.setLibre(true);
	}
	
	public void horaAsignada(String fechaalta,int horaalta,Usuario padre) {
		this.setFecha(fechaalta);
		this.setTiempo(horaalta);
		this.setPadre(padre);
		this.setLibre(false);
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
		
		
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Usuario getPadre() {
		return padre;
	}

	public void setPadre(Usuario padre) {
		this.padre = padre;
	}

	public boolean isLibre() {
		return libre;
	}



	public void setLibre(boolean libre) {
		this.libre = libre;
	}



	@Override
	public String toString() {
		String mensaje = new String();
		if(this.isLibre()) { // hora libre
			mensaje = "Hora "+this.tiempo+" de la agenda de "+this.agenda.getSitter().getLogin()+" libre";
		} else { // hora asignada
			mensaje = "Hora "+this.tiempo+" de la agenda de "+this.agenda.getSitter().getLogin()+" asignada al padre "+this.padre.getLogin();
		}
		
		return mensaje;
	}
	
	
}
