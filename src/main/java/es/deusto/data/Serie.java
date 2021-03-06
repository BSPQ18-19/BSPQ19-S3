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
	private int edad_rec;
	private double val;
	private String sinopsis;
	private int numVotos;
	
	@Element(column="ID_SERIE")
	public List<Temporada> temps;
	
//	private ImageIcon portada;

	
	public Serie(String titulo, int anho, String genero, int edad_rec, double val, String sinopsis, int numVotos) {
		this.titulo = titulo;
		this.anho = anho;
		this.genero = genero;
		this.val = val;
		this.edad_rec = edad_rec;
		this.sinopsis = sinopsis;
		this.numVotos=numVotos;
		this.temps = new ArrayList<Temporada>();
	}

	public int getNumVotos() {
		return numVotos;
	}

	public void setNumVotos(int numVotos) {
		this.numVotos = numVotos;
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

	public int getEdad_rec() {
		return edad_rec;
	}

	public void setEdad_Rec(int edad_rec) {
		this.edad_rec = edad_rec;
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
				+ sinopsis+ "Temp:" + temps.toString();
	}
	
	@Override
	public ImageIcon getPortada() {
		return null;
	}

	@Override
	public void setPortada(ImageIcon portada) {
//		this.portada = portada;
	}
}