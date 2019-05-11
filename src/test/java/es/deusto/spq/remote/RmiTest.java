package es.deusto.spq.remote;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.junit.Before;
import org.junit.Test;

import es.deusto.data.Pelicula;
import es.deusto.data.Serie;


public class RmiTest {

	Rmi rmi;
	@Before
	public void setUp() {
		try {
			rmi = new Rmi("name");
			
//			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
//			PersistenceManager pm = pmf.getPersistenceManager();
//			Transaction tx=pm.currentTransaction();
//			try
//			{
//			    tx.begin();
//			    Pelicula p = new Pelicula("p", 1, 1, "Drama", 1, "", 1);
//			    Serie serie = new Serie("s", 1, "Drama", 1, "");
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
		} catch (RemoteException e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testbuscarPelicula() {
		try {
			Pelicula[] peliculas = rmi.buscarPelicula("drama", "");
			assertTrue(peliculas.length>0);
		} catch (RemoteException e) {
			fail(e.toString());
		}
		
	}
	
	@Test
	public void testbuscarSerie() {
		try {
			Serie[] series = rmi.buscarSerie("drama", "");
			assertTrue(series.length>0);
		} catch (RemoteException e) {
			fail(e.toString());
		}
		
	}
}
