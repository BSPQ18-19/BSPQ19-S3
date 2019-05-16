package es.deusto.spq.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import es.deusto.data.Capitulo;
import es.deusto.data.Temporada;

public class JContenedorCrearCaps extends JPanel {

	private JTextField textFieldTitulo;
	private JSpinner spinner;
	private JTextField textFieldDescr;
	private Capitulo c;
	private Temporada t;
	private static ArrayList<Temporada> temporadas = new ArrayList<Temporada>();
	private ArrayList<Capitulo> capitulos;

	/**
	 * Create the panel.
	 */
	public JContenedorCrearCaps(int n) {
		capitulos = new ArrayList<Capitulo>();
		capitulos.clear();
		t = new Temporada(n);

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 88, 374, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 173, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		this.setLayout(gbl_panel);
		{
			JLabel lblNewLabel = new JLabel("Título");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			this.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			textFieldTitulo = new JTextField();
			GridBagConstraints gbc_textFieldTitulo = new GridBagConstraints();
			gbc_textFieldTitulo.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldTitulo.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldTitulo.gridx = 1;
			gbc_textFieldTitulo.gridy = 0;
			this.add(textFieldTitulo, gbc_textFieldTitulo);
			textFieldTitulo.setColumns(10);
		}
		{
			JLabel lblAo = new JLabel("Duración (min)");
			GridBagConstraints gbc_lblAo = new GridBagConstraints();
			gbc_lblAo.insets = new Insets(0, 0, 5, 5);
			gbc_lblAo.gridx = 0;
			gbc_lblAo.gridy = 1;
			this.add(lblAo, gbc_lblAo);
		}
		{
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.insets = new Insets(0, 0, 5, 0);
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.gridx = 1;
			gbc_spinner.gridy = 1;
			this.add(spinner, gbc_spinner);
		}
		{
			JLabel lblDescripcin = new JLabel("Descripción");
			GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
			gbc_lblDescripcin.insets = new Insets(0, 0, 0, 5);
			gbc_lblDescripcin.gridx = 0;
			gbc_lblDescripcin.gridy = 2;
			this.add(lblDescripcin, gbc_lblDescripcin);
		}
		{
			JPanel panel_1 = new JPanel();
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.fill = GridBagConstraints.BOTH;
			gbc_panel_1.gridx = 1;
			gbc_panel_1.gridy = 2;
			this.add(panel_1, gbc_panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			{
				textFieldDescr = new JTextField();
				panel_1.add(textFieldDescr, BorderLayout.CENTER);
				textFieldDescr.setColumns(10);
			}
		}
		{
			JButton btnAadirCaptulo = new JButton("Añadir capítulo");
			GridBagConstraints gbc_btnAadirCaptulo = new GridBagConstraints();
			gbc_btnAadirCaptulo.anchor = GridBagConstraints.EAST;
			gbc_btnAadirCaptulo.gridx = 1;
			gbc_btnAadirCaptulo.gridy = 3;
			this.add(btnAadirCaptulo, gbc_btnAadirCaptulo);
			btnAadirCaptulo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!textFieldTitulo.getText().isEmpty() && !spinner.getValue().equals(0)
							&& !textFieldDescr.getText().isEmpty()) {
						String titulo = textFieldTitulo.getText();
						int duracion = (Integer) spinner.getValue();
						String descr = textFieldDescr.getText();
						c = new Capitulo(titulo, duracion, descr, 0);
						capitulos.add(c);
						t.setCaps(capitulos);
						boolean guardar = true;
						for (int i = 0; i < temporadas.size(); i++) {
							if (temporadas.get(i).getNum() == t.getNum()) {
								guardar = false;
							}
						}
						if (guardar) {
						temporadas.add(t);
						}
						clear();
					} else {
						JOptionPane.showMessageDialog(getParent(), "Rellene primero todos los campos por favor.",
								"Campos incompletos", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			clear();
		}
	}

	public Capitulo getCapitulo() {
		return c;
	}

	public ArrayList<Capitulo> getCapitulos() {
		return capitulos;
	}
	
	public ArrayList<Temporada> getTemporadas() {
		return temporadas;
	}

	public void clear() {
		textFieldTitulo.setText("");
		spinner.setValue(0);
		textFieldDescr.setText("");
	}
	
	public void clearData() {
		temporadas.clear();
	}
}
