package es.deusto.spq.gui;

import es.deusto.data.Cliente;

public interface GestionarUsuarioListener {
	void onSelected(Cliente cliente);
	
	void onBuscar(String texto);

}
