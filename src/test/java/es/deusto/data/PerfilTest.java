package es.deusto.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.javatlacati.contiperf.PerfTest;
import com.github.javatlacati.contiperf.Required;
import com.github.javatlacati.contiperf.junit.ContiPerfRule;

import es.deusto.data.Perfil.ControlParental;

@PerfTest(invocations = 10)
@Required(max = 50, average = 25, throughput = 4)
public class PerfilTest {
	@Rule public ContiPerfRule rule = new ContiPerfRule();
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
