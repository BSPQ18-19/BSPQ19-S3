package es.deusto.spq.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import es.deusto.data.Serie;
import es.deusto.data.Temporada;
import es.deusto.spq.remote.ServiceLocator;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.GridLayout;
import javax.swing.JSlider;

public class JEditSerie extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5337694821655679408L;
	
	private ServiceLocator serviceLocator;
	private JTextField txtFieldTtulo;
	private JSpinner spinner;
	private JSlider slider;
	private JComboBox comboBoxGenero;
	private JTextField textFieldSinopsis;
	private JButton btnCaps;
	private ArrayList<String> generos = new ArrayList<String>();
	private Serie serie = null;
	private ArrayList<Temporada> temps;

	/**
	 * Create the panel.
	 */
	public JEditSerie(CardLayout card, ServiceLocator serviceLocator, Serie s) {
		this.serviceLocator = serviceLocator;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 388, 0 };
		gridBagLayout.rowHeights = new int[] { 55, 170, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panelSuperior = new JPanel();
		GridBagConstraints gbc_panelSuperior = new GridBagConstraints();
		gbc_panelSuperior.insets = new Insets(0, 0, 5, 0);
		gbc_panelSuperior.fill = GridBagConstraints.BOTH;
		gbc_panelSuperior.gridx = 0;
		gbc_panelSuperior.gridy = 0;
		add(panelSuperior, gbc_panelSuperior);
		panelSuperior.setLayout(new BorderLayout(0, 0));

		txtFieldTtulo = new JTextField(s.getTitulo());
		txtFieldTtulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldTtulo.setFont(new Font("Oxygen", Font.PLAIN, 25));
		panelSuperior.add(txtFieldTtulo, BorderLayout.CENTER);
		txtFieldTtulo.setColumns(10);

		JPanel panelCentral = new JPanel();
		GridBagConstraints gbc_panelCentral = new GridBagConstraints();
		gbc_panelCentral.insets = new Insets(0, 0, 5, 0);
		gbc_panelCentral.fill = GridBagConstraints.BOTH;
		gbc_panelCentral.gridx = 0;
		gbc_panelCentral.gridy = 1;
		add(panelCentral, gbc_panelCentral);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[] { 56, 420, 0 };
		gbl_panelCentral.rowHeights = new int[] { 0, 0, 51, 60, 0 };
		gbl_panelCentral.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelCentral.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panelCentral.setLayout(gbl_panelCentral);

		JLabel lblAnho = new JLabel("Año");
		GridBagConstraints gbc_lblAnho = new GridBagConstraints();
		gbc_lblAnho.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnho.gridx = 0;
		gbc_lblAnho.gridy = 0;
		panelCentral.add(lblAnho, gbc_lblAnho);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(s.getAnho(), 1895, 2019, 1));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 0;
		panelCentral.add(spinner, gbc_spinner);

		JLabel lblGnero = new JLabel("Género");
		GridBagConstraints gbc_lblGnero = new GridBagConstraints();
		gbc_lblGnero.insets = new Insets(0, 0, 5, 5);
		gbc_lblGnero.gridx = 0;
		gbc_lblGnero.gridy = 1;
		panelCentral.add(lblGnero, gbc_lblGnero);

		generos.add("");
		generos.add("Drama");
		generos.add("Ciencia Ficción");
		generos.add("Terror");
		generos.add("Acción");
		generos.add("Comedia");

		comboBoxGenero = new JComboBox(generos.toArray());
		for (int i = 0; i < generos.size(); i++) {
			if (generos.get(i).equals(s.getGenero())) {
				comboBoxGenero.setSelectedIndex(i);
				break;
			} else {
				comboBoxGenero.setSelectedIndex(0);
			}
		}
		comboBoxGenero.setSelectedItem(4);
		GridBagConstraints gbc_comboBoxGenero = new GridBagConstraints();
		gbc_comboBoxGenero.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxGenero.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxGenero.gridx = 1;
		gbc_comboBoxGenero.gridy = 1;
		panelCentral.add(comboBoxGenero, gbc_comboBoxGenero);

		JLabel lblEdadRecomendada = new JLabel("Edad rec.");
		GridBagConstraints gbc_lblEdadRecomendada = new GridBagConstraints();
		gbc_lblEdadRecomendada.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdadRecomendada.gridx = 0;
		gbc_lblEdadRecomendada.gridy = 2;
		panelCentral.add(lblEdadRecomendada, gbc_lblEdadRecomendada);

		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(1);
		slider.setValue(s.getEdad_rec());
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider.insets = new Insets(0, 0, 5, 0);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 2;
		panelCentral.add(slider, gbc_slider);
		slider.setMaximum(18);
		slider.setMinimum(0);

		JLabel lblSinopsis = new JLabel("Sinopsis");
		GridBagConstraints gbc_lblSinopsis = new GridBagConstraints();
		gbc_lblSinopsis.insets = new Insets(0, 0, 0, 5);
		gbc_lblSinopsis.gridx = 0;
		gbc_lblSinopsis.gridy = 3;
		panelCentral.add(lblSinopsis, gbc_lblSinopsis);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		panelCentral.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));

		textFieldSinopsis = new JTextField(s.getSinopsis());
		panel.add(textFieldSinopsis, BorderLayout.CENTER);
		textFieldSinopsis.setColumns(10);

		JPanel panelInferior = new JPanel();
		GridBagConstraints gbc_panelInferior = new GridBagConstraints();
		gbc_panelInferior.fill = GridBagConstraints.BOTH;
		gbc_panelInferior.gridx = 0;
		gbc_panelInferior.gridy = 2;
		add(panelInferior, gbc_panelInferior);
		panelInferior.setLayout(new BorderLayout(0, 0));

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(getParent(), JMainFrame.ADMIN);
			}
		});
		panelInferior.add(btnCancelar, BorderLayout.WEST);

		JPanel panel_1 = new JPanel();
		panelInferior.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		if (s.getTemps().isEmpty()) {
			btnCaps = new JButton("Añadir capítulos");
		} else {
			btnCaps = new JButton("Editar capítulos");
		}
		btnCaps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (s.getTemps().isEmpty()) {
					if (!txtFieldTtulo.getText().isEmpty() && !textFieldSinopsis.getText().isEmpty()
							&& comboBoxGenero.getSelectedIndex() != 0) {
						ArrayList<Temporada> tes = JCrearCaps.getTemps();
						if (tes != null) {
							temps = tes;
							btnCaps.setEnabled(false);
						}
					} else {
						JOptionPane.showMessageDialog(getParent(), "Rellene primero todos los campos por favor.",
								"Campos incompletos", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					card.show(getParent(), JMainFrame.EDITEMPS);
				}
			}
		});
		panel_1.add(btnCaps);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtFieldTtulo.getText().isEmpty() && !textFieldSinopsis.getText().isEmpty()
						&& comboBoxGenero.getSelectedIndex() != 0) {
					if (!s.getTitulo().equals(txtFieldTtulo.getText()) || s.getAnho() != (Integer) spinner.getValue()
							|| s.getEdad_rec() != slider.getValue()
							|| !s.getGenero().equals((String) comboBoxGenero.getSelectedItem())
							|| !s.getSinopsis().equals(textFieldSinopsis.getText())) {
						serie = new Serie(txtFieldTtulo.getText(), (Integer) spinner.getValue(),
								(String) comboBoxGenero.getSelectedItem(), (Integer) slider.getValue(), 0,
								textFieldSinopsis.getText(), 0);
						temps = JEditCaps.getTemps();
						if (!btnCaps.isEnabled()) {
							serie.setTemps(temps);
						}
						// MODIFICAR SERIE EN BASE DE DATOS
						card.show(getParent(), JMainFrame.ADMIN);
					} else {
						JOptionPane.showMessageDialog(getParent(),
								"Debe modificar algún campo previamente, inténtelo de nuevo.", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		JButton btnNewButton = new JButton("Eliminar serie");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//BORRAR DE BD
			}
		});
		panel_1.add(btnNewButton);
		panel_1.add(btnGuardar);
	}
}
