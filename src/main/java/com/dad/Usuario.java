package com.dad;

import java.util.ArrayList;
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
	private String passwordHash;
	private String email;
	private String provincia;
	private int tarifa;
	private String descripcion;
	
	//@ElementCollection (fetch= FetchType.EAGER)
	//private List<String> roles;
	
	@ManyToOne
	private Perfil perfil;
	
	@OneToMany(mappedBy="usuario")
	private List<Anuncio> anuncio;
	
	@OneToOne
	private Usuario centro;
	
	@OneToMany(mappedBy="origen")
	private List<Comentario> comentario_escrito;
	
	@OneToMany(mappedBy="destino")
	private List<Comentario> comentario_destinado;
	
	@OneToOne
	private Agenda agenda;
	
	@OneToMany(mappedBy="padre")
	private List<Hora> hora;
	
	//private List<Mensaje> mensaje;
		 
	
	protected Usuario(String login,
					String nombre,
					String apellido,
					String password,
					String email,
					String provincia,
					int tarifa,
					String descrip,
					Perfil perfil
					) {
		this.setLogin(login);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setPasswordHash(password);
		this.setEmail(email);
		this.setProvincia(provincia);
		this.setTarifa(tarifa);
		this.setDescripcion(descrip);
		this.setPerfil(perfil);
		this.anuncio = new ArrayList<>();
		this.comentario_destinado = new ArrayList<>();
		this.comentario_escrito= new ArrayList<>();
	}
	

	public Usuario() {
		this.anuncio = new ArrayList<>();
		this.comentario_destinado = new ArrayList<>();
		this.comentario_escrito= new ArrayList<>();
		
	}

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



	public String getPasswordHash() {
		return passwordHash;
	}



	public void setPasswordHash(String password) {
		this.passwordHash = password;
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



	public void setAnuncio(Anuncio anuncio) {
		this.anuncio.add(anuncio);
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public Usuario getCentro() {
		return centro;
	}



	public void setCentro(Usuario centro) {
		this.centro = centro;
	}



	public List<Comentario> getComentario_escrito() {
		return comentario_escrito;
	}


	public List<Comentario> getComentario_destinado() {
		return comentario_destinado;
	}



	public void setComentario_destinado(Comentario comentario_destinado) {
		this.comentario_destinado.add(comentario_destinado);
	}



	public Agenda getAgenda() {
		return agenda;
	}


	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}


	public List<Hora> getHora() {
		return hora;
	}


	public void setHora(Hora hora) {
		this.hora.add(hora);
	}


	public void setAnuncio(List<Anuncio> anuncio) {
		this.anuncio = anuncio;
	}


	public void setComentario_escrito(Comentario comentario_escrito) {
		this.comentario_escrito.add(comentario_escrito);
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
