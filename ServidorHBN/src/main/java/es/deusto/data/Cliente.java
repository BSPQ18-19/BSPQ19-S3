package es.deusto.data;

import java.io.Serializable;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1160185906413742133L;

	public enum Modo {
		ADMIN, USER;
	}

	@PrimaryKey
	private String nick;
	private String nombre;
	private String pass;
	private int edad;
	private Modo tipo;

	public Cliente(String nick, String nombre, String pass, int edad, Modo tipo) {
		this.nick = nick;
		this.nombre = nombre;
		this.pass = pass;
		this.edad = edad;
		this.tipo = tipo;
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

	@Override
	public String toString() {
		return "Usuario: " + nombre + "\nNickname: " + nick + "\nEdad: " + edad + "\nTipo de usuario: " + tipo;
	}
}
