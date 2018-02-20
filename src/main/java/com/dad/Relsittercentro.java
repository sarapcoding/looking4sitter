package com.dad;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Relsittercentro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private Usuario centro;
	
	@OneToMany
	private List<Usuario> sitter;
	private String fecha;
	private int activo;
	
	public Relsittercentro() {
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
	
	
	public Usuario getCentro() {
		return centro;
	}


	public void setCentro(Usuario centro) {
		this.centro = centro;
	}


	

	@Override
	public String toString() {
		return "Relación [Centro="+this.centro.getLogin()+",Sitters="+this.sitter.toString()+"]";
	}
	
	
	
}
