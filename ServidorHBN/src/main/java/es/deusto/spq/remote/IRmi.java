package es.deusto.spq.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRmi extends Remote {
	
	public boolean login(String usuario, String contrasenya) throws RemoteException;
	public boolean registrarse(String usuario,String nick, String pass,String fecha_nac) throws RemoteException;
}