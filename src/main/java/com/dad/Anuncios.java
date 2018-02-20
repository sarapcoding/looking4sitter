package com.dad;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Anuncios {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	// Atributos del anuncio
	private Long idusuario;
	//private String nombre;
	private String asunto;
	private String cuerpo;
	private String fecha; // DD-MM-YY
	
	
	 
	public Anuncios() {}

	public Anuncios(Long user,String asunto, String cuerpo, String fecha) {
		this.idusuario=user;
		this.asunto=asunto;
		this.cuerpo=cuerpo;
		this.fecha=fecha;
		
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}

	@Override
	public String toString() {
		return "Anuncio: [Padre="+this.idusuario.toString()+",Asunto="+this.asunto+",Cuerpo="+this.cuerpo+",Fecha="+this.fecha+"]";
	}
	
}