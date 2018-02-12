package com.dad;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class RelSitterCenter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@OneToOne
	private User Id_Centro;
	@OneToMany
	private List<User> Id_Sitter;
	public RelSitterCenter() {}
	
	@Override
	public String toString() {
		return "Relación [Centro="+this.Id_Centro.getNombre()+",Sitters="+this.Id_Sitter+"]";
	}
	
	
	
}
