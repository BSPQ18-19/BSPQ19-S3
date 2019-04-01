package es.deusto.data;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class UsuarioEstandar extends Usuario
{

	public UsuarioEstandar(int idUs, String nombre, String pass, String nick, int edad) {
		super(idUs, nombre, pass, nick, edad);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
