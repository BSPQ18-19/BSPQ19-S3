package es.deusto.spq.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.data.Cliente;
import es.deusto.data.Cliente.Modo;
import es.deusto.data.Perfil.ControlParental;
import es.deusto.data.Perfil;
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
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public ArrayList<Perfil> p = new ArrayList<Perfil>();

	public Rmi(String serverName) throws RemoteException {
		super();
		this.serverName = serverName;
		// PersistenceManagerFactory pmf =
		// JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		// this.pm = pmf.getPersistenceManager();
		// this.tx = pm.currentTransaction();
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
		ArrayList<Cliente> c = new ArrayList<Cliente>();
		boolean b = false;
		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();

				@SuppressWarnings("unchecked")
				Query<Cliente> usuariosQuery = pm.newQuery("SELECT FROM " + Cliente.class.getName());

				for (Cliente u : usuariosQuery.executeList()) {
					c.add(u);
				}
				clientes = c;

				tx.commit();
			} catch (Exception ex) {

				System.err.println("* Exception executing a query: " + ex.getMessage());

			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}

				pm.close();
			}
			JMainFrame.println("login(String " + usuario + ", String " + contrasenya + ")");

			for (Cliente cl : clientes) {
				if (cl.getNick().equals(usuario)) {
					JMainFrame.println("1");
					b = contrasenya.contentEquals(cl.getPass());
				}
			}
			JMainFrame.println("\t" + b);
		} catch (Exception ex) {
			System.err.println("* Exception: " + ex.getMessage());
		}
		return b;
	}

	public void registrarse(String usuario, String nick, String pass, String fecha, int tipo) {
		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				JMainFrame.println("Comprobando que el usuario no existía previamente '" + nick + "'");
				Cliente user = null;
				Perfil perfil = null;
				Modo m;
				ControlParental cp = ControlParental.FALSE;

				try {
					user = pm.getObjectById(Cliente.class, nick);
				} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
					JMainFrame.println("Exception launched: " + jonfe.getMessage());
				}
				JMainFrame.println("User: " + user);
				if (user != null) {
					JMainFrame.println("Setting password user: " + user);
					user.setPass(pass);
					JMainFrame.println("Password set user: " + user);
				} else {
					if (tipo == 0) {
						m = Modo.USER;
						user = new Cliente(usuario, pass, nick, m);
					} else {
						m = Modo.ADMIN;
						user = new Cliente(usuario, pass, nick, m);
					}
					perfil = new Perfil(usuario + "PerfilPrincipal", fecha, cp);
					user.perfiles.add(perfil);
					JMainFrame.println("Creating user: " + user);
					pm.makePersistent(user);
					// hashMap.put(user.getNick(), user);
					JMainFrame.println("User created: " + user);
				}
				JMainFrame.println("Creating profile: " + perfil);
				pm.makePersistent(perfil);
				tx.commit();

			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
			}
		} catch (Exception ex) {
			System.err.println("* Exception: " + ex.getMessage());
		}

	}

	@Override
	public String[] getPerfiles(String usuario) throws RemoteException {
		String[] usuarios = null;
		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				@SuppressWarnings("unchecked")
				Query<String> perfilQuery = pm
				.newQuery("SQL", "SELECT NOMBREP FROM PERFIL WHERE NICK_OWNER = '" + usuario + "'");
				
				@SuppressWarnings("unchecked")
				Query<Perfil> perfilesQuery = pm
				.newQuery("SELECT FROM " + Perfil.class.getName());
				
				//Hay que cambiarlo (Solo sirve para casos en los que haya 1 perfil)
				String s = null;
				for (String u : perfilQuery.executeList()) {
					s = u;
				}
				//
				
				for (Perfil u : perfilesQuery.executeList()) {
					if (u.getNombreP().equals(s)) {
						p.add(u);
					}
				}

				tx.commit();
			} catch (Exception ex) {

				System.err.println("* Exception executing a query: " + ex.getMessage());

			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}

				pm.close();
			}

			ArrayList<String> nicks = new ArrayList<>();

			for (Perfil u : p) {
				nicks.add(u.getNombreP());
			}

			usuarios = new String[nicks.size()];

			for (int i = 0; i < nicks.size(); i++) {
				usuarios[i] = nicks.get(i);
			}

		} catch (Exception ex) {
			System.err.println("* Exception: " + ex.getMessage());
		}
		return usuarios;
	}
}
