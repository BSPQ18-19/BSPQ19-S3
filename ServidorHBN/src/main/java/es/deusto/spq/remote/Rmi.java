package es.deusto.spq.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.data.Administrador;
import es.deusto.data.Usuario;
import es.deusto.data.UsuarioEstandar;

public class Rmi extends UnicastRemoteObject implements IRmi {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7373884561963536199L;
	private String serverName;

	private PersistenceManager pm = null;
	private Transaction tx = null;

	
		

	
	private HashMap<String, String[]> perfiles = new HashMap<String, String[]>();
	private HashMap<String, String> usuarios = new HashMap<String, String>();
	public ArrayList<Usuario>u=new ArrayList<Usuario>();
	public Rmi(String serverName) throws RemoteException {
		super();
		this.serverName = serverName;
//		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
//		this.pm = pmf.getPersistenceManager();
//		this.tx = pm.currentTransaction();
		//Usuarios y perfiles de prueba
		
		perfiles.put("josu", new String[] {"1", "2", "3"});
		usuarios.put("josu", "josu");
	}

	public String getName() {
		return serverName;
	}

	protected void finalize() throws Throwable {
		if (tx.isActive()) {
			tx.rollback();
		}
		pm.close();
	}

	@Override
	public boolean login(String usuario, String contrasenya) {
		// TODO Añadir lógica

		System.out.println("login(String " + usuario + ", String " + contrasenya + ")");
		return true;
	}

	public void registrarse(String usuario, String nick, String pass, String fecha_nac,int tipo) {
//		try {
//			tx.begin();
//			System.out.println("Comprobando que el usuario no existe '" + usuario + "'");
//			Usuario user = null;
//			try {
//				user = pm.getObjectById(Usuario.class, usuario);
//			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
//				System.out.println("Exception launched: " + jonfe.getMessage());
//			}
//			System.out.println("User: " + user);
//			if (user != null) {
////				System.out.println("Setting password user: " + user);
////				user.setPassword(password);
////				System.out.println("Password set user: " + user);
//			} else {
//				System.out.println("Creating user: " + user);
//				if(tipo==0) {
//				user = new UsuarioEstandar(usuario, pass, nick, fecha_nac);}
//				else{
//				user = new Administrador(usuario, pass, nick, fecha_nac);
//				}
//				pm.makePersistent(user);
//				System.out.println("User created: " + user);
//			}
//			tx.commit();
//		} finally {
//			if (tx.isActive()) {
//				tx.rollback();
//			}
//		}
		Usuario user ;
		if(tipo==0) {
			user = new UsuarioEstandar(usuario, pass, nick, fecha_nac);}
			else{
			user = new Administrador(usuario, pass, nick, fecha_nac);
			}
		u.add(user);
		System.out.println("añadido");

	}

	@Override
	public String[] getPerfiles(String usuario) throws RemoteException {
		return perfiles.get(usuario);
	}
	
	

}
