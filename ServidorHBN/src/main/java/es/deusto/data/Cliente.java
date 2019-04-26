package es.deusto.data;

import java.io.Serializable;
import java.util.*;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4267654683453575942L;
	
	public enum Modo {
		  ADMIN, USER;
	}
	
	@PrimaryKey
	private String nick;
	private String nombre;
	private String pass;
	private Modo tipo;
	
	@Element(column="NICK_OWNER")
	public List<Perfil> perfiles;
	
	
	public Cliente(String nombre, String pass, String nick, Modo tipo) {
		this.nombre = nombre;
		this.pass = pass;
		this.nick = nick;
		this.tipo = tipo;
		this.perfiles = new ArrayList<Perfil>();
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
	
	public Modo getTipo() {
		return tipo;
	}
	public void setTipo(Modo tipo) {
		this.tipo = tipo;
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	@Override
	public String toString() {
		return "Cliente [nick=" + nick + "]";
	}
	
}
