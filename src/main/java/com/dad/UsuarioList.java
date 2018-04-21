package com.dad;

import java.util.ArrayList;
import java.util.List;

public class UsuarioList {
	List<Usuario> myList = new ArrayList<>();
	
	public UsuarioList() {
	}
	
	public UsuarioList(List<Usuario> lista) {
		this.setMyList(lista);
	}

	public List<Usuario> getMyList() {
		return myList;
	}

	public void setMyList(List<Usuario> myList) {
		this.myList = myList;
	}
	
	
}
