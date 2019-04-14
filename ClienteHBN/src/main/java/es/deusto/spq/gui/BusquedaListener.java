package es.deusto.spq.gui;

import java.util.ArrayList;

public interface BusquedaListener {
	void onBuscar(boolean serieSelected, boolean peliculaSelected, ArrayList<String> generos, String campoDeBusqueda);
}
