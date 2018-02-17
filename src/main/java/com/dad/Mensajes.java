package com.dad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mensajes {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	// Atributos del mensaje
	private Long idUsuarioOrigen;
	private Long idUsuarioDestino;
	private String asunto;
	private String cuerpo;
	private String fecha; // DD-MM-YY
	
	
	 
	public Mensajes() {}
	

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


	public String getAsunto() {
		return asunto;
	}


	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}


	public String getCuerpo() {
		return cuerpo;
	}


	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	@Override
	public String toString() {
		return "Mensaje: [From="+this.idUsuarioOrigen.toString()+",To="+this.idUsuarioDestino.toString()+",Asunto="+this.asunto+",Cuerpo="+this.cuerpo+",Fecha="+this.fecha+"]";
	}
	
	
	
}
