package com.dad;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.domain.Page;

@Entity
public class Agenda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	private Usuario sitter; // usuario sitter al que pertenece la agenda
	
	@OneToMany(mappedBy="agenda")
	private List<Hora> hora; // dias en la agenda
	
	public Agenda () {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getSitter() {
		return sitter;
	}

	public void setSitter(Usuario sitter) {
		this.sitter = sitter;
	}
	
	

	
	public List<Hora> getHora() {
		return hora;
	}

	public void setHora(List<Hora> hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "Agenda de "+this.sitter.getLogin();
	}
	

}
