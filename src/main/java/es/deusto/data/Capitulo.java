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
	private long duracion; // ms
	private String descr;
	private double valoracion;
	
	public Capitulo(String titulo, long duracion, String descr, double valoracion) {
		this.titulo = titulo;
		this.duracion = duracion;
		this.descr = descr;
		this.valoracion = valoracion;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public long getDuracion() {
		return duracion;
	}
	public void setDuracion(long duracion) {
		this.duracion = duracion;
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
