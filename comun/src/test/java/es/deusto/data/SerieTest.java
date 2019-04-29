package es.deusto.data;

import static org.junit.Assert.*;

import org.junit.*;

public class SerieTest {

	Serie s;

	@Before
	public void setUp() {
		s = new Serie("Red", 2002, "Drama", 8, "Hola");
	}

	// getters
	@Test
	public void testgetTitulo() {
		assertEquals("Red",s.getTitulo() );
	}
	@Test
	public void testgetAnho() {
		assertEquals(2002,s.getAnho() );
	}
	@Test
	public void testgetGenero() {
		assertEquals("Drama",s.getGenero() );
	}
//	@Test
//	public void testgetVal() {
//		assertEquals(8,s.getVal() );
//	}
	@Test
	public void testgetSinopsis() {
		assertEquals("Hola",s.getSinopsis() );
	}
	
	
	//setters
	@Test
	public void testsetTitulo() {
		s.setTitulo("Adios");
		assertEquals("Adios",s.getTitulo() );
	}
	@Test
	public void testsetAnho() {
		s.setAnho(2004);
		assertEquals(2004,s.getAnho() );
	}
	@Test
	public void testsetGenero() {
		s.setGenero("Terror");
		assertEquals("Terror",s.getGenero() );
	}
//	@Test
//	public void testsetVal() {
//		s.setVal(4);
//		assertEquals(4,s.getVal() );
//	}
	@Test
	public void testsetSinopsis() {
		s.setSinopsis("Si");
		assertEquals("Si",s.getSinopsis() );
	}
	
}
