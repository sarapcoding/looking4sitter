package com.dad;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Relsittercentros {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//@OneToOne
	private Long idUsuarioCentro;
	//@OneToMany
	private Long idUsuarioSitter;
	public Relsittercentros() {}
	
	
	
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



	@Override
	public String toString() {
		return "Relación [Centro="+this.idUsuarioCentro.toString()+",Sitters="+this.idUsuarioSitter.toString()+"]";
	}
	
	
	
}
