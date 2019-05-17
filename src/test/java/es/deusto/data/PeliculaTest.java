package es.deusto.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PeliculaTest {

	Pelicula p;
	@Before
	public void setUp(){
		p= new Pelicula("Peli", 1998, 80, "Drama", 3, "Hola", 5,1);
	}
	//getters
	@Test
	public void testgetTitulo() {
		assertEquals("Peli", p.getTitulo());
	}
	@Test
	public void testgetAnho() {
		assertEquals(1998, p.getAnho());
	}
	@Test
	public void testgetDuracion() {
		assertEquals(80, p.getDuracion());
	}
	@Test
	public void testgetGenero() {
		assertEquals("Drama", p.getGenero());
	}
	@Test
	public void testgetEdadRec() {
		assertEquals(3, p.getEdad_rec());
	}
	@Test
	public void testgetSinopsis() {
		assertEquals("Hola", p.getSinopsis());
	}
//	@Test
//	public void testgetValoracion() {
//		assertEquals(5,p.getValoracion());
//	}
	
	//setters
	@Test
	public void testsetTitulo() {
		p.setTitulo("A");
		assertEquals("A", p.getTitulo());
	}
	@Test
	public void testsetAnho() {
		p.setAnho(2000);
		assertEquals(2000, p.getAnho());
	}
	@Test
	public void testsetDuracion() {
		p.setDuracion(90);
		assertEquals(90, p.getDuracion());
	}
	@Test
	public void testsetGenero() {
		p.setGenero("Terror");
		assertEquals("Terror", p.getGenero());
	}
	@Test
	public void testsetEdadRec() {
		p.setEdad_rec(7);
		assertEquals(7, p.getEdad_rec());
	}
	@Test
	public void testsetSinopsis() {
		p.setSinopsis("B");
		assertEquals("B", p.getSinopsis());
	}

	@Test
	public void testtoString() {
		assertTrue(p.toString()!=null);
	}
	
	@Test
	public void testgetPortada() {
		p.setPortada(null);
		assertTrue(p.getPortada()==null);
	}
	
	@Test
	public void testValoracion() {
		double d = 1;
		p.setValoracion(d);
		assertTrue(d==p.getValoracion());
	}
	
	@Test
	public void testId() {
		p.setIdPel(1);
		assertTrue(p.getIdPel()==1);
	}
	
}
