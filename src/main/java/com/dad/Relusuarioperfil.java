package com.dad;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Relusuarioperfil {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Perfil perfil;
	
	@OneToOne(mappedBy="relacion")
	private Usuario usuario;
	
	private String fecha;
	private int activo;
	
	public Relusuarioperfil() {
		setActivo(1);
		DateFormat myFecha = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		setFecha(myFecha.format(date));
	}
	

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}





	public int getActivo() {
		return activo;
	}


	public void setActivo(int activo) {
		this.activo = activo;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Perfil getPerfil() {
		return perfil;
	}


	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public String toString() {
		return "Relación [Perfil="+this.perfil.getNombre()+",Usuarios="+this.usuario.getLogin()+"]";
	}
	
	
	
}
