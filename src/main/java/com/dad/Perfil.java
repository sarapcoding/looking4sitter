package com.dad;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Perfil {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nombre;
	/*
	@OneToMany(mappedBy="perfil")
	private List<Usuario> usuario;
	*/
	/*
	 * Los perfiles existentes son:
	 * Admin
	 * Padre
	 * Sitter
	 * Star Sitter
	 * Centro
	 * */
	public Perfil() {
		//this.usuario = new ArrayList<>();
	}
	
	public Perfil(String nombre) {
		this.setNombre(nombre);
		//this.usuario = new ArrayList<>();
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
/*
	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario.add(usuario);
	}
	*/
	
	

}
