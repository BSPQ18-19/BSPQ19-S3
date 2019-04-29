package es.deusto.spq.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import es.deusto.data.Perfil;

public interface IRmi extends Remote {
	
	public boolean login(String usuario, String contrasenya) throws RemoteException;

	public void registrarse(String usuario,String nick, String pass,String fecha_nac,int tipo) throws RemoteException;

	public Perfil[] getPerfiles(String usuario) throws RemoteException;
	
	public boolean getTipo(String usuario) throws RemoteException;
	
//	public void crearPerfil(String usuario, String nombreP, String fecha);
	public void cambiarControlParental(Perfil p) throws RemoteException;
}