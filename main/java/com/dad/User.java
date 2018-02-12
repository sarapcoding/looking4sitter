package com.dad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	// Atributos del usuario
	private String Login;
	private String Nombre;
	private String Password;
	private String Email;
	private String Provincia;
	private int Tarifa;
	private String Descripcion;
		 
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getProvincia() {
		return Provincia;
	}
	public void setProvincia(String provincia) {
		Provincia = provincia;
	}
	public int getTarifa() {
		return Tarifa;
	}
	public void setTarifa(int tarifa) {
		Tarifa = tarifa;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	protected User() {}
	// Constructor padre / centro
	public User (String login, String nombre, String email, String password, String provincia, String descripcion) {
		this.Login = login;
		this.Nombre = nombre;
		this.Password = password;
		this.Email = email;
		this.Provincia = provincia;
		this.Tarifa = 0;
		this.Descripcion = descripcion;
	}
	// constructor sitter
	public User (String login, String nombre, String email, String password, String provincia, String descripcion, int tarifa) {
		this.Login = login;
		this.Nombre = nombre;
		this.Password = password;
		this.Email = email;
		this.Provincia = provincia;
		this.Tarifa = tarifa;
		this.Descripcion = descripcion;
	}


	@Override
	public String toString() {
		return "Usuario [Login=" + this.Login +", Nombre=" + this.Nombre +", Provincia="+this.Provincia +"]";
	}
}
