package es.deusto.spq.gui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import es.deusto.data.Serie;
import es.deusto.data.Temporada;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JEditCaps extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7005907198791608715L;
	private JLabel lblTtulo;
	private JTemporada temporada;
	private static ArrayList<Temporada> t;

	/**
	 * Create the panel.
	 */
	public JEditCaps(CardLayout cd) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblTtulo = new JLabel("Título");
		lblTtulo.setFont(new Font("Tahoma", Font.PLAIN, 31));
		GridBagConstraints gbc_lblTtulo = new GridBagConstraints();
		gbc_lblTtulo.gridwidth = 2;
		gbc_lblTtulo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTtulo.gridx = 0;
		gbc_lblTtulo.gridy = 0;
		add(lblTtulo, gbc_lblTtulo);
		
		temporada = new JTemporada();
		GridBagConstraints gbc_temporada = new GridBagConstraints();
		gbc_temporada.gridwidth = 2;
		gbc_temporada.fill = GridBagConstraints.BOTH;
		gbc_temporada.gridx = 0;
		gbc_temporada.gridy = 1;
		add(temporada, gbc_temporada);
		
		JPanel panel = new JPanel();
		temporada.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cd.show(getParent(), JMainFrame.EDITSERIES);
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t = new ArrayList<Temporada>(temporada.getTemps());
				cd.show(getParent(), JMainFrame.EDITSERIES);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 11;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);
		
	}
	
	public void setSerie(Serie serie) {
		lblTtulo.setText(serie.getTitulo());
		
		List<Temporada> temporadas = serie.getTemps();
		temporada.eliminarTodas();
		if(temporadas != null) {
			for (Temporada t:temporadas) {
				temporada.anyadirTemporada(t);
			}
		}
	}
	
	public static ArrayList<Temporada> getTemps() {
		return t;
	}

}