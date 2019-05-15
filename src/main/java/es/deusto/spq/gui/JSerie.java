package es.deusto.spq.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import es.deusto.data.Serie;
import es.deusto.data.Temporada;

public class JSerie extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7005907198791608715L;
	private JTextArea textAreaSinopsis;
	private JLabel lblTtulo;
	private JTextField textFieldGenero;
	private JLabel lblAo;
	private JLabel lblValoracin;
	private JTemporada temporada;
	private JTextField textFieldAnyo;
	private JTextField textFieldValoracion;

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
		
		textAreaSinopsis = new JTextArea();
		textAreaSinopsis.setEditable(false);
		GridBagConstraints gbc_textAreaDescripcion = new GridBagConstraints();
		gbc_textAreaDescripcion.gridwidth = 2;
		gbc_textAreaDescripcion.insets = new Insets(0, 0, 5, 0);
		gbc_textAreaDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textAreaDescripcion.gridx = 0;
		gbc_textAreaDescripcion.gridy = 1;
		add(textAreaSinopsis, gbc_textAreaDescripcion);
		
		JLabel lblGnero = new JLabel("Género:");
		GridBagConstraints gbc_lblGnero = new GridBagConstraints();
		gbc_lblGnero.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGnero.insets = new Insets(0, 0, 5, 5);
		gbc_lblGnero.gridx = 0;
		gbc_lblGnero.gridy = 2;
		add(lblGnero, gbc_lblGnero);
		
		textFieldGenero = new JTextField();
		textFieldGenero.setEditable(false);
		GridBagConstraints gbc_textFieldGenero = new GridBagConstraints();
		gbc_textFieldGenero.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldGenero.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldGenero.gridx = 1;
		gbc_textFieldGenero.gridy = 2;
		add(textFieldGenero, gbc_textFieldGenero);
		textFieldGenero.setColumns(10);
		
		lblAo = new JLabel("Año: ");
		GridBagConstraints gbc_lblAo = new GridBagConstraints();
		gbc_lblAo.anchor = GridBagConstraints.EAST;
		gbc_lblAo.insets = new Insets(0, 0, 5, 5);
		gbc_lblAo.gridx = 0;
		gbc_lblAo.gridy = 3;
		add(lblAo, gbc_lblAo);
		
		textFieldAnyo = new JTextField();
		textFieldAnyo.setEditable(false);
		GridBagConstraints gbc_textFieldAnyo = new GridBagConstraints();
		gbc_textFieldAnyo.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAnyo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAnyo.gridx = 1;
		gbc_textFieldAnyo.gridy = 3;
		add(textFieldAnyo, gbc_textFieldAnyo);
		textFieldAnyo.setColumns(10);
		
		lblValoracin = new JLabel("Valoración:");
		GridBagConstraints gbc_lblValoracin = new GridBagConstraints();
		gbc_lblValoracin.anchor = GridBagConstraints.EAST;
		gbc_lblValoracin.insets = new Insets(0, 0, 5, 5);
		gbc_lblValoracin.gridx = 0;
		gbc_lblValoracin.gridy = 4;
		add(lblValoracin, gbc_lblValoracin);
		
		textFieldValoracion = new JTextField();
		textFieldValoracion.setEditable(false);
		GridBagConstraints gbc_textFieldValoracion = new GridBagConstraints();
		gbc_textFieldValoracion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldValoracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldValoracion.gridx = 1;
		gbc_textFieldValoracion.gridy = 4;
		add(textFieldValoracion, gbc_textFieldValoracion);
		textFieldValoracion.setColumns(10);
		
		temporada = new JTemporada();
		GridBagConstraints gbc_temporada = new GridBagConstraints();
		gbc_temporada.gridwidth = 2;
		gbc_temporada.fill = GridBagConstraints.BOTH;
		gbc_temporada.gridx = 0;
		gbc_temporada.gridy = 5;
		add(temporada, gbc_temporada);

	}
	
	public void setSerie(Serie serie) {
		lblTtulo.setText(serie.getTitulo());
		textAreaSinopsis.setText(serie.getSinopsis());
		textFieldGenero.setText(serie.getGenero());
		textFieldAnyo.setText(Integer.toString(serie.getAnho()));
		textFieldValoracion.setText(Double.toString(serie.getVal()));
		
		List<Temporada> temporadas = serie.getTemps();
		temporada.eliminarTodas();
		if(temporadas != null) {
			for (Temporada t:temporadas) {
				temporada.anyadirTemporada(t);
			}
		}
	}

}
