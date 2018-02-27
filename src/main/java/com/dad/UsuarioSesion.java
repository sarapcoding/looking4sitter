package com.dad;

public class UsuarioSesion {
	private Usuario usuario;
	private Perfil perfil;
	
	public UsuarioSesion() {}
	
	public UsuarioSesion(Usuario usuario, Perfil perfil) {
		setUsuario(usuario);
		setPerfil(perfil);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	

}
