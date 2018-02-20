package com.dad;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Anuncio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	// Atributos del anuncio
	
	@ManyToOne
	private Usuario usuario;
	//private String nombre;
	private String asunto;
	private String cuerpo;
	private String fecha; // DD-MM-YY
	
	
	 
	public Anuncio() {}

	public Anuncio(Usuario user,String asunto, String cuerpo, String fecha) {
		this.usuario=user;
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

	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Anuncio: [Padre="+this.usuario.getNombre()+",Asunto="+this.asunto+",Cuerpo="+this.cuerpo+",Fecha="+this.fecha+"]";
	}
	
}