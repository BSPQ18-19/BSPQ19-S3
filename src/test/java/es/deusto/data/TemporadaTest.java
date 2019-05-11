package es.deusto.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TemporadaTest {

	
	Temporada t;
	@Before
	public void setUp(){
		t= new Temporada(7,3);
	}
	//getters
	@Test
	public void testgetNum() {
		assertEquals(7, t.getNum());
	}@Test
	public void testgetAnho() {
		assertEquals(3, t.getAnho());
	}

	//setters
	@Test
	public void testsetNum() {
		t.setNum(4);
		assertEquals(4, t.getNum());
	}@Test
	public void testsetAnho() {
		t.setAnho(6);
		assertEquals(6, t.getAnho());
	}
}
