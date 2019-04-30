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

public class JBarraBusquedaAdmin extends JPanel {

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
	public JBarraBusquedaAdmin() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
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
		
		panelAdmin = new JPanel();
		GridBagConstraints gbc_panelAdmin = new GridBagConstraints();
		gbc_panelAdmin.gridwidth = 3;
		gbc_panelAdmin.insets = new Insets(0, 0, 0, 5);
		gbc_panelAdmin.fill = GridBagConstraints.BOTH;
		gbc_panelAdmin.gridx = 0;
		gbc_panelAdmin.gridy = 3;
		add(panelAdmin, gbc_panelAdmin);
		
		rdbtnPorDefecto = new JRadioButton("Por defecto");
		rdbtnPorDefecto.setSelected(true);
		buttonGroupAdmin.add(rdbtnPorDefecto);
		panelAdmin.add(rdbtnPorDefecto);
		
		rdbtnTtuloVaco = new JRadioButton("Título vacío");
		buttonGroupAdmin.add(rdbtnTtuloVaco);
		panelAdmin.add(rdbtnTtuloVaco);
		
		rdbtnDescripcinVaca = new JRadioButton("Sinopsis vacía");
		buttonGroupAdmin.add(rdbtnDescripcinVaca);
		panelAdmin.add(rdbtnDescripcinVaca);
		
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
	
	
	private ArrayList<BusquedaAdminListener> busquedaListeners = new ArrayList<BusquedaAdminListener>();
	private JPanel panelGeneros;
	private JRadioButton rdbtnPeliculas;
	private JRadioButton rdbtnSeries;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroupGeneros = new ButtonGroup();
	private JPanel panelAdmin;
	private JRadioButton rdbtnTtuloVaco;
	private JRadioButton rdbtnDescripcinVaca;
	private JRadioButton rdbtnPorDefecto;
	private final ButtonGroup buttonGroupAdmin = new ButtonGroup();
	
	public void addBusquedaListener(BusquedaAdminListener busquedaListener){
		busquedaListeners.add(busquedaListener);
	}
	
	public boolean removeBusquedaListener(BusquedaAdminListener busquedaListener) {
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
		
		boolean seguir = true;
		String modo = null;
		for(int i = 0; i < panelAdmin.getComponentCount() && seguir == true; i++) {
			if(panelAdmin.getComponent(i).getClass().equals(JCheckBox.class)) {
				JCheckBox checkBox = (JCheckBox) panelAdmin.getComponent(i);
				if(checkBox.isSelected()) {
					modo = checkBox.getText();
					seguir = false;
				}
			}
		}
		
		for(BusquedaAdminListener busquedaListener:busquedaListeners) {
			busquedaListener.onBuscarAdmin(genero, campoDeBusqueda, rdbtnPeliculas.isSelected(), modo);
		}
	}
	

	public boolean isPeliculasSelected() {
		return rdbtnPeliculas.isSelected();
	}
	public boolean isSeriesSelected() {
		return rdbtnSeries.isSelected();
	}
}
