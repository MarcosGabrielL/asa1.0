package com.softsaj.AsaSpring.models;

public class Torrent {
	
	private String idioma;
	private String url;
	private String type;
	private String name;
	private String uploaded;
	private String tamanho;
	private String seeders;
	private String tipo;
	private String temporada;
	private String episodio;
	
	
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUploaded() {
		return uploaded;
	}
	public void setUploaded(String uploaded) {
		this.uploaded = uploaded;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getSeeders() {
		return seeders;
	}
	public void setSeeders(String seeders) {
		this.seeders = seeders;
	}
	public Torrent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTemporada() {
		return temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	public String getEpisodio() {
		return episodio;
	}
	public void setEpisodio(String episodio) {
		this.episodio = episodio;
	}
	
	

}
