package es.deusto.spq;

import java.rmi.Naming;

import es.deusto.spq.remote.IRmi;
import es.deusto.spq.remote.Rmi;

/**
 * Hello world!
 *
 */
public class Main 
{
	private static final String HOST = "127.0.0.1";
	private static final String PORT = "1099";
	private static final String SERVER_NAME = "ServidorHBN";
	
    public static void main( String[] args )
    {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + HOST + ":" + PORT + "/" + SERVER_NAME;

		try {		
			IRmi objServer = new Rmi(name);
			Naming.rebind(name, objServer);
			System.out.println("* Server '" + name + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
    }
}
