package com.patrojascontreras.dto;

public class Video {
	private static final long serialVersionUID = 3530695931349272562L;
	
	private int idVideo;
	private String nomVideo, urlVideo, categoria;
	
	public int getIdVideo() {
		return idVideo;
	}
	
	public void setIdVideo(int idVideo) {
		this.idVideo = idVideo;
	}
	
	public String getNomVideo() {
		return nomVideo;
	}
	
	public void setNomVideo(String nomVideo) {
		this.nomVideo = nomVideo;
	}
	
	public String getUrlVideo() {
		return urlVideo;
	}
	
	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
