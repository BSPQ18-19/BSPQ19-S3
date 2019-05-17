package es.deusto.spq.gui;

import javax.swing.JPanel;

import es.deusto.data.Pelicula;
import es.deusto.data.Serie;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class JContenido extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7389893104009003077L;
	
	private JLabel lblTitulo;
	private JLabel lblGenero;
	private Serie serie;
	private Pelicula peli;
	
	/**
	 * Create the panel.
	 */
	public JContenido(Serie serie) {
		this();
		setSerie(serie);
	}
	
	public JContenido(Pelicula peli) {
		this();
		setPelicula(peli);
	}
	
	public JContenido() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{110, 343, 0};
		gridBagLayout.rowHeights = new int[]{300, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel label = new JLabel();
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		add(label, gbc_label);
		
		JPanel panelSerie = new JPanel();
		GridBagConstraints gbc_panelSerie = new GridBagConstraints();
		gbc_panelSerie.fill = GridBagConstraints.BOTH;
		gbc_panelSerie.gridx = 1;
		gbc_panelSerie.gridy = 0;
		add(panelSerie, gbc_panelSerie);
		panelSerie.setLayout(new GridLayout(2, 1, 0, 0));
		
		lblTitulo = new JLabel();
		panelSerie.add(lblTitulo);
		
		lblGenero = new JLabel();
		panelSerie.add(lblGenero);
		
	}
	
	public Serie getSerie() {
		return serie;
	}
	
	public void setSerie(Serie serie) {
		this.serie = serie;
		lblTitulo.setText(serie.getTitulo() + " (" + serie.getAnho() +") " + "// Valoración: " + serie.getVal());
		lblGenero.setText(serie.getGenero());
	}
	
	public void setPelicula(Pelicula peli) {
		this.peli = peli; 
		lblTitulo.setText(peli.getTitulo() + " (" + peli.getAnho() +") " + "// Valoración: " + peli.getValoracion());
		lblGenero.setText(peli.getGenero());
	}

	@Override
	public String toString() {
		return "JContenido [lblTitulo=" + lblTitulo + ", lblGenero=" + lblGenero + ", serie=" + serie + ", peli=" + peli
				+ "]";
	}
}
