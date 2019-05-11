package es.deusto.spq.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import com.stackoverflow.WrappingFlowLayout;

import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class JBarraBusqueda extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6629908002287760900L;
	private JTextField textField;

	private static final String[] GENEROS = new String[] {"Drama", "Ciencia Ficción", "Terror", "Acción", "Comedia"};
	
	private WrappingFlowLayout wrappingFlowLayout;
	
	/**
	 * Create the panel.
	 */
	public JBarraBusqueda() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscar.gridx = 2;
		gbc_btnBuscar.gridy = 0;
		add(btnBuscar, gbc_btnBuscar);
		
		panelGeneros = new JPanel();
		wrappingFlowLayout = new WrappingFlowLayout();
		panelGeneros.setLayout(wrappingFlowLayout);
		GridBagConstraints gbc_panelGeneros = new GridBagConstraints();
		gbc_panelGeneros.insets = new Insets(0, 0, 5, 0);
		gbc_panelGeneros.gridwidth = 3;
		gbc_panelGeneros.fill = GridBagConstraints.VERTICAL;
		gbc_panelGeneros.gridx = 0;
		gbc_panelGeneros.gridy = 1;
		add(panelGeneros, gbc_panelGeneros);
		
		rdbtnPeliculas = new JRadioButton("Películas");
		rdbtnPeliculas.setSelected(true);
		buttonGroup.add(rdbtnPeliculas);
		GridBagConstraints gbc_rdbtnPeliculas = new GridBagConstraints();
		gbc_rdbtnPeliculas.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnPeliculas.gridx = 0;
		gbc_rdbtnPeliculas.gridy = 2;
		add(rdbtnPeliculas, gbc_rdbtnPeliculas);
		
		rdbtnSeries = new JRadioButton("Series");
		buttonGroup.add(rdbtnSeries);
		GridBagConstraints gbc_rdbtnSeries = new GridBagConstraints();
		gbc_rdbtnSeries.anchor = GridBagConstraints.WEST;
		gbc_rdbtnSeries.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnSeries.gridx = 1;
		gbc_rdbtnSeries.gridy = 2;
		add(rdbtnSeries, gbc_rdbtnSeries);
		
		for(int i = 0; i < GENEROS.length; i++) {
//		for(String genero: GENEROS) {
			String genero = GENEROS[i];
			JCheckBox button = new JCheckBox(genero);
			panelGeneros.add(button);
			buttonGroupGeneros.add(button);
			if(i == 0) {
				button.setSelected(true);
			}
		}
		
		btnBuscar.addActionListener((e)->processBusquedaEvent());

	}
	
	
	private ArrayList<BusquedaListener> busquedaListeners = new ArrayList<BusquedaListener>();
	private JPanel panelGeneros;
	private JRadioButton rdbtnPeliculas;
	private JRadioButton rdbtnSeries;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroupGeneros = new ButtonGroup();
	
	public void addBusquedaListener(BusquedaListener busquedaListener){
		busquedaListeners.add(busquedaListener);
	}
	
	public boolean removeBusquedaListener(BusquedaListener busquedaListener) {
		return busquedaListeners.remove(busquedaListener);
	}
	
	protected void processBusquedaEvent() {
		String campoDeBusqueda = textField.getText();
		
		String genero = null;
		
		//Tiene break
		for(Component c: panelGeneros.getComponents()) {
			JCheckBox checkBox = (JCheckBox) c;
			if(checkBox.isSelected()) {
				genero = checkBox.getText();
				break;
			}
		}
		
		for(BusquedaListener busquedaListener:busquedaListeners) {
			busquedaListener.onBuscar(genero, campoDeBusqueda, rdbtnPeliculas.isSelected());
		}
	}
	

	public boolean isPeliculasSelected() {
		return rdbtnPeliculas.isSelected();
	}
	public boolean isSeriesSelected() {
		return rdbtnSeries.isSelected();
	}
}
