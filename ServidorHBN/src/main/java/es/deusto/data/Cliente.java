package es.deusto.data;

import java.io.Serializable;
import java.util.Collection;
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
	
	@Element(column="NICK_OWNER")
	private Collection<Perfil> perfiles;
	
	
	public Cliente(String nombre, String pass, String nick, Modo tipo) {
		this.nombre = nombre;
		this.pass = pass;
		this.nick = nick;
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
	
	public Modo getTipo() {
		return tipo;
	}
	public void setTipo(Modo tipo) {
		this.tipo = tipo;
	}

	public Collection<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(Collection<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	
}
