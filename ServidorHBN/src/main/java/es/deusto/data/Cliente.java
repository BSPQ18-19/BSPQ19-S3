package es.deusto.data;

import java.io.Serializable;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum Modo {
		  ADMIN, USER;
	}
	
	@PrimaryKey
	private String nick;
	private String nombre;
	private String pass;
	private Modo tipo;
	private String fecha;
	
	
	public Cliente(String nombre, String pass, String nick, String fecha, Modo tipo) {
		this.nombre = nombre;
		this.pass = pass;
		this.nick = nick;
		this.tipo = tipo;
		this.fecha = fecha;
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
	public String getEdad() {
		return fecha;
	}
	public void setEdad(String fecha) {
		this.fecha = fecha;
	}
	
	public Modo getTipo() {
		return tipo;
	}
	public void setTipo(Modo tipo) {
		this.tipo = tipo;
	}
	
	
}
