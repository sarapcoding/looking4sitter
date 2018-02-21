package com.dad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comentario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Usuario origen;
	
	@ManyToOne
	private Usuario destino;
	
	private int puntuacion;
	private String comentario;
	private String fecha; // DD-MM-YY
	
	public Comentario() {}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Usuario getOrigen() {
		return origen;
	}


	public void setOrigen(Usuario origen) {
		this.origen = origen;
	}


	public Usuario getDestino() {
		return destino;
	}


	public void setDestino(Usuario destino) {
		this.destino = destino;
	}


	public int getPuntuacion() {
		return puntuacion;
	}


	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	@Override
	public String toString() {
		return "Comentario [Padre="+this.origen.getLogin()+",Sitter="+this.destino.getLogin()+",Puntuación="+Integer.toString(this.puntuacion)+",Comentario="+this.comentario+",Fecha="+this.fecha+";";
		
	}
}
