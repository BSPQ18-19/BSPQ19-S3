package es.deusto.spq.gui;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JUsuario extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4776827044525863728L;
	
	private JLabel lblBienvenido;

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Unable to find file: " + path);
			return null;
		}
	}

	public JUsuario(CardLayout cardLayout) {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JButton btnVlver = new JButton("Volver");
		btnVlver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cardLayout.show(getParent(), JMainFrame.LOGIN);
				
			}
		});
		
		lblBienvenido = new JLabel();
		GridBagConstraints gbc_lblBienvenido = new GridBagConstraints();
		gbc_lblBienvenido.insets = new Insets(0, 0, 5, 5);
		gbc_lblBienvenido.gridx = 1;
		gbc_lblBienvenido.gridy = 0;
		add(lblBienvenido, gbc_lblBienvenido);
		btnVlver.setIcon(new ImageIcon(JUsuario.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		GridBagConstraints gbc_btnVlver = new GridBagConstraints();
		gbc_btnVlver.insets = new Insets(0, 0, 5, 5);
		gbc_btnVlver.gridx = 0;
		gbc_btnVlver.gridy = 1;
		add(btnVlver, gbc_btnVlver);

		JButton btnPeliculas = new JButton("Buscar películas o series");
		btnPeliculas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(getParent(), JMainFrame.PANEL_BUSQUEDA_USUARIO);
			}
		});
		GridBagConstraints gbc_btnPeliculas = new GridBagConstraints();
		gbc_btnPeliculas.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPeliculas.insets = new Insets(0, 0, 5, 5);
		gbc_btnPeliculas.gridx = 1;
		gbc_btnPeliculas.gridy = 2;
		add(btnPeliculas, gbc_btnPeliculas);

		JButton btnMiLista = new JButton("Mi Lista");
		GridBagConstraints gbc_btnMiLista = new GridBagConstraints();
		gbc_btnMiLista.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMiLista.insets = new Insets(0, 0, 5, 5);
		gbc_btnMiLista.gridx = 1;
		gbc_btnMiLista.gridy = 3;
		add(btnMiLista, gbc_btnMiLista);

		JButton btnConfig = new JButton("Config");
		btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cardLayout.show(getParent(), JMainFrame.PARENTAL);
				
			}
		});
		//btnConfig.setIcon(createImageIcon("/ClienteHBN/src/main/java/es/deusto/spq/res/config.png", "volver"));

		GridBagConstraints gbc_btnConfig = new GridBagConstraints();
		gbc_btnConfig.insets = new Insets(0, 0, 5, 0);
		gbc_btnConfig.gridx = 2;
		gbc_btnConfig.gridy = 1;
		add(btnConfig, gbc_btnConfig);
		
		JButton btnMisPerfiles = new JButton("Mis perfiles");
		btnMisPerfiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(getParent(), JMainFrame.PERFILES);
			}
		});
		GridBagConstraints gbc_btnMisPerfiles = new GridBagConstraints();
		gbc_btnMisPerfiles.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMisPerfiles.insets = new Insets(0, 0, 0, 5);
		gbc_btnMisPerfiles.gridx = 1;
		gbc_btnMisPerfiles.gridy = 4;
		add(btnMisPerfiles, gbc_btnMisPerfiles);

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				lblBienvenido.setText("¡Bienvenido, " + JMainFrame.usuario + "!");
			}
		});
	}

}
