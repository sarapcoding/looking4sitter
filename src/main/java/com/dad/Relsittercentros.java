package com.dad;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Relsittercentros {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long idUsuarioCentro;
	private Long idUsuarioSitter;
	private String fecha;
	private int activo;
	
	public Relsittercentros() {
		setActivo(1);
		DateFormat myFecha = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		setFecha(myFecha.format(date));
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getIdUsuarioCentro() {
		return idUsuarioCentro;
	}


	public void setIdUsuarioCentro(Long idUsuarioCentro) {
		this.idUsuarioCentro = idUsuarioCentro;
	}


	public Long getIdUsuarioSitter() {
		return idUsuarioSitter;
	}


	public void setIdUsuarioSitter(Long idUsuarioSitter) {
		this.idUsuarioSitter = idUsuarioSitter;
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


	@Override
	public String toString() {
		return "Relación [Centro="+this.idUsuarioCentro.toString()+",Sitters="+this.idUsuarioSitter.toString()+"]";
	}
	
	
	
}
