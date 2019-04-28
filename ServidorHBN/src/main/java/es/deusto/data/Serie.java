package es.deusto.data;

import java.io.Serializable;
import java.util.*;

import javax.jdo.annotations.*;
import javax.swing.ImageIcon;

@PersistenceCapable
public class Serie implements Serializable, Contenido {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623340830343950062L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private int idSer;
	private String titulo;
	private int anho;
	private String genero;
	private double val;
	private String sinopsis;
	
	@Element(column="ID_SERIE")
	public List<Temporada> temps;
	
	private ImageIcon portada;

	
	public Serie(String titulo, int anho, String genero, double val, String sinopsis) {
		this.titulo = titulo;
		this.anho = anho;
		this.genero = genero;
		this.val = val;
		this.sinopsis = sinopsis;
		this.temps = new ArrayList<Temporada>();
	}

	public int getIdPel() {
		return idSer;
	}

	public void setIdPel(int idPel) {
		this.idSer = idPel;
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

	public List<Temporada> getTemps() {
		return temps;
	}

	public void setTemps(List<Temporada> temps) {
		this.temps = temps;
	}

	@Override
	public String toString() {
		return "Serie: " + titulo + "\nAño:" + anho + "\nGénero: " + genero + "\nValoración: " + val + "\nSinopsis: "
				+ sinopsis;
	}
	
	@Override
	public ImageIcon getPortada() {
		return portada;
	}

	@Override
	public void setPortada(ImageIcon portada) {
		this.portada = portada;
	}
}