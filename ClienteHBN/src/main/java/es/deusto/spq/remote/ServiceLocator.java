package es.deusto.spq.remote;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ServiceLocator {
	
	private static final String HOST = "127.0.0.1";
	private static final String PORT = "1099";
	private static final String SERVER_NAME = "ServidorHBN";
	
	private IRmi stubServer;
	
	public boolean setService(){
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		String name = "//" + HOST + ":" + PORT + "/" + SERVER_NAME;
		try {
			stubServer = (IRmi) java.rmi.Naming.lookup(name);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public IRmi getService(){
		return stubServer;
	}
}

