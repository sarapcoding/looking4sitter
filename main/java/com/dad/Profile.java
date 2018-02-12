package com.dad;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String Nombre;
	/*
	 * Los perfiles existentes son:
	 * Admin
	 * Padre
	 * Sitter
	 * Star Sitter
	 * Centro
	 * 
	 * */
	public Profile() {}
	public Profile(String nombre) {
		this.Nombre= nombre;
	}
	
	@Override
	public String toString() {
		return "Perfil [Nombre="+this.Nombre+"]";
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	

}
