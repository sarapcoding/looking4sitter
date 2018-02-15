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
	private Long id_Usuario_Origen;
	private Long id_Usuario_Destino;
	private int puntuacion;
	private String comentario;
	private String fecha; // DD-MM-YY
	
	public Comentarios() {}
	
	public Comentarios(Long origen, Long destino, int puntos, String comentario, String fecha) {
		this.id_Usuario_Origen=origen;
		this.id_Usuario_Destino=destino;
		this.puntuacion=puntos;
		this.comentario=comentario;
		this.fecha=fecha;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_Usuario_Origen() {
		return id_Usuario_Origen;
	}

	public void setId_Usuario_Origen(Long id_Usuario_Origen) {
		this.id_Usuario_Origen = id_Usuario_Origen;
	}

	public Long getId_Usuario_Destino() {
		return id_Usuario_Destino;
	}

	public void setId_Usuario_Destino(Long id_Usuario_Destino) {
		this.id_Usuario_Destino = id_Usuario_Destino;
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
		return "Comentario [Padre="+this.id_Usuario_Origen.toString()+",Sitter="+this.id_Usuario_Destino.toString()+",Puntuación="+Integer.toString(this.puntuacion)+",Comentario="+this.comentario+",Fecha="+this.fecha+";";
		
	}
}
