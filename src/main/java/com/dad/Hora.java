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
	
	private int tiempo;// [0..23]
	private String fecha; //YYYY-MM-DD
	private String notas;
	private boolean asignada; // true=si, false=no
	private boolean ocupada; // true=si, false=no
	private boolean libre; // true=si, false=no
	
	@OneToMany(mappedBy="hora")
	private Agenda agenda;
	
	@OneToMany(mappedBy="hora")
	private Usuario padre;
	
	public Hora() {}

	
	
	public void horaDeAlta(String fechaalta,int horaalta) {
		this.setFecha(fechaalta);
		this.setTiempo(horaalta);
		this.setLibre(true);
		this.setAsignada(false);
		this.setOcupada(false);
	}
	
	public void horaAPadre(String fechaalta,int horaalta,Usuario padre) {
		this.setFecha(fechaalta);
		this.setTiempo(horaalta);
		this.setLibre(false);
		this.setAsignada(true);
		this.setOcupada(false);
		this.setPadre(padre);
		
	}
	
	public void horaOcupada(String fechaalta,int horaalta) {
		this.setFecha(fechaalta);
		this.setTiempo(horaalta);
		this.setLibre(false);
		this.setAsignada(false);
		this.setOcupada(true);
		this.setPadre(padre);
		
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
	
	
	public boolean isAsignada() {
		return asignada;
	}

	public void setAsignada(boolean asignada) {
		this.asignada = asignada;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
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
		if(this.isAsignada()) {
			mensaje = "Hora "+this.tiempo+" de la agenda de "+this.agenda.getSitter().getLogin()+" asignada al padre "+this.padre.getLogin();
		} else if (this.isOcupada()) {
			mensaje = "Hora "+this.tiempo+" de la agenda de "+this.agenda.getSitter().getLogin()+" ocupada";
		} else if (this.isLibre()) {
			mensaje = "Hora "+this.tiempo+" de la agenda de "+this.agenda.getSitter().getLogin()+" libre";
		}
		
		return mensaje;
	}
	
	
}
