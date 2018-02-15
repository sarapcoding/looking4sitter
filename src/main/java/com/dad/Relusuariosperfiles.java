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
	@OneToOne
	private Perfiles id_Perfil;
	@OneToMany
	private List<Usuarios> id_Usuario;
	
	
	
	public Relusuariosperfiles() {}
	
	
	@Override
	public String toString() {
		return "Relación [Perfil="+this.id_Perfil.getNombre()+",Usuarios="+this.id_Usuario+"]";
	}
	
	
	
}
