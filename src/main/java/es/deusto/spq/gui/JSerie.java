package es.deusto.spq.gui;

import javax.swing.JPanel;

import es.deusto.data.Serie;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.NumberFormat;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JFormattedTextField;

public class JSerie extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7005907198791608715L;
	private JTextArea textArea;
	private JLabel lblTtulo;
	private JTextField textField;
	private JDateChooser dateChooser;
	private JLabel lblAo;
	private JLabel lblValoracin;
	private JFormattedTextField formattedTextField;
	private JTemporada temporada;

	/**
	 * Create the panel.
	 */
	public JSerie() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblTtulo = new JLabel("Título");
		lblTtulo.setFont(new Font("Tahoma", Font.PLAIN, 31));
		GridBagConstraints gbc_lblTtulo = new GridBagConstraints();
		gbc_lblTtulo.gridwidth = 2;
		gbc_lblTtulo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTtulo.gridx = 0;
		gbc_lblTtulo.gridy = 0;
		add(lblTtulo, gbc_lblTtulo);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 1;
		add(textArea, gbc_textArea);
		
		JLabel lblGnero = new JLabel("Género:");
		GridBagConstraints gbc_lblGnero = new GridBagConstraints();
		gbc_lblGnero.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGnero.insets = new Insets(0, 0, 5, 5);
		gbc_lblGnero.gridx = 0;
		gbc_lblGnero.gridy = 2;
		add(lblGnero, gbc_lblGnero);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		lblAo = new JLabel("Año: ");
		GridBagConstraints gbc_lblAo = new GridBagConstraints();
		gbc_lblAo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAo.insets = new Insets(0, 0, 5, 5);
		gbc_lblAo.gridx = 0;
		gbc_lblAo.gridy = 3;
		add(lblAo, gbc_lblAo);
		
		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 3;
		add(dateChooser, gbc_dateChooser);
		
		lblValoracin = new JLabel("Valoración:");
		GridBagConstraints gbc_lblValoracin = new GridBagConstraints();
		gbc_lblValoracin.anchor = GridBagConstraints.EAST;
		gbc_lblValoracin.insets = new Insets(0, 0, 5, 5);
		gbc_lblValoracin.gridx = 0;
		gbc_lblValoracin.gridy = 4;
		add(lblValoracin, gbc_lblValoracin);
		
		formattedTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
		GridBagConstraints gbc_formattedTextField = new GridBagConstraints();
		gbc_formattedTextField.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField.gridx = 1;
		gbc_formattedTextField.gridy = 4;
		add(formattedTextField, gbc_formattedTextField);
		
		temporada = new JTemporada();
		GridBagConstraints gbc_temporada = new GridBagConstraints();
		gbc_temporada.gridwidth = 2;
		gbc_temporada.fill = GridBagConstraints.BOTH;
		gbc_temporada.gridx = 0;
		gbc_temporada.gridy = 5;
		add(temporada, gbc_temporada);

	}
	
	public void setSerie(Serie serie) {
//		lblTtulo.setText(serie.getTitulo());
//		dateChooser.setDate(DateFormat.parse(serie.getAnho()));
//		serie.getGenero();
//		serie.getSinopsis();
//		serie.getTemps();
//		serie.getVal();
	}

}
