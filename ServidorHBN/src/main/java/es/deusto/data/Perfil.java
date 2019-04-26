package es.deusto.data;

import java.io.Serializable;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Perfil implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -70269628117674220L;
	
	@PrimaryKey
	private String nombreP;
	private String fecha;

	public Perfil(String nombre, String fecha) {
		this.nombreP = nombre;
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombreP;
	}

	public void setNombre(String nombre) {
		this.nombreP = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Perfil [nombreP=" + nombreP + "]";
	}
}
