package es.deusto.spq.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class Rmi extends UnicastRemoteObject implements IRmi {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7373884561963536199L;
	private String serverName;
	
	private HashMap<String, String[]> perfiles = new HashMap<String, String[]>();
	private HashMap<String, String> usuarios = new HashMap<String, String>();
	
	public Rmi(String serverName) throws RemoteException {
		super();
		this.serverName = serverName;
		
		//Usuarios y perfiles de prueba
		
		perfiles.put("josu", new String[] {"1", "2", "3"});
		usuarios.put("josu", "josu");
	}
	
	public String getName() {
		return serverName;
	}

	@Override
	public boolean login(String usuario, String contrasenya) {
		// TODO Añadir lógica
		System.out.println("login(String "+usuario+", String "+contrasenya+")");
		return contrasenya.contentEquals(usuarios.get(usuario));
	}

	@Override
	public String[] getPerfiles(String usuario) throws RemoteException {
		return perfiles.get(usuario);
	}
	
	

}
