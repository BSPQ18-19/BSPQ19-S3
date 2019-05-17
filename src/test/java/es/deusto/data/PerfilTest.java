package es.deusto.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.deusto.data.Perfil.ControlParental;

public class PerfilTest {

	Perfil p;

	@Before
	public void setUp() {
		p = new Perfil("n", "fecha", Perfil.ControlParental.TRUE);
	}
	
	@Test
	public void testNombreP() {
		p.setNombreP("n");
		assertEquals(p.getNombreP(), "n");
	}

	@Test
	public void testFecha() {
		p.setFecha("n");
		assertTrue(p.getFecha().contentEquals("n"));
	}

	@Test
	public void testControlParental() {
		p.setControlParental(Perfil.ControlParental.TRUE);
		assertTrue(ControlParental.TRUE.equals(p.getControlParental()));
	}

	@Test
	public void testtoString() {
		assertTrue(null != p.toString());
	}

}
