package es.deusto.spq.gui;

public interface EditarListener {
	/**
	 * @param campoDeBusquedaID
	 * @param campoDeBusquedaTitulo
	 * @param isPelicula
	 */
	public void onBuscar(String campoDeBusquedaID, String campoDeBusquedaTitulo, boolean isPelicula);
}
