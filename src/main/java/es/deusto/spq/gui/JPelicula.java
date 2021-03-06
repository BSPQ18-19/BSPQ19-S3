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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JPelicula extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceLocator serviceLocator;
	
	private JLabel lblTitulo = new JLabel();
	private JLabel lblAño = new JLabel();
	private JLabel lblGenero = new JLabel();
	private JLabel lblEdad = new JLabel();
	private JLabel lblDesc = new JLabel();
	private JLabel lblValoracion = new JLabel();
	private Pelicula p;
	public JPelicula(CardLayout cardLayout, ServiceLocator serviceLocator) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.serviceLocator = serviceLocator;
		gridBagLayout.columnWidths = new int[] { 378, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 90, 37, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0 };
		setLayout(gridBagLayout);
		
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 0;
		add(lblTitulo, gbc_lblTitulo);

		
		
		GridBagConstraints gbc_lblAño = new GridBagConstraints();
		gbc_lblAño.anchor = GridBagConstraints.WEST;
		gbc_lblAño.insets = new Insets(0, 0, 5, 5);
		gbc_lblAño.gridx = 0;
		gbc_lblAño.gridy = 1;
		add(lblAño, gbc_lblAño);

		
		
		GridBagConstraints gbc_lblGenero = new GridBagConstraints();
		gbc_lblGenero.anchor = GridBagConstraints.WEST;
		gbc_lblGenero.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenero.gridx = 0;
		gbc_lblGenero.gridy = 2;
		add(lblGenero, gbc_lblGenero);

		
		
		GridBagConstraints gbc_lblEdad = new GridBagConstraints();
		gbc_lblEdad.anchor = GridBagConstraints.WEST;
		gbc_lblEdad.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdad.gridx = 0;
		gbc_lblEdad.gridy = 3;
		add(lblEdad, gbc_lblEdad);


		
		GridBagConstraints gbc_lblDesc = new GridBagConstraints();
		gbc_lblDesc.fill = GridBagConstraints.BOTH;
		gbc_lblDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblDesc.gridx = 0;
		gbc_lblDesc.gridy = 4;
		add(lblDesc, gbc_lblDesc);

		
		
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
				int seleccion = JOptionPane.showOptionDialog(null, "¿Qué nota le pondrías a esta película?", "Valora ", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				System.out.println(seleccion);
				//calculo valoracion
				double val=((p.getValoracion()*p.getNumvotos())+seleccion)/(p.getNumvotos()+1);
				System.out.println("Valor final "+val);
				try {
					valorar(val, p);
					lblValoracion.setText("Valoración media:" + val + "/5");
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

	public void valorar(double val, Pelicula p) throws RemoteException {

		System.out.println(serviceLocator);
		IRmi s = serviceLocator.getService();
		System.out.println(val);
		s.valorarPelicula(val, p);

	}
	public void setPelicula(Pelicula p){
		this.p=p;
		lblTitulo.setText(p.getTitulo());
		lblAño.setText("Año: " + Integer.toString(p.getAnho()));
		lblGenero.setText("Genero :" + p.getGenero());
		lblEdad.setText("Edad recomendada :+" + p.getEdad_rec());
		lblDesc.setText("<html>" + p.getSinopsis());
		lblValoracion.setText("Valoración media:" + p.getValoracion() + "/5");
	}

}
