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
	
	@Autowired
	private RemarkRepository comentarioRepositorio;
	
	@PostConstruct
	private void initDatabase(){
		
		Usuario us1 = new Usuario("padrede2","Antonio","García","padrede2123","padrede2@email.com","Madrid",0,"feliz","ROLE_padre");
		Usuario us2 = new Usuario("mia","Amelia","G","mia123","mia@email.com","Madrid",15,"lala","ROLE_sitter");
		Usuario us3 = new Usuario("flor","Flor","Blanca","flor123","flor@email.com","Barcelona",13,"po","ROLE_sitter");
		Usuario us4 = new Usuario("caracoles","Caracoles Center","","caracoles123","caracoles@email.com","Madrid",0,"tinky","ROLE_centro");
		Usuario us5 = new Usuario("kei","Kei","Wong","kei123","kei@email.com","Madrid",0,"Padre de un nene tranquilo","ROLE_padre");
		Usuario us6 = new Usuario("aurora","Aurora","Perez","aurora123","aurora@email.com","Madrid",16,"Seria, responsable","ROLE_sitter");
		Usuario us7 = new Usuario("xtina","Cristina","StClair","xtina123","xtina@email.com","Guipúzqua",13,"Au pair de Londres, responsable, enseño inglés","ROLE_sitter");
		Usuario us8 = new Usuario("popurri","Carlos","Fu","popurri123","popurri@email.com","Albacete",13,"Chico serio, hermano de 5","ROLE_sitter");
		Usuario us9 = new Usuario("nina","Nina","Wong","nina123","nina@email.com","Madrid",0,"Madre de un nene tranquilo","ROLE_padre");
		
		
		usuarioRepositorio.save(us1);
		usuarioRepositorio.save(us2);
		usuarioRepositorio.save(us3);
		usuarioRepositorio.save(us4);
		usuarioRepositorio.save(us5);
		usuarioRepositorio.save(us6);
		usuarioRepositorio.save(us7);
		usuarioRepositorio.save(us8);
		usuarioRepositorio.save(us9);
		
		
		Anuncio an1 = new Anuncio(us1,"Niñer@ para el sábado","Busco a alguien que pueda cuidar mi crío de 3 a 5 este viernes","2018-03-02");
		Anuncio an2 = new Anuncio(us5,"Busco persona","Tengo gemelos que necesitan ser vigilados","2018-03-02");
		Anuncio an3 = new Anuncio(us1,"AAAH","Aaaah","2018-03-03");
		Anuncio an4 = new Anuncio(us5,"Sábado por la noche","Busco persona responsable para cuidar de mi hijo de 6 años este viernes de 9 a 12 de la noche","2018-03-03");
		Anuncio an5 = new Anuncio(us1,"Niñero para la tarde","Busco a alguien que vigile a mi hijo mientras hace los deberes","2018-04-30");
		Anuncio an6 = new Anuncio(us1,"Busco persona que sepa chino","Clases particulares","2018-04-30");
		Anuncio an7 = new Anuncio(us1,"Persona que me lleve los niños a la ruta","Necesito a alguien que acompañe a mis niños desde la casa a la ruta","2018-04-29");
		Anuncio an8 = new Anuncio(us1,"Noche","Necesito a chico/chica que vigile a mi peque una noche","2018-05-07");
		Anuncio an9 = new Anuncio(us1,"Tarde + cumple","Necesito a alguien que vigile al niño en un cumple","2018-04-29");
		Anuncio an10 = new Anuncio(us1,"Niñero para la tarde","Necesito a alguien que le de el colacao al niño","2018-05-07");
		Anuncio an11 = new Anuncio(us5,"Alguien que me cuide los niños en una boda","Sábado de 11 a 6","2018-05-12");
		Anuncio an12 = new Anuncio(us5,"Profe de mates?","Busco a niñero que sea también profe particular","2018-04-30");
		Anuncio an13 = new Anuncio(us5,"URGENTE","Necesito a alguien de 9 a 9 para cuidar de mis hijos","2018-05-05");
		Anuncio an14 = new Anuncio(us5,"Niñero para la tarde","Para después del cole hasta las nueve","2018-04-30");
		Anuncio an15 = new Anuncio(us5,"Busco persona que sepa ruso","Para impartir clases particulares a mis dos chiquillas","2018-05-04");
		Anuncio an16 = new Anuncio(us5,"Persona paciente para niño TDA","Mi hijo tiene déficit de atención y necesito a alguien que tenga paciencia y experiencia","2018-05-06");
				
		anuncioRepositorio.save(an1);
		anuncioRepositorio.save(an2);
		anuncioRepositorio.save(an3);
		anuncioRepositorio.save(an4);
		anuncioRepositorio.save(an5);
		anuncioRepositorio.save(an6);
		anuncioRepositorio.save(an7);
		anuncioRepositorio.save(an8);
		anuncioRepositorio.save(an9);
		anuncioRepositorio.save(an10);
		anuncioRepositorio.save(an11);
		anuncioRepositorio.save(an12);
		anuncioRepositorio.save(an13);
		anuncioRepositorio.save(an14);
		anuncioRepositorio.save(an15);
		anuncioRepositorio.save(an16);
		
		// comentarios para sitters: mia us2 flor us3
		// padres: kei us5 nina us9 padrede2 us1
		
		Comentario c1 = new Comentario(us5,us2,"Gran sitter! Sabe inglés y es muy simpática",9,"2018-06-27");// kei a mia
		Comentario c2 = new Comentario(us9,us2,"Chica responsable",7,"2018-06-23");// nina a mia
		Comentario c3 = new Comentario(us9,us3,"Muy responsable, ¡la recomiendo!",9,"2018-05-30");
		Comentario c4 = new Comentario(us5,us3,"Muy maja",6,"2018-06-01");
		Comentario c5 = new Comentario(us9,us2,"Majísima!",7,"2018-06-01");
		Comentario c6 = new Comentario(us9,us2,"Hizo que le diera un ataque de alergia a mi perro :(",3,"2018-06-01");
		Comentario c7 = new Comentario(us9,us2,"Gran sitter!",8,"2018-06-01");
		
		comentarioRepositorio.save(c1);
		comentarioRepositorio.save(c2);
		comentarioRepositorio.save(c3);
		comentarioRepositorio.save(c4);
		comentarioRepositorio.save(c5);
		comentarioRepositorio.save(c6);
		comentarioRepositorio.save(c7);

	}
}
