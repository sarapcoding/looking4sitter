package com.dad;
import java.util.List;
import com.dad.Agenda;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Usuario padre;
	private Usuario sitter;
	private int tarifaHora;
	private int numHoras;
	private int fecha;
}
