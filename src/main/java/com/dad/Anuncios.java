package com.dad;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Anuncios {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	// Atributos del anuncio
	private Long Id_Usuario;
	private String Asunto;
	private String Cuerpo;
	private String Fecha; // DD-MM-YY
	
	
	 
	public Anuncios() {}

	public Anuncios(Long user,String asunto, String cuerpo, String fecha) {
		this.Id_Usuario=user;
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
	public Long getId_Usuario() {
		return Id_Usuario;
	}
	public void setId_Usuario(Long id_Usuario) {
		Id_Usuario = id_Usuario;
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
		return "Anuncio: [Padre="+this.Id_Usuario.toString()+",Asunto="+this.Asunto+",Cuerpo="+this.Cuerpo+",Fecha="+this.Fecha+"]";
	}
	
}
