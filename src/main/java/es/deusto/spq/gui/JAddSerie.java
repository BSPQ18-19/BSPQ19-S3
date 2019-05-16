package es.deusto.spq.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import es.deusto.data.Serie;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.GridLayout;

public class JAddSerie extends JPanel {
	private JTextField txtFieldTtulo;
	private JTextField textFieldSinopsis;
	private ArrayList<String> generos = new ArrayList<String>();

	/**
	 * Create the panel.
	 */
	public JAddSerie(CardLayout card) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{388, 0};
		gridBagLayout.rowHeights = new int[]{55, 170, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panelSuperior = new JPanel();
		GridBagConstraints gbc_panelSuperior = new GridBagConstraints();
		gbc_panelSuperior.insets = new Insets(0, 0, 5, 0);
		gbc_panelSuperior.fill = GridBagConstraints.BOTH;
		gbc_panelSuperior.gridx = 0;
		gbc_panelSuperior.gridy = 0;
		add(panelSuperior, gbc_panelSuperior);
		panelSuperior.setLayout(new BorderLayout(0, 0));
		
		txtFieldTtulo = new JTextField();
		txtFieldTtulo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtFieldTtulo.setText("");
			}
		});
		txtFieldTtulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldTtulo.setText("Inserte el título");
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
		gbl_panelCentral.columnWidths = new int[]{56, 420, 0};
		gbl_panelCentral.rowHeights = new int[]{0, 0, 60, 0};
		gbl_panelCentral.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		
		JLabel lblAnho = new JLabel("Año");
		GridBagConstraints gbc_lblAnho = new GridBagConstraints();
		gbc_lblAnho.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnho.gridx = 0;
		gbc_lblAnho.gridy = 0;
		panelCentral.add(lblAnho, gbc_lblAnho);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1970, 1895, 2019, 1));
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
		
		JComboBox comboBoxGenero = new JComboBox(generos.toArray());
		GridBagConstraints gbc_comboBoxGenero = new GridBagConstraints();
		gbc_comboBoxGenero.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxGenero.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxGenero.gridx = 1;
		gbc_comboBoxGenero.gridy = 1;
		panelCentral.add(comboBoxGenero, gbc_comboBoxGenero);
		
		JLabel lblSinopsis = new JLabel("Sinopsis");
		GridBagConstraints gbc_lblSinopsis = new GridBagConstraints();
		gbc_lblSinopsis.insets = new Insets(0, 0, 0, 5);
		gbc_lblSinopsis.gridx = 0;
		gbc_lblSinopsis.gridy = 2;
		panelCentral.add(lblSinopsis, gbc_lblSinopsis);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		panelCentral.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		textFieldSinopsis = new JTextField();
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
				String genero = (String) comboBoxGenero.getSelectedItem();
				System.out.println(genero);
				clear();
				card.show(getParent(), JMainFrame.ADMIN);
			}
		});
		panelInferior.add(btnCancelar, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		panelInferior.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnCaps = new JButton("Añadir capítulos");
		btnCaps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel_1.add(btnCaps);
		
		JButton button = new JButton("Añadir");
		panel_1.add(button);
	}
	
	public void clear() {
		txtFieldTtulo.setText("");
		textFieldSinopsis.setText("");
	}

}
