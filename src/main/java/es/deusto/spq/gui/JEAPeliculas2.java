package es.deusto.spq.gui;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import es.deusto.data.Pelicula;
//import es.deusto.spq.remote.Rmi;
import es.deusto.spq.remote.IRmi;
import es.deusto.spq.remote.ServiceLocator;

public class JEAPeliculas2 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5499785594445789660L;
	private JTextField idP;
	private JTextField titulo;
	private JTextField anyo;
	private JTextField duracion;
	private JTextField genero;
	private JTextField edadRecom;
	private JTextField sinopsis;
	ArrayList<Pelicula> pelis = new ArrayList<Pelicula>();
	private ServiceLocator serviceLocator;
	
	
	/**
	 * Create panel
	 * @param cardLayout
	 * @param servicelocator
	 */
	
	public JEAPeliculas2(CardLayout cardLayout, ServiceLocator servicelocator) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblIdPelicula = new JLabel("Id Pelicula");
		GridBagConstraints gbc_lblIdPelicula = new GridBagConstraints();
		gbc_lblIdPelicula.anchor = GridBagConstraints.EAST;
		gbc_lblIdPelicula.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdPelicula.gridx = 1;
		gbc_lblIdPelicula.gridy = 1;
		add(lblIdPelicula, gbc_lblIdPelicula);
		
		idP = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		add(idP, gbc_textField);
		idP.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Título");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);
		
		titulo = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		add(titulo, gbc_textField_1);
		titulo.setColumns(10);
		
		JLabel lblAo = new JLabel("Año (DD-MM-YYYY)");
		GridBagConstraints gbc_lblAo = new GridBagConstraints();
		gbc_lblAo.anchor = GridBagConstraints.EAST;
		gbc_lblAo.insets = new Insets(0, 0, 5, 5);
		gbc_lblAo.gridx = 1;
		gbc_lblAo.gridy = 3;
		add(lblAo, gbc_lblAo);
		
		anyo = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 3;
		add(anyo, gbc_textField_2);
		anyo.setColumns(10);
		
		JLabel lblDuracin = new JLabel("Duración");
		GridBagConstraints gbc_lblDuracin = new GridBagConstraints();
		gbc_lblDuracin.anchor = GridBagConstraints.EAST;
		gbc_lblDuracin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuracin.gridx = 1;
		gbc_lblDuracin.gridy = 4;
		add(lblDuracin, gbc_lblDuracin);
		
		duracion = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 4;
		add(duracion, gbc_textField_3);
		duracion.setColumns(10);
		
		JLabel lblGnero = new JLabel("Género");
		GridBagConstraints gbc_lblGnero = new GridBagConstraints();
		gbc_lblGnero.anchor = GridBagConstraints.EAST;
		gbc_lblGnero.insets = new Insets(0, 0, 5, 5);
		gbc_lblGnero.gridx = 1;
		gbc_lblGnero.gridy = 5;
		add(lblGnero, gbc_lblGnero);
		
		genero = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 5;
		add(genero, gbc_textField_4);
		genero.setColumns(10);
		
		JLabel lblEdadrecom = new JLabel("EdadRecom");
		GridBagConstraints gbc_lblEdadrecom = new GridBagConstraints();
		gbc_lblEdadrecom.anchor = GridBagConstraints.EAST;
		gbc_lblEdadrecom.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdadrecom.gridx = 1;
		gbc_lblEdadrecom.gridy = 6;
		add(lblEdadrecom, gbc_lblEdadrecom);
		
		edadRecom = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 2;
		gbc_textField_5.gridy = 6;
		add(edadRecom, gbc_textField_5);
		edadRecom.setColumns(10);
		
		JLabel lblSinopsis = new JLabel("Sinopsis");
		GridBagConstraints gbc_lblSinopsis = new GridBagConstraints();
		gbc_lblSinopsis.anchor = GridBagConstraints.EAST;
		gbc_lblSinopsis.insets = new Insets(0, 0, 5, 5);
		gbc_lblSinopsis.gridx = 1;
		gbc_lblSinopsis.gridy = 7;
		add(lblSinopsis, gbc_lblSinopsis);
		
		sinopsis = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 2;
		gbc_textField_6.gridy = 7;
		add(sinopsis, gbc_textField_6);
		sinopsis.setColumns(10);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 9;
		add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAtras = new JButton("Atrás");
		panel.add(btnAtras);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO da error
				cardLayout.show(getParent(), JMainFrame.ADMIN);
//				cardLayout.show(getParent(), JMainFrame.REGISTRO);
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		panel.add(btnEliminar);
		
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				eliminarPelicula2(titulo.getText());
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		panel.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				editarPelicula2(idP.getText(), titulo.getText(), anyo.getText(), duracion.getText(), genero.getText(), edadRecom.getText(), sinopsis.getText());
				
			}

		});
		JButton btnAñadir = new JButton("Añadir");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadirPelicula2(idP.getText(), titulo.getText(), anyo.getText(), duracion.getText(), genero.getText(), edadRecom.getText(), sinopsis.getText());
				
			}
		});
		panel.add(btnAñadir);
		
//		btnEditar.addListener((e)->processEditarEvent());
//		btnEliminar.addActionListener((e)->processAñadirEvent());
//		btnAñadir.addActionListener((e)->processAñadirEvent());
	}
	public boolean eliminarPelicula2(String titulo){
		try{
			IRmi s = serviceLocator.getService();
			Pelicula[] p = s.eliminarPelicula(titulo);
			return false;
		}catch(RemoteException e){	
			return false;
		}
	}
	public boolean editarPelicula2(String idP, String titulo, String anyo, String duracion, String genero, String edadRecom, String sinopsis){
		try{
			IRmi s = serviceLocator.getService();
			Pelicula[] p = s.editarPelicula(idP, titulo,anyo,duracion,genero,edadRecom,sinopsis);
			return false;
		}catch(RemoteException e){	
			return false;
		}
	}
	public boolean añadirPelicula2(String idP, String titulo, String anyo, String duracion, String genero, String edadRecom, String sinopsis){
		try{
			IRmi s = serviceLocator.getService();
			Pelicula[] p = s.añadirPelicula(idP, titulo,anyo,duracion,genero,edadRecom,sinopsis);
			return false;
		}catch(RemoteException e){	
			return false;
		}
	}
	
	private ArrayList<EditarListener> editarListeners = new ArrayList<EditarListener>();
	public void addEditarListener(EditarListener editarListener){
		editarListeners.add(editarListener);
	}
	public boolean removeEditarListener(EditarListener editarListener) {
		return editarListeners.remove(editarListener);
	}
	
	private ArrayList<AñadirListener> añadirListeners = new ArrayList<AñadirListener>();
	public void addAñadirListener(AñadirListener añadirListener){
		añadirListeners.add(añadirListener);
	}
	public boolean removeAñadirListener(AñadirListener añadirListener) {
		return añadirListeners.remove(añadirListener);
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JEAPeliculas2 jEAPeliculas2 = new JEAPeliculas2(null, null);
		frame.setContentPane(jEAPeliculas2);
		frame.setSize(600,500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}