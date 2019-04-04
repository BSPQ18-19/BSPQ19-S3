package es.deusto.data;

import java.io.Serializable;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	private int idUs;
	private String nombre;
	private String pass;
	private String nick;
	private int edad;
	private int tipo;
	
	
	public Cliente(int idUs, String nombre, String pass, String nick, int edad, int tipo) {
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
	
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
