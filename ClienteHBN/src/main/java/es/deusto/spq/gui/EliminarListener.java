package es.deusto.spq.gui;

public interface EliminarListener {
	/**
	 * @param campoDeBusquedaID
	 * @param campoDeBusquedaTitulo
	 * @param isPelicula
	 */
	public void onBuscar(String campoDeBusquedaID, String campoDeBusquedaTitulo, boolean isPelicula);
}
