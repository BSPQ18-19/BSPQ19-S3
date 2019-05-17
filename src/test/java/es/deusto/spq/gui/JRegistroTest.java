package es.deusto.spq.gui;

import org.junit.*;

import es.deusto.spq.gui.JRegistro;

import static org.junit.Assert.*;

import com.github.javatlacati.contiperf.*;
import com.github.javatlacati.contiperf.junit.ContiPerfRule;

public class JRegistroTest {
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	JRegistro j;

	@Before
	public void setUp() {
		j = new JRegistro(null, null);
	}

	@Test
	@PerfTest(invocations = 10)
	@Required(max = 75, average = 50, throughput = 4)
	public void testconfirmarContraseña() {
		char[] a = { 'a', 'b', 'c' };
		char[] b = { 'a', 'b', 'c' };
		assertTrue(j.confirmarContaseña(a, b));
	}
	
	@Test
	@PerfTest(invocations = 10)
	@Required(max = 50, average = 25, throughput = 4)
	public void testconfirmarContraseñano() {
		char[] a = { 'a', 'b', 'c' };
		char[] b = { 'a', 'd', 'c', 'e' };
		assertFalse(j.confirmarContaseña(a, b));
	}

}
