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

public class Rmi extends UnicastRemoteObject implements IRmi {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7373884561963536199L;
	private String serverName;

	private PersistenceManager pm = null;
	private Transaction tx = null;

	
		

	private HashMap<String, Cliente> hashMap = new HashMap<String, Cliente>();
	public ArrayList<Cliente>c=new ArrayList<Cliente>();
	public Rmi(String serverName) throws RemoteException {
		super();
		this.serverName = serverName;
//		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
//		this.pm = pmf.getPersistenceManager();
//		this.tx = pm.currentTransaction();
		
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
	private Cliente getCliente(String usuario, String contrasenya){
		Cliente clienteCorrecto = null;
        try
        {
            tx.begin();
            System.out.println("Retrieving Extent for Messages");
            Extent<Cliente> e = pm.getExtent(Cliente.class, true);
            Iterator<Cliente> iter = e.iterator();
            boolean seguir = true;
            while (iter.hasNext() && seguir)
            {
                Cliente cliente = iter.next();
                
                if(cliente.getNick().equals(usuario) && cliente.getPass().contentEquals(contrasenya)) {
                	seguir = false;
                	clienteCorrecto = cliente;
                }
            }
            tx.commit();
        }
        catch (Exception e)
        {
            System.out.println("Exception thrown during retrieval of Extent : " + e.getMessage());
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
        
        
		return clienteCorrecto;
	}
	
	@Override
	public boolean login(String usuario, String contrasenya) {
//		System.out.println("login(String "+usuario+", String "+contrasenya+")");
//		if(getCliente(usuario, contrasenya)!=null) {
//			return true;
//		}else{
//			return false;
//		}
		
		System.out.println("login(String "+usuario+", String "+contrasenya+")");
		Cliente u = hashMap.get(usuario);
		if(u == null) return false;
		boolean b = contrasenya.contentEquals(u.getPass());
		System.out.println("\t"+b);
		return b;
	}

	public void registrarse(String usuario, String nick, String pass, String fecha,int tipo) {
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
		Cliente cliente;
		Modo m;
        if (tipo == 0) {
            m = Modo.USER;
            cliente = new Cliente(usuario, pass, nick, fecha, m);
        } else {
            m = Modo.ADMIN;
            cliente = new Cliente(usuario, pass, nick, fecha, m);
        }
        hashMap.put(cliente.getNick(), cliente);
        System.out.println("añadido");
	}

	@Override
	public String[] getPerfiles(String usuario) throws RemoteException {
		return new String[] {"Default"};
	}
	
	

}
