package es.deusto.spq.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import es.deusto.data.Pelicula;
import es.deusto.spq.remote.IRmi;
import es.deusto.spq.remote.ServiceLocator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class JPelicula extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceLocator serviceLocator;
	public JPelicula(CardLayout cardLayout,Pelicula p,ServiceLocator serviceLocator){
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.serviceLocator = serviceLocator;
		gridBagLayout.columnWidths = new int[] { 378, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 90, 37, 0, 0, 0};
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0 };
		setLayout(gridBagLayout);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setText(p.getTitulo());
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 0;
		add(lblTitulo, gbc_lblTitulo);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(getParent(), JMainFrame.PANEL_BUSQUEDA_USUARIO);
			}
		});
		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.anchor = GridBagConstraints.EAST;
		gbc_btnAtras.insets = new Insets(0, 0, 5, 0);
		gbc_btnAtras.gridx = 1;
		gbc_btnAtras.gridy = 0;
		add(btnAtras, gbc_btnAtras);
		
		JLabel lblAño = new JLabel("Año");
		lblAño.setText("Año: "+Integer.toString(p.getAnho()));
		GridBagConstraints gbc_lblAño = new GridBagConstraints();
		gbc_lblAño.anchor = GridBagConstraints.WEST;
		gbc_lblAño.insets = new Insets(0, 0, 5, 5);
		gbc_lblAño.gridx = 0;
		gbc_lblAño.gridy = 1;
		add(lblAño, gbc_lblAño);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setText("Genero :"+p.getGenero());
		GridBagConstraints gbc_lblGenero = new GridBagConstraints();
		gbc_lblGenero.anchor = GridBagConstraints.WEST;
		gbc_lblGenero.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenero.gridx = 0;
		gbc_lblGenero.gridy = 2;
		add(lblGenero, gbc_lblGenero);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setText("Edad recomendada :+"+p.getEdad_rec());
		GridBagConstraints gbc_lblEdad = new GridBagConstraints();
		gbc_lblEdad.anchor = GridBagConstraints.WEST;
		gbc_lblEdad.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdad.gridx = 0;
		gbc_lblEdad.gridy = 3;
		add(lblEdad, gbc_lblEdad);
		
		JLabel lblDesc = new JLabel("desc");
		lblDesc.setText("<html>"+p.getSinopsis());
		GridBagConstraints gbc_lblDesc = new GridBagConstraints();
		gbc_lblDesc.fill = GridBagConstraints.BOTH;
		gbc_lblDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblDesc.gridx = 0;
		gbc_lblDesc.gridy = 4;
		add(lblDesc, gbc_lblDesc);
		
		JLabel lblValoracion = new JLabel("Valoracion");
		lblValoracion.setText("Valoración media:"+p.getValoracion()+"/5");
		GridBagConstraints gbc_lblValoracion = new GridBagConstraints();
		gbc_lblValoracion.fill = GridBagConstraints.BOTH;
		gbc_lblValoracion.insets = new Insets(0, 0, 5, 0);
		gbc_lblValoracion.gridx = 1;
		gbc_lblValoracion.gridy = 4;
		add(lblValoracion, gbc_lblValoracion);
		
		JButton btnAadirALista = new JButton("Añadir a lista");
		GridBagConstraints gbc_btnAadirALista = new GridBagConstraints();
		gbc_btnAadirALista.anchor = GridBagConstraints.WEST;
		gbc_btnAadirALista.insets = new Insets(0, 0, 5, 5);
		gbc_btnAadirALista.gridx = 0;
		gbc_btnAadirALista.gridy = 5;
		add(btnAadirALista, gbc_btnAadirALista);
		
	
		
		
		
		JButton btnValorarPelicula = new JButton("Valorar Pelicula");
		btnValorarPelicula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] options = {"0", "1", "2", "3","4","5"};
				int seleccion = JOptionPane.showOptionDialog(null, "¿Qué nota le pondrías a esta película?", "Valora "+p.getTitulo(), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				System.out.println(seleccion);
				//calculo valoracion
				double val=(p.getValoracion()+seleccion)/(p.getNumvotos()+1);
				System.out.println("Valor final "+val);
				try {
					valorar(val, p);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnValorarPelicula = new GridBagConstraints();
		gbc_btnValorarPelicula.insets = new Insets(0, 0, 5, 0);
		gbc_btnValorarPelicula.gridx = 1;
		gbc_btnValorarPelicula.gridy = 5;
		add(btnValorarPelicula, gbc_btnValorarPelicula);
		
	}
	
	public void valorar(double val,Pelicula p)
			throws RemoteException {

		System.out.println(serviceLocator);
		IRmi s = serviceLocator.getService();
		s.valorarPelicula(val, p);

	}

}
