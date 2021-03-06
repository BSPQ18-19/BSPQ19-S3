package es.deusto.spq.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import es.deusto.spq.IMessagePrinter;
import es.deusto.spq.JMainFrame;
import es.deusto.spq.TipoMensaje;

public class Rmi extends UnicastRemoteObject implements IRmi {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7373884561963536199L;

	private PersistenceManagerFactory pmf;
	private PersistenceManager pm = null;
	private Transaction tx = null;
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	private IMessagePrinter messagePrinter;

	// private HashMap<String, Cliente> hashMap = new HashMap<String, Cliente>();
	// public ArrayList<Perfil> p = new ArrayList<Perfil>();

	public Rmi(IMessagePrinter messagePrinter) throws RemoteException {
		super();
		this.messagePrinter = messagePrinter;
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
	}

	// protected void finalize() throws Throwable {
	// if (tx.isActive()) {
	// tx.rollback();
	// }
	// pm.close();
	// }

	// @SuppressWarnings("unused")
	// private Cliente getCliente(String usuario, String contrasenya) {
	// Cliente clienteCorrecto = null;
	// try {
	// tx.begin();
	// messagePrinter.println("Retrieving Extent for Messages");
	// Extent<Cliente> e = pm.getExtent(Cliente.class, true);
	// Iterator<Cliente> iter = e.iterator();
	// boolean seguir = true;
	// while (iter.hasNext() && seguir) {
	// Cliente cliente = iter.next();
	//
	// if (cliente.getNick().equals(usuario) &&
	// cliente.getPass().contentEquals(contrasenya)) {
	// seguir = false;
	// clienteCorrecto = cliente;
	// }
	// }
	// tx.commit();
	// } catch (Exception e) {
	// messagePrinter.println("Exception thrown during retrieval of Extent : " +
	// e.getMessage());
	// } finally {
	// if (tx.isActive()) {
	// tx.rollback();
	// }
	// pm.close();
	// }
	//
	// return clienteCorrecto;
	// }

	@Override
	public boolean login(String usuario, String contrasenya) {
		clientes.clear();
		boolean b = false;
		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				@SuppressWarnings("unchecked")
				Query<Cliente> usuariosQuery = pm.newQuery("SELECT FROM " + Cliente.class.getName());

				List<Cliente> listaUsuarios = usuariosQuery.executeList();
				for (Cliente u : listaUsuarios) {
					clientes.add(u);
				}

				tx.commit();
			} catch (Exception ex) {

				messagePrinter.println("* Exception executing a query: " + ex.getMessage(), TipoMensaje.ERROR);

			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}

