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
	private int idUs;
	private String nombre;
	private String pass;
	private String nick;
	private int edad;
	private Modo tipo;
	
	
	public Cliente(int idUs, String nombre, String pass, String nick, int edad, Modo tipo) {
		this.idUs = idUs;
		this.nombre = nombre;
		this.pass = pass;
		this.nick = nick;
		this.edad = edad;
		this.tipo = tipo;
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
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public Modo getTipo() {
		return tipo;
	}
	public void setTipo(Modo tipo) {
		this.tipo = tipo;
	}
	
	
}
