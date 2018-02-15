package com.dad;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Relsittercentros {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	private Usuarios id_Centro;
	@OneToMany
	private List<Usuarios> id_Sitter;
	public Relsittercentros() {}
	
	@Override
	public String toString() {
		return "Relación [Centro="+this.id_Centro.getNombre()+",Sitters="+this.id_Sitter+"]";
	}
	
	
	
}
