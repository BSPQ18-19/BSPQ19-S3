package es.deusto.spq.gui;

import org.junit.*;

import es.deusto.spq.gui.JRegistro;

import static org.junit.Assert.*;

public class JRegistroTest {

	JRegistro j;

	@Before
	public void setUp() {
		j = new JRegistro(null, null);
	}

	@Test
	public void testconfirmarContraseña() {
		char[] a = { 'a', 'b', 'c' };
		char[] b = { 'a', 'b', 'c' };
		assertTrue(j.confirmarContaseña(a, b));
	}

	public void testconfirmarContraseñano() {
		char[] a = { 'a', 'b', 'c' };
		char[] b = { 'a', 'd', 'c', 'e' };
		assertFalse(j.confirmarContaseña(a, b));
	}

}
