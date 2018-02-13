package com.dad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mensajes {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	// Atributos del mensaje
	private Long Id_Usuario_Origen;
	private Long Id_Usuario_Destino;
	private String Asunto;
	private String Cuerpo;
	private String Fecha; // DD-MM-YY
	
	
	 
	public Mensajes() {}
	
	public Mensajes(Long u_ori,Long u_des,String asunto, String cuerpo, String fecha) {
		this.Id_Usuario_Destino=u_des;
		this.Id_Usuario_Origen=u_ori;
		this.Asunto=asunto;
		this.Cuerpo=cuerpo;
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
	public String getAsunto() {
		return Asunto;
	}
	public void setAsunto(String asunto) {
		Asunto = asunto;
	}
	public String getCuerpo() {
		return Cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		Cuerpo = cuerpo;
	}
	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	
	@Override
	public String toString() {
		return "Mensaje: [From="+this.Id_Usuario_Origen.toString()+",To="+this.Id_Usuario_Destino.toString()+",Asunto="+this.Asunto+",Cuerpo="+this.Cuerpo+",Fecha="+this.Fecha+"]";
	}
	
	
	
}
