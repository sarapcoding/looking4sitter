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
	private Long Id;
	@OneToOne
	private Perfiles Id_Perfil;
	@OneToMany
	private List<Usuarios> Id_Usuario;
	
	
	
	public Relusuariosperfiles() {}
	
	
	@Override
	public String toString() {
		return "Relación [Perfil="+this.Id_Perfil.getNombre()+",Usuarios="+this.Id_Usuario+"]";
	}
	
	
	
}
