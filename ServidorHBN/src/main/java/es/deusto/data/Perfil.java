package es.deusto.data;

import java.io.Serializable;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Perfil implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -70269628117674220L;
	
	public enum ControlParental {
		  TRUE, FALSE;
	}
	
	
	@PrimaryKey
	private String nombreP;
	private String fecha;
	private ControlParental ControlParental;

	public Perfil(String nombre, String fecha, ControlParental m) {
		this.nombreP = nombre;
		this.fecha = fecha;
		this.ControlParental = m;
	}

	public String getNombreP() {
		return nombreP;
	}

	public void setNombreP(String nombre) {
		this.nombreP = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public ControlParental getControlParental() {
		return ControlParental;
	}

	public void setControlParental(ControlParental cp) {
		this.ControlParental = cp;
	}
	
	@Override
	public String toString() {
		return "Perfil [nombreP=" + nombreP + "]";
	}
}
