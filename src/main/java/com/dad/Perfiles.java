package com.dad;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Perfiles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nombre;
	/*
	 * Los perfiles existentes son:
	 * Admin
	 * Padre
	 * Sitter
	 * Star Sitter
	 * Centro
	 * 
	 * */
	public Perfiles() {}
	public Perfiles(String nombre) {
		this.nombre= nombre;
	}
	
	@Override
	public String toString() {
		return "Perfil [Nombre="+this.nombre+"]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
