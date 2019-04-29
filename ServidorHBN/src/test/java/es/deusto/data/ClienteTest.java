package es.deusto.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.deusto.data.Cliente.Modo;
import es.deusto.data.Perfil.ControlParental;

public class ClienteTest {
	
	Cliente c;
	Perfil p;
	List listA;
	@Before
	public void setUp() {
		p=new Perfil("A", "1-2-3", ControlParental.FALSE);
		c = new Cliente("Jose", "123", "Jose123", Modo.USER);
		c.perfiles.add(p);
		listA = new ArrayList();
		listA.add(p);
	}
	
	//getters
	@Test
	public void testgetNombre() {
		assertEquals("Jose", c.getNombre());	
	}
	@Test
	public void testgetPass() {
		assertEquals("123", c.getPass());
	}
	@Test
	public void testgetNick() {
		
		p=new Perfil("A", "1-2-3", ControlParental.FALSE);
	//	assertEquals(perfiles, c.getPerfiles());
	}
	@Test
	public void testgetModo() {
		assertEquals(Modo.USER, c.getTipo());
	}
	@Test
	public void testgetPerfiles() {
		assertEquals(listA, c.perfiles);
	}
	
	//setters
	@Test
	public void testsetNombre() {
		c.setNombre("Paco");
		assertEquals("Paco", c.getNombre());	
	}
	@Test
	public void testsetPass() {
		c.setPass("234");
		assertEquals("234", c.getPass());
	}
	@Test
	public void testsetNick() {
		c.setNick("Josex");
		assertEquals("Josex", c.getNick());
	}
	@Test
	public void testsetModo() {
		c.setTipo(Modo.ADMIN);
		assertEquals(Modo.ADMIN, c.getTipo());
	}
}
