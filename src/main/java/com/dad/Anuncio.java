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
	private String loginUsuario;
	
	
	 
	public Anuncio() {}

	public Anuncio(Usuario user,String asunto, String cuerpo, String fecha) {
		this.setUsuario(user);
		this.setAsunto(asunto);
		this.setCuerpo(asunto);
		this.setFecha(fecha);
		this.setLoginUsuario(user.getLogin());
		
	}

	public Anuncio(String asunto, String cuerpo, String fecha) {
		this.setUsuario(null);
		this.setAsunto(asunto);
		this.setCuerpo(asunto);
		this.setFecha(fecha);
		
	}
	

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String login_usuario) {
		this.loginUsuario = login_usuario;
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