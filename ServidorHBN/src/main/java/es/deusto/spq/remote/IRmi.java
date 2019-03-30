package es.deusto.spq.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRmi extends Remote {
	
	public boolean login(String usuario, String contrasenya) throws RemoteException;
}