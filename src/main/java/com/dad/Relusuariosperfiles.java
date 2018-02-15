package com.dad;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Relusuariosperfiles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long id_Perfil;
	private Long id_Usuario;
	
	
	
	public Relusuariosperfiles() {}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getId_Perfil() {
		return id_Perfil;
	}


	public void setId_Perfil(Long id_Perfil) {
		this.id_Perfil = id_Perfil;
	}


	public Long getId_Usuario() {
		return id_Usuario;
	}


	public void setId_Usuario(Long id_Usuario) {
		this.id_Usuario = id_Usuario;
	}


	@Override
	public String toString() {
		return "Relación [Perfil="+this.id_Perfil.toString()+",Usuarios="+this.id_Usuario.toString()+"]";
	}
	
	
	
}
