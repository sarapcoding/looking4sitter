package com.dad;

public class UsuarioSesion {
	private Usuario usuario;// usuario que actualmente está iniciado sesión
	
	public UsuarioSesion() {}
	
	public UsuarioSesion(Usuario usuario) {
		setUsuario(usuario);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
