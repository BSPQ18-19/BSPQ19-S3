package es.deusto.spq.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRmi extends Remote {
	
	public boolean login(String usuario, String contrasenya) throws RemoteException;

	public void registrarse(String usuario,String nick, String pass,String fecha_nac,int tipo) throws RemoteException;

	public String[] getPerfiles(String usuario) throws RemoteException;

}