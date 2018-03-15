package com.dad;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUsersLoader {
	@Autowired
	private UserRepository usuarioRepositorio;
	
	@Autowired
	private AdvertRepository anuncioRepositorio;
	
	@PostConstruct
	private void initDatabase(){
	//Copiar el initi de AppController. Necesario cambiar la estructura de los controladores para adaptar los rolesde usuario
		
		usuarioRepositorio.save(new Usuario("mia","Amelia","G","mia123","mia@email.com","Madrid",15,"lala","ROLE_sitter"));
		usuarioRepositorio.save(new Usuario("kei","Kei","Wong","kei123","kei@email.com","Madrid",0,"Padre de un nene tranquilo","ROLE_padre"));
		
		Usuario us1 = new Usuario("padrede2","Antonio","García","padrede2123","padrede2@email.com","Madrid",0,"feliz","ROLE_padre");
		//Usuario us2 = new Usuario("mia","Amelia","G","mia123","mia@email.com","Madrid",15,"lala","ROLE_sitter");
		Usuario us3 = new Usuario("flor","Flor","Blanca","flor123","flor@email.com","Barcelona",13,"po","ROLE_sitter");
		Usuario us4 = new Usuario("caracoles","Caracoles Center","","caracoles123","caracoles@email.com","Madrid",0,"tinky","ROLE_centro");
		//Usuario us5 = new Usuario("kei","Kei","Wong","kei123","kei@email.com","Madrid",0,"Padre de un nene tranquilo","ROLE_padre");
		
		usuarioRepositorio.save(us1);
		//usuarioRepositorio.save(us2);
		usuarioRepositorio.save(us3);
		usuarioRepositorio.save(us4);
		//usuarioRepositorio.save(us5);
		
		
		Anuncio an1 = new Anuncio(us1,"Niñer@ para el sábado","Busco a alguien que pueda cuidar mi crío de 3 a 5 este viernes","2018-03-02");
		//Anuncio an2 = new Anuncio(us5,"Busco persona","Tengo gemelos que necesitan ser vigilados","2018-03-02");
		Anuncio an3 = new Anuncio(us1,"AAAH","Aaaah","2018-03-03");
		//Anuncio an4 = new Anuncio(us5,"Sábado por la noche","Busco persona responsable para cuidar de mi hijo de 6 años este viernes de 9 a 12 de la noche","2018-03-03");
		
		
		anuncioRepositorio.save(an1);
		//anuncioRepositorio.save(an2);
		anuncioRepositorio.save(an3);
		//anuncioRepositorio.save(an4);
		
		//usuarioRepositorio.
		/*
		us1.setAnuncio(an1_guardado);
		us1.setAnuncio(an3_guardado);
		us5.setAnuncio(an2_guardado);
		us5.setAnuncio(an4_guardado);
		
		sitter.setUsuario(us2);
		sitter.setUsuario(us3);
		padre.setUsuario(us1);
		padre.setUsuario(us5);
		centro.setUsuario(us4);
		*/
		
		

	}
}
