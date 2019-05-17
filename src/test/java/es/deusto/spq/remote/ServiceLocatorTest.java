package es.deusto.spq.remote;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import es.deusto.spq.remote.IRmi;
import es.deusto.spq.remote.ServiceLocator;

public class ServiceLocatorTest {

	@Mock
	ServiceLocator serviceLocator;
	
	@Mock
	IRmi rmi;
	
	@Before
	public void setUp() {
		serviceLocator = mock(ServiceLocator.class);
		rmi = mock(IRmi.class);
	}
	
	@Test
	public void testGetService() {
		when(serviceLocator.getService()).thenReturn(rmi);
		IRmi rmi = serviceLocator.getService();
		when(this.rmi.toString()).thenReturn("To String de prueba");
		assertTrue(rmi.toString() != null);
	}
	
	@Test
	public void testSetService() {
		when(serviceLocator.setService()).thenReturn(true);
		assertTrue(serviceLocator.setService());
	}

}