				pm.close();
			}
			messagePrinter.println("login(String " + usuario + ", String " + contrasenya + ")");

			for (Cliente cl : clientes) {
				if (cl.isHabilitado()) {
					if (cl.getNick().equals(usuario)) {
						b = contrasenya.contentEquals(cl.getPass());
					}
				}
			}
			messagePrinter.println("\t" + b);
		} catch (Exception ex) {
			messagePrinter.println("* Exception: " + ex.getMessage(), TipoMensaje.ERROR);
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
				messagePrinter.println("Comprobando que el usuario no existía previamente '" + nick + "'");
				Cliente user = null;
				Perfil perfil = null;
				Modo m;
				ControlParental cp = ControlParental.FALSE;

				try {
					user = pm.getObjectById(Cliente.class, nick);
				} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
					messagePrinter.println("Exception launched: " + jonfe.getMessage());
				}
				messagePrinter.println("User: " + user);
				if (user != null) {
					messagePrinter.println("Setting password user: " + user);
					user.setPass(pass);
					messagePrinter.println("Password set user: " + user);
				} else {
					if (tipo == 0) {
						m = Modo.USER;
						user = new Cliente(usuario, pass, nick, m);
					} else {
						m = Modo.ADMIN;
						user = new Cliente(usuario, pass, nick, m);
					}
					perfil = new Perfil(nick + "PerfilPrincipal", fecha, cp);
					user.perfiles.add(perfil);
					clientes.add(user);
					messagePrinter.println("Creating user: " + user);
					pm.makePersistent(user);
					messagePrinter.println("User created: " + user);
				}
				messagePrinter.println("Creating profile: " + perfil);
				pm.makePersistent(perfil);
				tx.commit();

			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		} catch (Exception ex) {
			messagePrinter.println("* Exception: " + ex.getMessage(), TipoMensaje.ERROR);
		}

	}

	@Override
	public Perfil[] getPerfiles(String usuario) throws RemoteException {
		ArrayList<Perfil> perfiles = new ArrayList<Perfil>();
		// String[] usuarios = null;
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

				messagePrinter.println("* Exception executing a query: " + ex.getMessage(), TipoMensaje.ERROR);

			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}

				pm.close();
			}

			// ArrayList<String> nicks = new ArrayList<>();
			//
			// for (Perfil u : p) {
			// nicks.add(u.getNombreP());
			// }
			//
			// usuarios = new String[nicks.size()];
			//
			// for (int i = 0; i < nicks.size(); i++) {
			// usuarios[i] = nicks.get(i);
			// }

		} catch (Exception ex) {
			messagePrinter.println("* Exception: " + ex.getMessage(), TipoMensaje.ERROR);
		}
		return perfiles.toArray(new Perfil[perfiles.size()]);
	}

	@Override
	public void crearPerfil(String usuario, Perfil p) {
		try {
			tx.begin();
			
			Query q = pm.newQuery("SELECT FROM " + Cliente.class.getName());
			@SuppressWarnings("unchecked")
			List<Cliente> clientes = (List<Cliente>) q.execute();
			Cliente user = null;
			for (Cliente c : clientes) {
				System.out.println("crear perfil bucle");
				if (c.getNick().equals(usuario)) {
					user = c;
					break;
				}
			}
			user.toString();
			// user.perfiles.add(p);
			List<Perfil> nuevo = user.perfiles;
			// for(Perfil x: nuevo) {
			// messagePrinter.println(x.getNombreP());
			// }

			messagePrinter.println("Creating profile: " + p);
			pm.makePersistent(p);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

	}

	public void cambiarControlParental(Perfil p) throws RemoteException {
		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				if (p.getControlParental() == ControlParental.TRUE) {
					p.setControlParental(ControlParental.FALSE);
				} else {
					p.setControlParental(ControlParental.TRUE);
				}
				pm.makePersistent(p);
				messagePrinter.println("Control Parental actualizado");
				tx.commit();
			} catch (Exception ex) {

				// messagePrinter.println("* Exception executing a query: " + ex.getMessage(), TipoMensaje.ERROR);

			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}

				pm.close();
			}
		} catch (Exception ex) {
			messagePrinter.println("* Exception: " + ex.getMessage(), TipoMensaje.ERROR);
		}

	}

	@Override
	public boolean getTipo(String usuario) throws RemoteException {
		// false = usuario
		// true = admin
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

				messagePrinter.println("* Exception executing a query: " + ex.getMessage(), TipoMensaje.ERROR);

			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}

				pm.close();
			}
		} catch (Exception ex) {
			messagePrinter.println("* Exception: " + ex.getMessage(), TipoMensaje.ERROR);
		}
		return tipo;
	}
	// public static void main(String[] args) {
	// PersistenceManagerFactory pmf =
	// JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	// PersistenceManager pm = pmf.getPersistenceManager();
	// Transaction tx=pm.currentTransaction();
	// try
	// {
	// tx.begin();
	// Pelicula p = new Pelicula("p", 1, 1, "Drama", 1, "", 1);
	// Serie s = new Serie("s", 1, "Drama", 1, "");
	// pm.makePersistent(p);
	// pm.makePersistent(s);
	// tx.commit();
	// }
	// finally
	// {
	// if (tx.isActive())
	// {
	// tx.rollback();
	// }
	// pm.close();
	// }
	// try {
	// Rmi rmi = new Rmi("");
	// Contenido[] resultado = rmi.buscarPelicula("Drama", "t");
	// System.out.println(java.util.Arrays.toString(resultado));
	// } catch (RemoteException e) {
	// e.printStackTrace();
	// }
	// }

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
			try {
				tx.begin();

				@SuppressWarnings("rawtypes")
				Query q = pm.newQuery("SELECT FROM " + nombreTabla);
				@SuppressWarnings("unchecked")
				List<Pelicula> contenidos = (List<Pelicula>) q.execute();
				Iterator<Pelicula> iter = contenidos.iterator();
				while (iter.hasNext()) {
					Pelicula p = iter.next();
					if (p.getTitulo().contains(campoDeBusqueda) && p.getGenero().equalsIgnoreCase(genero)) {
						if (modo.equalsIgnoreCase("Sinopsis vacía")) {
							if (p.getSinopsis().equals("")) {
								arrayList.add(p);
							}
						} else if (modo.equalsIgnoreCase("Título vacío")) {
							if (p.getTitulo().equals("")) {
								arrayList.add(p);
							}
						} else {
							arrayList.add(p);
						}

					}

				}

				tx.commit();
				// Si no se pone este for, no se inicializan las variables
				for (Contenido c : arrayList) {
					c.toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}

				pm.close();
			}
		} catch (Exception e) {
			messagePrinter.println("* Exception: " + e.getMessage(), TipoMensaje.ERROR);
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
			try {
				tx.begin();

				@SuppressWarnings("rawtypes")
				Query q = pm.newQuery("SELECT FROM " + nombreTabla);
				@SuppressWarnings("unchecked")
				List<Serie> contenidos = (List<Serie>) q.execute();
				Iterator<Serie> iter = contenidos.iterator();
				while (iter.hasNext()) {
					Serie s = iter.next();
					if (s.getTitulo().contains(campoDeBusqueda) && s.getGenero().equalsIgnoreCase(genero)) {
						if (modo.equalsIgnoreCase("Sinopsis vacía")) {
							if (s.getSinopsis().equals("")) {
								arrayList.add(s);
							}
						} else if (modo.equalsIgnoreCase("Título vacío")) {
							if (s.getTitulo().equals("")) {
								arrayList.add(s);
							}
						} else {
							arrayList.add(s);
						}
					}

				}

				tx.commit();
				// Si no se pone este for, no se inicializan las variables
				for (Contenido c : arrayList) {
					c.toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}

				pm.close();
			}
		} catch (Exception e) {
			messagePrinter.println("* Exception: " + e.getMessage(), TipoMensaje.ERROR);
		}
		return arrayList.toArray(new Serie[arrayList.size()]);
	}

	public void valorarPelicula(double val, Pelicula p) {
		ArrayList<Pelicula> arrayList = new ArrayList<Pelicula>();
		String nombreTabla;
		nombreTabla = Pelicula.class.getName();
		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				@SuppressWarnings("rawtypes")
				Query query = pm.newQuery("SELECT FROM " + nombreTabla);
				tx.begin();
				@SuppressWarnings("unchecked")
				List<Pelicula> contenidos = (List<Pelicula>) query.execute();
				Iterator<Pelicula> iter = contenidos.iterator();
				while (iter.hasNext()) {
					Pelicula peli = iter.next();

					peli.setValoracion(val);
					peli.setNumvotos(peli.getNumvotos()+1);
					pm.makePersistent(peli);
					tx.commit();
				}

			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		} catch (Exception ex) {
			messagePrinter.println("* Exception: " + ex.getMessage(), TipoMensaje.ERROR);
		}

	}

	@Override
	public void valorarSerie(double val, Serie s) throws RemoteException {
		ArrayList<Serie> arrayList = new ArrayList<Serie>();
		String nombreTabla;
		nombreTabla = Serie.class.getName();
		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				@SuppressWarnings("rawtypes")
				Query query = pm.newQuery("SELECT FROM " + nombreTabla);
				tx.begin();
				@SuppressWarnings("unchecked")
				List<Serie> contenidos = (List<Serie>) query.execute();
				Iterator<Serie> iter = contenidos.iterator();
				while (iter.hasNext()) {
					Serie serie = iter.next();
					serie.setNumVotos(serie.getNumVotos()+1);
					serie.setVal(val);
					pm.makePersistent(serie);
					tx.commit();
				}

			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		} catch (Exception ex) {
			messagePrinter.println("* Exception: " + ex.getMessage(), TipoMensaje.ERROR);
		}

	}

	@Override
	public Cliente[] buscarUsuarios(String nombre) throws RemoteException {
		ArrayList<Cliente> arrayList = new ArrayList<Cliente>();

		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();

				@SuppressWarnings("rawtypes")
				Query q = pm.newQuery("SELECT FROM " + Cliente.class.getName());
				@SuppressWarnings("unchecked")
				List<Cliente> contenidos = (List<Cliente>) q.execute();
				Iterator<Cliente> iter = contenidos.iterator();
				while (iter.hasNext()) {
					Cliente s = iter.next();
					if (s.getNick().contains(nombre)) {
						arrayList.add(s);
					}
				}

				tx.commit();
				// Si no se pone este for, no se inicializan las variables
				for (Cliente c : arrayList) {
					c.toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}

				pm.close();
			}
		} catch (Exception e) {
			messagePrinter.println("* Exception: " + e.getMessage(), TipoMensaje.ERROR);
		}
		return arrayList.toArray(new Cliente[arrayList.size()]);
	}

	@Override
	public void editarUsuarios(String n, boolean habilitado) throws RemoteException {
		Cliente user = null;
		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx = pm.currentTransaction();
				tx.begin();
				Query q = pm.newQuery("SELECT FROM " + Cliente.class.getName());
				List<Cliente> contenidos = (List<Cliente>) q.execute();
				Iterator<Cliente> iter = contenidos.iterator();
				while (iter.hasNext()) {
					Cliente s = iter.next();
					if (s.getNick().equals(n)) {
						user = s;
						user.setHabilitado(habilitado);
						pm.makePersistent(user);
						break;
					}
				}

				tx.commit();

			} catch (Exception e) {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		} catch (Exception e) {
			messagePrinter.println("* Exception: " + e.getMessage(), TipoMensaje.ERROR);
		}
	}

	
	@Override
	public Pelicula[] añadirPelicula(String campoDeBusqueda, String anyo, String duracion,
			String genero, String edadRecom, String sinopsis) throws RemoteException {
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
//			    @SuppressWarnings("unchecked")
			    Pelicula peli = null;
			    try
			    {
			    	//peli = pm.getObjectById(Pelicula.class, campoDeBusqueda);
			    }
			    catch (javax.jdo.JDOObjectNotFoundException jdoObjectNotFoundException)
			    {
			     	messagePrinter.println("Exception launched: " + jdoObjectNotFoundException.getMessage());
			    }
			    
				@SuppressWarnings("unchecked")
				List<Pelicula> contenidos = (List<Pelicula>)q.execute();
			    Iterator<Pelicula> iter = contenidos.iterator();
			    boolean make = true;
			    while (iter.hasNext())
			    {
			    	Pelicula p = iter.next();
			    	if(campoDeBusqueda == p.getTitulo()){
			    		make=false;
			    	}
			    	
			    }
			    if(make){
			    	try
			    	{
			    		peli.setAnho(Integer.parseInt(anyo));
			    		peli.setDuracion(Long.valueOf(duracion));
			    		peli.setEdad_rec(Integer.parseInt(edadRecom));
			    		peli.setGenero(genero);
				    	peli.setSinopsis(sinopsis);
					    contenidos.add(peli);
			    	}
			    	catch(Exception e) //NumberFormatException e)
			    	{
			    		System.out.println("NumberFormatException: " + e.getMessage());
			    	}
			    	
			    }else{
			    	
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
	public Pelicula[] editarPelicula(String campoDeBusqueda, String anyo, String duracion,
			String genero, String edadRecom, String sinopsis) throws RemoteException {
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
//			    @SuppressWarnings("unchecked")
			    Pelicula peli = null;
			    try
			    {
			    	peli = pm.getObjectById(Pelicula.class, campoDeBusqueda);
			    }
			    catch (javax.jdo.JDOObjectNotFoundException jdoObjectNotFoundException)
			    {
			    	messagePrinter.println("Exception launched: " + jdoObjectNotFoundException.getMessage());
			    }
			    
				@SuppressWarnings("unchecked")
				List<Pelicula> contenidos = (List<Pelicula>)q.execute();
			    Iterator<Pelicula> iter = contenidos.iterator();
			   while (iter.hasNext())
			    {
			    	Pelicula p = iter.next();
			    	if(campoDeBusqueda == p.getTitulo()){
			    		if(anyo != null){
					    	try
					    	{
					    		peli.setAnho(Integer.parseInt(anyo));
					    	}
					    	catch(NumberFormatException e)
					    	{
					    		System.out.println("NumberFormatException: " + e.getMessage());
					    	}
				    	}
				    	if(duracion != null){
					    	try
					    	{
					    		peli.setDuracion(Long.valueOf(duracion));
					    	}
					    	catch(NumberFormatException e)
					    	{
					    		System.out.println("NumberFormatException: " + e.getMessage());
					    	}
				    	}
				    	if(genero != null){
				    		peli.setGenero(genero);
				    	}
				    	if(edadRecom != null){
					    	try
					    	{
					    		peli.setEdad_rec(Integer.parseInt(edadRecom));
					    	}
					    	catch(NumberFormatException e)
					    	{
					    		System.out.println("NumberFormatException: " + e.getMessage());
					    	}
				    	}
				    	if(sinopsis != null){
				    		peli.setSinopsis(sinopsis);
				    	}
					    contenidos.add(peli);
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
	public Pelicula[] eliminarPelicula(String campoDeBusqueda) throws RemoteException {
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
//			    @SuppressWarnings("unchecked")
			    Pelicula peli = null;
			    try
			    {
			    	peli = pm.getObjectById(Pelicula.class, campoDeBusqueda);
			    }
			    catch (javax.jdo.JDOObjectNotFoundException jdoObjectNotFoundException)
			    {
			    	messagePrinter.println("Exception launched: " + jdoObjectNotFoundException.getMessage());
			    }
			    
				@SuppressWarnings("unchecked")
				List<Pelicula> contenidos = (List<Pelicula>)q.execute();
			    Iterator<Pelicula> iter = contenidos.iterator();
			    boolean make = false;
			    while (iter.hasNext())
			    {
			    	Pelicula p = iter.next();
			    	if(campoDeBusqueda == p.getTitulo()){
			    		iter.remove();
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
	
}
