package es.deusto.spq.remote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.rmi.RemoteException;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import com.github.javatlacati.contiperf.PerfTest;
import com.github.javatlacati.contiperf.Required;
import com.github.javatlacati.contiperf.junit.ContiPerfRule;

import es.deusto.data.Capitulo;
import es.deusto.data.Cliente;
import es.deusto.data.Pelicula;
import es.deusto.data.Serie;
import es.deusto.data.Temporada;
import es.deusto.spq.IMessagePrinter;
import es.deusto.spq.TipoMensaje;

@PerfTest(invocations = 10)
@Required(max = 1000, average = 500, throughput = 2)
public class RmiTest {
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	private static Rmi rmi;
//	@Before
//	public void setUp() {
//		try {
//			rmi = new Rmi("name");
//			
//			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
//			PersistenceManager pm = pmf.getPersistenceManager();
//			Transaction tx=pm.currentTransaction();
//			try
//			{
//			    tx.begin();
//			    Pelicula p = new Pelicula("p", 1, 1, "Drama", 1, "", 1, 0);
//			    Serie serie = new Serie("s", 1, "Drama", 1, "");
//			    List<Temporada> temporadas= serie.getTemps();
//			    Temporada t = new Temporada(1, 2002);
//			    List<Capitulo> caps = t.getCaps();
//			    caps.add(new Capitulo("c", 1, 18, "desc", 10));
//			    temporadas.add(t);
//			    t.setCaps(caps);
//			    serie.setTemps(temporadas);
//			    pm.makePersistent(p);
//			    pm.makePersistent(serie);
//			    tx.commit();
//			}
//			finally
//			{
//			    if (tx.isActive())
//			    {
//			        tx.rollback();
//			    }
//			    pm.close();
//			}
//		} catch (RemoteException e) {
//			fail(e.toString());
//		}
//	}
	
	private static final String USER_TEST_ADMIN = "usertestadmin";
	private static final String USUARIO_ADMIN_DE_TEST = "Usuario admin de test";
	
	private static final String USER_TEST = "usertest";
	private static final String USUARIO_DE_TEST = "Usuario de test";
	private static final String FECHA = "1-1-1990";
	
	@BeforeClass
	public static void setUp() {
		try {
			IMessagePrinter messagePrinter = new IMessagePrinter() {
				
				@Override
				public void println(String mensaje) {
					
				}
				
				@Override
				public void println(String s, TipoMensaje tipo) {
					
				}
			};
			rmi = new Rmi(messagePrinter);
			rmi.registrarse(USUARIO_DE_TEST, USER_TEST, USER_TEST, FECHA, Cliente.Modo.USER.ordinal());
			rmi.registrarse(USUARIO_ADMIN_DE_TEST, USER_TEST_ADMIN, USER_TEST_ADMIN, FECHA, Cliente.Modo.ADMIN.ordinal());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	}
	
	@Ignore
	@Test
	public void testbuscarPelicula() {
		try {
			Pelicula[] peliculas = rmi.buscarPelicula("drama", "");
			assertTrue(peliculas.length>0);
		} catch (RemoteException e) {
			fail(e.toString());
		}
		
	}
	
	@Ignore
	@Test
	public void testbuscarSerie() {
		try {
			Serie[] series = rmi.buscarSerie("drama", "");
			assertTrue(series.length>0);
		} catch (RemoteException e) {
			fail(e.toString());
		}
		
	}
	
	@Test
	public void testObtenerClienteAdmin() {
		try {
			boolean encontrado = false;
			Cliente[] usuarios = rmi.buscarUsuarios(USER_TEST_ADMIN);
			assertNotNull(usuarios);
			for(Cliente usuario:usuarios) {
				if(usuario.getNick().equals(USER_TEST_ADMIN)) {
					encontrado = true;
					assertEquals(usuario.getNombre(), USUARIO_ADMIN_DE_TEST);
					assertEquals(usuario.getPass(), USER_TEST_ADMIN);
					assertEquals(usuario.getTipo(), Cliente.Modo.ADMIN);
					break;
				}
			}
			assertTrue(encontrado);
		} catch (RemoteException e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testObtenerCliente() {
		try {
			boolean encontrado = false;
			Cliente[] usuarios = rmi.buscarUsuarios(USER_TEST);
			assertNotNull(usuarios);
			for(Cliente usuario:usuarios) {
				if(usuario.getNick().equals(USER_TEST)) {
					encontrado = true;
					assertEquals(usuario.getNombre(), USUARIO_DE_TEST);
					assertEquals(usuario.getPass(), USER_TEST);
					assertEquals(usuario.getTipo(), Cliente.Modo.USER);
					break;
				}
			}
			assertTrue(encontrado);
		} catch (RemoteException e) {
			fail(e.toString());
		}
	}
	
	@Test
	@PerfTest(invocations = 100, threads = 10)
    @Required(max = 10000, average = 5000)
	public void testnoObtenerCliente() {
		try {
			boolean encontrado = false;
			Cliente[] usuarios = rmi.buscarUsuarios(USER_TEST);
			assertNotNull(usuarios);
			for(Cliente usuario:usuarios) {
				if(usuario.getNick().equals(USER_TEST)) {
					encontrado = true;
					assertEquals(usuario.getNombre(), USUARIO_DE_TEST);
					assertNotEquals(usuario.getPass(), "Contraseña incorrecta");
					assertEquals(usuario.getTipo(), Cliente.Modo.USER);
					break;
				}
			}
			assertTrue(encontrado);
		} catch (RemoteException e) {
			fail(e.toString());
		}
	}
	@Ignore
	@Test
	public void testEliminarPelicula(){
		try {
			Pelicula[] peliculas = null;
			peliculas[0].setTitulo("a");
			peliculas = rmi.eliminarPelicula("a");
			assertTrue(peliculas.length>0);
		} catch (RemoteException e) {
			fail(e.toString());
		}
		
	}
	@Ignore
	@Test
	public void testEditarPelicula(){
		try {
			Pelicula[] peliculas = null;
			peliculas[0].setTitulo("a");
			peliculas[0].setDuracion(2000);
			peliculas = rmi.editarPelicula("a", "2001","","","","");
			assertEquals(peliculas[0].getAnho(), 2001);
		} catch (RemoteException e) {
			fail(e.toString());
		}
		
	}
	@Ignore
	@Test
	public void testAñadirPelicula(){
		try {
			Pelicula[] peliculas = null;
			peliculas = rmi.añadirPelicula("pelicula", "2001","200","18","Drama","aaa");
			assertEquals(peliculas[0].getTitulo(), "pelicula");
			assertEquals(peliculas[0].getAnho(), 2001);
		} catch (RemoteException e) {
			fail(e.toString());
		}
		
	}
	
	public static void main(String[] args) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
		    tx.begin();
		    Pelicula p = new Pelicula("p", 1, 1, "Drama", 1, "", 1, 1);
		    Serie serie = new Serie("s", 1, "Drama", 1, 0, "", 1);
		    List<Temporada> temporadas= serie.getTemps();
		    Temporada t = new Temporada(1, 2002);
		    List<Capitulo> caps = t.getCaps();
		    caps.add(new Capitulo("Titulo del capitulo", 1, "Descripcion", 0));
		    temporadas.add(t);
		    t.setCaps(caps);
		    serie.setTemps(temporadas);
		    pm.makePersistent(p);
		    pm.makePersistent(serie);
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
	}
}
