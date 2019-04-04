package es.deusto.data;

import java.io.Serializable;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Pelicula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6479758331057618872L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private int idPel;
	private String titulo;
	private int anho;
	private long duracion; // ms
	private String genero;
	private int edad_rec;
	private String sinopsis;
	private double valoracion;

	public Pelicula(String titulo, int anho, long duracion, String genero, int edad_rec, String sinopsis,
			double valoracion) {
		this.titulo = titulo;
		this.anho = anho;
		this.duracion = duracion;
		this.genero = genero;
		this.edad_rec = edad_rec;
		this.sinopsis = sinopsis;
		this.valoracion = valoracion;
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

	public int getAño() {
		return anho;
	}

	public void setAño(int año) {
		this.anho = año;
	}

	public long getDuracion() {
		return duracion;
	}

	public void setDuracion(long duracion) {
		this.duracion = duracion;
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

	public void setEdad_rec(int edad_rec) {
		this.edad_rec = edad_rec;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public double getValoracion() {
		return valoracion;
	}

	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}

	@Override
	public String toString() {
		return "Película: " + titulo + "\nAño: " + anho + "\nDuración: " + duracion + "\nGénero: " + genero + "\nEdad recomendada: " + edad_rec + "\nSinopsis: " + sinopsis + "\nValoración: " + valoracion;
	}

}
