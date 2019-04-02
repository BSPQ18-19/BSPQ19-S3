package es.deusto.spq.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.data.Usuario;

public class Rmi extends UnicastRemoteObject implements IRmi {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7373884561963536199L;
	private String serverName;
	private PersistenceManager pm = null;
	private Transaction tx = null;

	public Rmi(String serverName) throws RemoteException {
		super();
		this.serverName = serverName;
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
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

	public boolean registrarse(String usuario, String nick, String pass, String fecha_nac) {
		try {
			tx.begin();
			System.out.println("Comprobando que el usuario no existe '" + usuario + "'");
			Usuario user = null;
			try {
				user = pm.getObjectById(Usuario.class, usuario);
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				System.out.println("Exception launched: " + jonfe.getMessage());
			}
			System.out.println("User: " + user);
			if (user != null) {
//				System.out.println("Setting password user: " + user);
//				user.setPassword(password);
//				System.out.println("Password set user: " + user);
			} else {
				System.out.println("Creating user: " + user);
				user = new Usuario(usuario, pass, nick, fecha_nac);
				pm.makePersistent(user);
				System.out.println("User created: " + user);
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return true;
	}

}
