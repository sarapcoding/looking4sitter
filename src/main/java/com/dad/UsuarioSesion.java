package com.dad;

public class UsuarioSesion {
	private Usuarios usuario;
	private Perfiles perfil;
	
	public UsuarioSesion() {}
	
	public UsuarioSesion(Usuarios usuario, Perfiles perfil) {
		setUsuario(usuario);
		setPerfil(perfil);
	}
	
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	public Perfiles getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfiles perfil) {
		this.perfil = perfil;
	}
	
	

}
