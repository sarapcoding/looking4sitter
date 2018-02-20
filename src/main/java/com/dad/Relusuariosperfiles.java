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

@Entity
public class Relusuariosperfiles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long idperfil;
	private Long idusuario;
	private String fecha;
	private int activo;
	
	public Relusuariosperfiles() {
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


	public Long getIdperfil() {
		return idperfil;
	}


	public void setIdperfil(Long idperfil) {
		this.idperfil = idperfil;
	}


	public Long getIdusuario() {
		return idusuario;
	}


	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}


	@Override
	public String toString() {
		return "Relación [Perfil="+this.idperfil.toString()+",Usuarios="+this.idusuario.toString()+"]";
	}
	
	
	
}
