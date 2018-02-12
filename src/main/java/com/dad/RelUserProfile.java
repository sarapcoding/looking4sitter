package com.dad;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class RelUserProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@OneToOne
	private Profile Id_Perfil;
	@OneToMany
	private List<User> Id_Usuario;
	
	
	
	public RelUserProfile() {}
	
	
	@Override
	public String toString() {
		return "Relación [Perfil="+this.Id_Perfil.getNombre()+",Usuarios="+this.Id_Usuario+"]";
	}
	
	
	
}
