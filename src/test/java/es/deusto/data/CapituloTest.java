package es.deusto.data;

import static org.junit.Assert.*;

import com.github.javatlacati.contiperf.*;
import com.github.javatlacati.contiperf.junit.ContiPerfRule;

import org.junit.*;

@PerfTest(invocations = 10)
@Required(max = 250, average = 125)
public class CapituloTest {
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	Capitulo c;

	@Before
	public void setUp() {
		c = new Capitulo("Narnia", 80, "Un armario en Narnia", 3.1);
	}

	@Test
	public void testgetTitulo() {

		assertEquals("Narnia", c.getTitulo());
	
	}
	@Test
	public void testgetDuracion() {

		assertEquals(80, c.getDuracion());

	}
	@Test
	public void testgetDesc() {

		assertEquals("Un armario en Narnia", c.getDescr());

	}
//	@Test
//	public void testgetValoracion() {
//
//		assertEquals( c.getValoracion(),3.1);
//
//	}
	@Test
	public void testsetTitulo() {
		c.setTitulo("Hola");
		assertEquals(c.getTitulo(), "Hola");
		
	}
	@Test
	public void testsetDuracion() {
		c.setDuracion(40);
		assertEquals(c.getDuracion(), 40);
		
	}
	@Test
	public void testsetDescr() {
		c.setDescr("Hola");
		assertEquals(c.getDescr(), "Hola");
		
	}
//	@Test
//	public void testsetValoracion() {
//		c.setValoracion(4);
//		assertEquals(c.getValoracion(), 4);
//		
//	}
}
