package es.deusto.spq.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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
import es.deusto.data.Contenido;
import es.deusto.data.Pelicula;
import es.deusto.data.Perfil;
import es.deusto.data.Serie;
import es.deusto.data.Perfil.ControlParental;
import es.deusto.spq.JMainFrame;

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
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
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
					clientes.add(u);
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
			JMainFrame.println("login(String " + usuario + ", String " + contrasenya + ")");

			for (Cliente cl : clientes) {
				if (cl.getNick().equals(usuario)) {
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
	public Perfil[] getPerfiles(String usuario) throws RemoteException {
		ArrayList<Perfil> perfiles = new ArrayList<Perfil>();
		//		String[] usuarios = null;
		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				@SuppressWarnings("unchecked")
				Query<String> perfilQuery = pm.newQuery("SQL",
						"SELECT NOMBREP FROM PERFIL WHERE NICK_OWNER = '" + usuario + "'");

				@SuppressWarnings("unchecked")
				Query<Perfil> perfilesQuery = pm.newQuery("SELECT FROM " + Perfil.class.getName());

				// Hay que cambiarlo (Solo sirve para casos en los que haya 1 perfil)
				String s = null;
				for (String u : perfilQuery.executeList()) {
					s = u;
				}
				//

				for (Perfil u : perfilesQuery.executeList()) {
					if (u.getNombreP().equals(s)) {
						perfiles.add(u);
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

			//			ArrayList<String> nicks = new ArrayList<>();
			//
			//			for (Perfil u : p) {
			//				nicks.add(u.getNombreP());
			//			}
			//
			//			usuarios = new String[nicks.size()];
			//
			//			for (int i = 0; i < nicks.size(); i++) {
			//				usuarios[i] = nicks.get(i);
			//			}

		} catch (Exception ex) {
			System.err.println("* Exception: " + ex.getMessage());
		}
		return perfiles.toArray(new Perfil[perfiles.size()]);
	}
	//
	//	@Override
	//	public void crearPerfil(String usuario, String nombreP, String fecha) {
	//		try {
	//			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	//			PersistenceManager pm = pmf.getPersistenceManager();
	//			Transaction tx = pm.currentTransaction();
	//			try {
	//				tx.begin();
	//				JMainFrame.println("Comprobando que el usuario no existía previamente '" + usuario + "'");
	//				Cliente user = null;
	//				Perfil perfil = null;
	//				ControlParental cp = ControlParental.FALSE;
	//
	//				for (Cliente c : clientes) {
	//					if (c.getNick().equals(usuario)) {
	//						user = c;
	//					}
	//				}
	//				perfil = new Perfil(nombreP, fecha, cp);
	//				user.perfiles.add(perfil);
	//				pm.makePersistent(user);
	//				JMainFrame.println("Creating profile: " + perfil);
	//				pm.makePersistent(perfil);
	//				tx.commit();
	//
	//			} finally {
	//				if (tx.isActive()) {
	//					tx.rollback();
	//				}
	//			}
	//		} catch (Exception ex) {
	//			System.err.println("* Exception: " + ex.getMessage());
	//		}
	//
	//	}

	public void cambiarControlParental(Perfil p)throws RemoteException {
		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				if(p.getControlParental()==ControlParental.TRUE){
					p.setControlParental(ControlParental.FALSE);
				}else {
					p.setControlParental(ControlParental.TRUE);
				}
				pm.makePersistent(p);
				JMainFrame.println("Control Parental actualizado");
				tx.commit();
			} catch (Exception ex) {

			//	System.err.println("* Exception executing a query: " + ex.getMessage());

			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}

				pm.close();
			}
		} catch (Exception ex) {
			System.err.println("* Exception: " + ex.getMessage());
		}
		
	}
		
	@Override
	public boolean getTipo(String usuario) throws RemoteException {
		//false = usuario
		//true = admin
		boolean tipo = false;
		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				@SuppressWarnings("unchecked")
				Query<Cliente> clientesQuery = pm.newQuery("SELECT FROM " + Cliente.class.getName());

				for (Cliente c : clientesQuery.executeList()) {
					if (c.getNick().equals(usuario)) {
						if (c.getTipo() == Modo.ADMIN) {
						tipo = true;
						}
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
		} catch (Exception ex) {
			System.err.println("* Exception: " + ex.getMessage());
		}
		return tipo;
	}
	public static void main(String[] args) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
		    tx.begin();
		    Pelicula p = new Pelicula("p", 1, 1, "Drama", 1, "", 1);
		    Serie s = new Serie("s", 1, "Drama", 1, "");
		    pm.makePersistent(p);
		    pm.makePersistent(s);
		    tx.commit();
		}
		finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		    }
		    pm.close();
		}
		try {
			Rmi rmi = new Rmi("");
			Contenido[] resultado = rmi.buscarPelicula("Drama", "t");
			System.out.println(java.util.Arrays.toString(resultado));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}


	@Override
	public Pelicula[] buscarPelicula(String genero, String campoDeBusqueda) throws RemoteException {
		return buscarPelicula(genero, campoDeBusqueda, "Por defecto");
	}

	@Override
	public Serie[] buscarSerie(String genero, String campoDeBusqueda) throws RemoteException {
		return buscarSerie(genero, campoDeBusqueda, "Por defecto");
	}

	@Override
	public Pelicula[] buscarPelicula(String genero, String campoDeBusqueda, String modo) throws RemoteException {
		ArrayList<Pelicula> arrayList = new ArrayList<Pelicula>();
		
		String nombreTabla;
		
		nombreTabla = Pelicula.class.getName();
		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try
			{
			    tx.begin();

			    @SuppressWarnings("rawtypes")
				Query q = pm.newQuery("SELECT FROM " + nombreTabla );
			    @SuppressWarnings("unchecked")
				List<Pelicula> contenidos = (List<Pelicula>)q.execute();
			    Iterator<Pelicula> iter = contenidos.iterator();
			    while (iter.hasNext())
			    {
			    	Pelicula p = iter.next();
			    	if(p.getTitulo().contains(campoDeBusqueda) && p.getGenero().equalsIgnoreCase(genero)) {
			    		if(modo.equalsIgnoreCase("Sinopsis vacía")) {
			    			if(p.getSinopsis().equals("")) {
			    				arrayList.add(p);
			    			}
			    		}else if(modo.equalsIgnoreCase("Título vacío")) {
			    			if(p.getTitulo().equals("")) {
			    				arrayList.add(p);
			    			}
			    		}else {
			    			arrayList.add(p);
			    		}
			    		
			    	}
			    	
			    }

			    
			    tx.commit();
			    //Si no se pone este for, no se inicializan las variables
			    for(Contenido c: arrayList) {
			    	c.toString();
			    }
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally
			{
			    if (tx.isActive())
			    {
			        tx.rollback();
			    }

			    pm.close();
			}
		} catch (Exception e) {
			System.err.println("* Exception: " + e.getMessage());
		}
		return arrayList.toArray(new Pelicula[arrayList.size()]);
	}

	@Override
	public Serie[] buscarSerie(String genero, String campoDeBusqueda, String modo) throws RemoteException {
		ArrayList<Serie> arrayList = new ArrayList<Serie>();
		
		String nombreTabla;
		
		nombreTabla = Serie.class.getName();
		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try
			{
			    tx.begin();

			    @SuppressWarnings("rawtypes")
				Query q = pm.newQuery("SELECT FROM " + nombreTabla );
			    @SuppressWarnings("unchecked")
				List<Serie> contenidos = (List<Serie>)q.execute();
			    Iterator<Serie> iter = contenidos.iterator();
			    while (iter.hasNext())
			    {
			    	Serie s = iter.next();
			    	if(s.getTitulo().contains(campoDeBusqueda) && s.getGenero().equalsIgnoreCase(genero)) {
			    		if(modo.equalsIgnoreCase("Sinopsis vacía")) {
			    			if(s.getSinopsis().equals("")) {
			    				arrayList.add(s);
			    			}
			    		}else if(modo.equalsIgnoreCase("Título vacío")) {
			    			if(s.getTitulo().equals("")) {
			    				arrayList.add(s);
			    			}
			    		}else {
			    			arrayList.add(s);
			    		}
			    	}
			    	
			    }

			    
			    tx.commit();
			    //Si no se pone este for, no se inicializan las variables
			    for(Contenido c: arrayList) {
			    	c.toString();
			    }
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally
			{
			    if (tx.isActive())
			    {
			        tx.rollback();
			    }

			    pm.close();
			}
		} catch (Exception e) {
			System.err.println("* Exception: " + e.getMessage());
		}
		return arrayList.toArray(new Serie[arrayList.size()]);
	}
}
