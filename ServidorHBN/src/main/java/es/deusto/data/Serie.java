package es.deusto.data;

import java.io.Serializable;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Serie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623340830343950062L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private int idPel;
	private String titulo;
	private int anho;
	private String genero;
	private double val;
	private String sinopsis;
	private int temps;
	private int caps;

	public Serie(String titulo, int anho, String genero, double val, String sinopsis, int temps, int caps) {
		this.titulo = titulo;
		this.anho = anho;
		this.genero = genero;
		this.val = val;
		this.sinopsis = sinopsis;
		this.temps = temps;
		this.caps = caps;
	}

	public int getIdPel() {
		return idPel;
	}

	public void setIdPel(int idPel) {
		this.idPel = idPel;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnho() {
		return anho;
	}

	public void setAnho(int anho) {
		this.anho = anho;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public double getVal() {
		return val;
	}

	public void setVal(double val) {
		this.val = val;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public int getCaps() {
		return caps;
	}

	public void setCaps(int caps) {
		this.caps = caps;
	}

	@Override
	public String toString() {
		return "Serie: " + titulo + "\nAño:" + anho + "\nGénero: " + genero + "\nValoración: " + val + "\nSinopsis: "
				+ sinopsis + "\nNº de temporadas: " + temps + "\nNº de capítulos: " + caps;
	}
}