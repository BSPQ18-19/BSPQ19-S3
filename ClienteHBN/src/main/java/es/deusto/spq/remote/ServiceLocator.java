package es.deusto.spq.remote;

import java.rmi.RemoteException;

public class ServiceLocator {
	
	private static final String HOST = "127.0.0.1";
	private static final String PORT = "1099";
	private static final String SERVER_NAME = "ServidorHBN";
	
	private IRmi stubServer;
	
	public void setService(){
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			String name = "//" + HOST + ":" + PORT + "/" + SERVER_NAME;
			stubServer = (IRmi) java.rmi.Naming.lookup(name);
			//System.out.println("* Message coming from the server: '" + stubServer.sayHello() + "'");
//			try {
//				stubServer.registerUser("usuario", "contraseña");
//				System.out.println(stubServer.sayMessage("usuario", "contraseña", "Hola, esto es un mensaje"));
//				System.out.println(stubServer.sayMessage("usuario", "contraseña incorrecta", "Esto no se va a ver"));
//			} catch (RemoteException e) {
//				System.err.println("Error al autentificarse: "+ e.getMessage());
//			}
//			try {
//				System.out.println(stubServer.sayMessage("usuarioSinRegistrar", "contraseña2", "Esto no se va a ver"));
//			} catch (RemoteException e) {
//				System.err.println("Error al autentificarse: "+ e.getMessage());
//			}
		} catch (Exception e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public IRmi getService(){
		return stubServer;
	}
	
	public static void main(String args[]) {
		ServiceLocator locator = new ServiceLocator();
		locator.setService();
		try {
			locator.getService().login("u", "c");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

