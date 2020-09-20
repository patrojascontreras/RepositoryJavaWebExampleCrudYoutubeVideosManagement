package com.patrojascontreras.dto;

public class Categoria {
	private static final long serialVersionUID = -7701704025347198379L;
	
	private int idCategoria;
	private String categoria;
	
	public Categoria() {
		
	}
	
	public Categoria(int idCategoria, String categoria) {
		this.idCategoria = idCategoria;
		this.categoria = categoria;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public String getCategoria() {
		return categoria;
	}
}
