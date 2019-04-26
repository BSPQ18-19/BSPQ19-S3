package es.deusto.data;

import java.io.Serializable;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Capitulo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8025679469389334045L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private int idCap;
	private String titulo;
	private int anho;
	private long duracion; // ms
	private int edad_rec;
	private String descr;
	private double valoracion;
	
	public Capitulo(String titulo, int anho, long duracion, int edad_rec, String descr, double valoracion) {
		this.titulo = titulo;
		this.anho = anho;
		this.duracion = duracion;
		this.edad_rec = edad_rec;
		this.descr = descr;
		this.valoracion = valoracion;
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
	public long getDuracion() {
		return duracion;
	}
	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}
	public int getEdad_rec() {
		return edad_rec;
	}
	public void setEdad_rec(int edad_rec) {
		this.edad_rec = edad_rec;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public double getValoracion() {
		return valoracion;
	}
	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}
	
}
