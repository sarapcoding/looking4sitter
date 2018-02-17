package com.dad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comentarios {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long idUsuarioOrigen;
	private Long idUsuarioDestino;
	private int puntuacion;
	private String comentario;
	private String fecha; // DD-MM-YY
	
	public Comentarios() {}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getIdUsuarioOrigen() {
		return idUsuarioOrigen;
	}


	public void setIdUsuarioOrigen(Long idUsuarioOrigen) {
		this.idUsuarioOrigen = idUsuarioOrigen;
	}


	public Long getIdUsuarioDestino() {
		return idUsuarioDestino;
	}


	public void setIdUsuarioDestino(Long idUsuarioDestino) {
		this.idUsuarioDestino = idUsuarioDestino;
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
		return "Comentario [Padre="+this.idUsuarioOrigen.toString()+",Sitter="+this.idUsuarioDestino.toString()+",Puntuación="+Integer.toString(this.puntuacion)+",Comentario="+this.comentario+",Fecha="+this.fecha+";";
		
	}
}
