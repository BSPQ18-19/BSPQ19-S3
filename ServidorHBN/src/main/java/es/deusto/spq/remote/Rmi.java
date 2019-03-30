package es.deusto.spq.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Rmi extends UnicastRemoteObject implements IRmi {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7373884561963536199L;
	private String serverName;
	public Rmi(String serverName) throws RemoteException {
		super();
		this.serverName = serverName;
	}
	
	public String getName() {
		return serverName;
	}

	@Override
	public boolean login(String usuario, String contrasenya) {
		// TODO Añadir lógica
		System.out.println("login(String "+usuario+", String "+contrasenya+")");
		return true;
	}

}
