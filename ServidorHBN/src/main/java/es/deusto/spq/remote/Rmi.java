package es.deusto.spq.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.data.Cliente;
import es.deusto.data.Cliente.Modo;
import es.deusto.spq.JMainFrame;

public class Rmi extends UnicastRemoteObject implements IRmi {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7373884561963536199L;
	private String serverName;

	private PersistenceManager pm = null;
	private Transaction tx = null;

	private HashMap<String, Cliente> hashMap = new HashMap<String, Cliente>();
	public ArrayList<Cliente> c = new ArrayList<Cliente>();

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

	@SuppressWarnings("unused")
	private Cliente getCliente(String usuario, String contrasenya) {
		Cliente clienteCorrecto = null;
		try {
			tx.begin();
			JMainFrame.println("Retrieving Extent for Messages");
			Extent<Cliente> e = pm.getExtent(Cliente.class, true);
			Iterator<Cliente> iter = e.iterator();
			boolean seguir = true;
			while (iter.hasNext() && seguir) {
				Cliente cliente = iter.next();

				if (cliente.getNick().equals(usuario) && cliente.getPass().contentEquals(contrasenya)) {
					seguir = false;
					clienteCorrecto = cliente;
				}
			}
			tx.commit();
		} catch (Exception e) {
			JMainFrame.println("Exception thrown during retrieval of Extent : " + e.getMessage());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return clienteCorrecto;
	}

	@Override
	public boolean login(String usuario, String contrasenya) {
//		JMainFrame.println("login(String "+usuario+", String "+contrasenya+")");
//		if(getCliente(usuario, contrasenya)!=null) {
//			return true;
//		}else{
//			return false;
//		}

		JMainFrame.println("login(String " + usuario + ", String " + contrasenya + ")");
		Cliente u = hashMap.get(usuario);
		if (u == null)
			return false;
		boolean b = contrasenya.contentEquals(u.getPass());
		JMainFrame.println("\t" + b);
		return b;
	}

	public void registrarse(String usuario, String nick, String pass, String fecha, int tipo) {
		try {
			tx.begin();
			JMainFrame.println("Comprobando que el usuario no existe '" + usuario + "'");
			Cliente user = null;
			Modo m;
			try {
				user = pm.getObjectById(Cliente.class, usuario);
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				JMainFrame.println("Exception launched: " + jonfe.getMessage());
			}
			JMainFrame.println("User: " + user);
			if (user != null) {
				JMainFrame.println("Setting password user: " + user);
				user.setPass(pass);
				JMainFrame.println("Password set user: " + user);
			} else {
				JMainFrame.println("Creating user: " + user);
				if (tipo == 0) {
					m = Modo.USER;
					user = new Cliente(usuario, pass, nick, fecha, m);
				} else {
					 m = Modo.ADMIN;
					user = new Cliente(usuario, pass, nick, fecha, m);
				}
				pm.makePersistent(user);
				hashMap.put(user.getNick(), user);
				JMainFrame.println("User created: " + user);
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}

		
	}

	@Override
	public String[] getPerfiles(String usuario) throws RemoteException {
		return new String[] { "Default" };
	}

}
