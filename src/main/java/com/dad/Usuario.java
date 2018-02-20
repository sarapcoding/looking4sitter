package com.dad;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// Atributos del usuario
	private String login;
	private String nombre;
	private String apellido;
	private String password;
	private String email;
	private String provincia;
	private int tarifa;
	private String descripcion;
	
	@ManyToOne
	private Perfil perfil;
	
	@OneToMany(mappedBy="usuario")
	private List<Anuncio> anuncio;
	
	@OneToOne
	private Usuario centro;
	
	//private List<Comentario> comentario;
	//private List<Mensaje> mensaje;
		 
	
	protected Usuario() {}
	


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getProvincia() {
		return provincia;
	}



	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}



	public int getTarifa() {
		return tarifa;
	}



	public void setTarifa(int tarifa) {
		this.tarifa = tarifa;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

public Perfil getPerfil() {
		return perfil;
	}



	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}



	public List<Anuncio> getAnuncio() {
		return anuncio;
	}



	public void setAnuncio(List<Anuncio> anuncio) {
		this.anuncio = anuncio;
	}



	/*
	@Override
	public String toString() {
		return "Usuario [Login=" + this.login +", Nombre=" + this.nombre +", Provincia="+this.provincia +"]";
	}
*/
	@Override
	public String toString() {
		return "Login=" + this.login +" - Nombre=" + this.nombre +" - Provincia="+this.provincia +" - Tarifa="+this.tarifa;
	}
	
}
