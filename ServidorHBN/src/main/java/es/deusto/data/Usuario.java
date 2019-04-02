package es.deusto.data;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


public class Usuario implements Serializable{

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", pass=" + pass + ", nick=" + nick + ", fecha_nac=" + fecha_nac + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private int idUs;
	private String nombre;
	private String pass;
	private String nick;
	private String fecha_nac;
	public Usuario(String nombre, String pass, String nick, String fecha_nac) {
		super();
		this.nombre = nombre;
		this.pass = pass;
		this.nick = nick;
		this.fecha_nac = fecha_nac;
	}
	public int getIdUs() {
		return idUs;
	}
	public void setIdUs(int idUs) {
		this.idUs = idUs;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getFecha_nac() {
		return fecha_nac;
	}
	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
