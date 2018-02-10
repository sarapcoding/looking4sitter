package com.dad;

public class Advert {
	private String id_padre;
	private String asunto;
	private String comentario;
	private String ciudad;
	private String fecha_inicio;
	private int salario; 
	public Advert(){
		
	}
	public String getId_nombre() {
		return id_padre;
	}
	public void setId_nombre(String nombre) {
		this.id_padre = nombre;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	@Override
	public String toString() {
		return "Anuncio [nombre=" + id_padre + ", asunto=" + asunto + ", comentario=" + comentario + "]";
	}

	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
}
