package es.deusto.spq.gui;

import org.junit.*;

import es.deusto.spq.gui.JRegistro;

import static org.junit.Assert.*;

import com.github.javatlacati.contiperf.*;
import com.github.javatlacati.contiperf.junit.ContiPerfRule;

public class JRegistroTest {
	@Rule public ContiPerfRule rule = new ContiPerfRule();

	@Test
	@PerfTest(invocations = 10)
	@Required(max = 100, average = 50, throughput = 4)
	public void testconfirmarContraseña() {
		char[] a = { 'a', 'b', 'c' };
		char[] b = { 'a', 'b', 'c' };
		assertTrue(JRegistro.confirmarContaseña(a, b));
	}
	
	@Test
	@PerfTest(invocations = 10)
	@Required(max = 250, average = 50, throughput = 4)
	public void testconfirmarContraseñano() {
		char[] a = { 'a', 'b', 'c' };
		char[] b = { 'a', 'd', 'c', 'e' };
		assertFalse(JRegistro.confirmarContaseña(a, b));
	}

}
