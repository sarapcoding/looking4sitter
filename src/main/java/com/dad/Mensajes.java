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
	private Long id_Usuario_Origen;
	private Long id_Usuario_Destino;
	private String asunto;
	private String cuerpo;
	private String fecha; // DD-MM-YY
	
	
	 
	public Mensajes() {}
	
	public Mensajes(Long u_ori,Long u_des,String asunto, String cuerpo, String fecha) {
		this.id_Usuario_Destino=u_des;
		this.id_Usuario_Origen=u_ori;
		this.asunto=asunto;
		this.cuerpo=cuerpo;
		this.fecha=fecha;
	}


	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_Usuario_Destino() {
		return id_Usuario_Destino;
	}

	public void setId_Usuario_Destino(Long id_Usuario_Destino) {
		this.id_Usuario_Destino = id_Usuario_Destino;
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

	public Long getId_Usuario_Origen() {
		return id_Usuario_Origen;
	}

	public void setId_Usuario_Origen(Long id_Usuario_Origen) {
		this.id_Usuario_Origen = id_Usuario_Origen;
	}

	@Override
	public String toString() {
		return "Mensaje: [From="+this.id_Usuario_Origen.toString()+",To="+this.id_Usuario_Destino.toString()+",Asunto="+this.asunto+",Cuerpo="+this.cuerpo+",Fecha="+this.fecha+"]";
	}
	
	
	
}
