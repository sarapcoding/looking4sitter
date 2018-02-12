package com.dad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Remark {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private Long Id_Usuario_Origen;
	private Long Id_Usuario_Destino;
	private int Puntuacion;
	private String Comentario;
	private String Fecha; // DD-MM-YY
	
	public Remark() {}
	
	public Remark(Long origen, Long destino, int puntos, String comentario, String fecha) {
		this.Id_Usuario_Origen=origen;
		this.Id_Usuario_Destino=destino;
		this.Puntuacion=puntos;
		this.Comentario=comentario;
		this.Fecha=fecha;
	}
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getId_Usuario_Origen() {
		return Id_Usuario_Origen;
	}

	public void setId_Usuario_Origen(Long id_Usuario_Origen) {
		Id_Usuario_Origen = id_Usuario_Origen;
	}

	public Long getId_Usuario_Destino() {
		return Id_Usuario_Destino;
	}

	public void setId_Usuario_Destino(Long id_Usuario_Destino) {
		Id_Usuario_Destino = id_Usuario_Destino;
	}

	public int getPuntuacion() {
		return Puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		Puntuacion = puntuacion;
	}

	public String getComentario() {
		return Comentario;
	}

	public void setComentario(String comentario) {
		Comentario = comentario;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	@Override
	public String toString() {
		return "Comentario [Padre="+this.Id_Usuario_Origen.toString()+",Sitter="+this.Id_Usuario_Destino.toString()+",Puntuación="+Integer.toString(this.Puntuacion)+",Comentario="+this.Comentario+",Fecha="+this.Fecha+";";
		
	}
}
