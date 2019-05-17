package es.deusto.spq.gui;

import javax.swing.JPanel;

import es.deusto.data.Contenido;
import es.deusto.data.Pelicula;
import es.deusto.data.Serie;
import es.deusto.spq.remote.ServiceLocator;

import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class JPanelBusquedaAdmin extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8279609532633434074L;

	private DefaultListModel<JPortada> defaultListModel;
	private JList<JPortada> list;
	

	/**
	 * Create the panel.
	 * 
	 * @param serviceLocator
	 * @param cardLayout
	 */
	public JPanelBusquedaAdmin(CardLayout cardLayout, ServiceLocator serviceLocator) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(JPanelBusquedaAdmin.this.getParent(), JMainFrame.ADMIN);
			}
		});
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 5, 5);
		gbc_btnVolver.gridx = 0;
		gbc_btnVolver.gridy = 0;
		add(btnVolver, gbc_btnVolver);

		JBarraBusquedaAdmin barraBusqueda = new JBarraBusquedaAdmin();
		GridBagConstraints gbc_barraBusqueda = new GridBagConstraints();
		gbc_barraBusqueda.gridwidth = 2;
		gbc_barraBusqueda.insets = new Insets(0, 0, 5, 0);
		gbc_barraBusqueda.fill = GridBagConstraints.BOTH;
		gbc_barraBusqueda.gridx = 0;
		gbc_barraBusqueda.gridy = 1;
		add(barraBusqueda, gbc_barraBusqueda);
//
//		defaultListModel = new DefaultListModel<JContenido>();
//
//		JScrollPane scrollPane = new JScrollPane();
//		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
//		gbc_scrollPane.gridwidth = 2;
//		gbc_scrollPane.fill = GridBagConstraints.BOTH;
//		gbc_scrollPane.gridx = 0;
//		gbc_scrollPane.gridy = 2;
//		add(scrollPane, gbc_scrollPane);
//
//		list = new JList<JContenido>();
//		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		scrollPane.setViewportView(list);
//		list.setModel(defaultListModel);
//		defaultListModel.addElement(a);

		JContenedorResultadosBusqueda contenedorResultadosBusqueda = new JContenedorResultadosBusqueda();
		GridBagConstraints gbc_contenedorResultadosBusqueda = new GridBagConstraints();
		gbc_contenedorResultadosBusqueda.gridwidth = 2;
		gbc_contenedorResultadosBusqueda.insets = new Insets(0, 0, 0, 5);
		gbc_contenedorResultadosBusqueda.fill = GridBagConstraints.BOTH;
		gbc_contenedorResultadosBusqueda.gridx = 0;
		gbc_contenedorResultadosBusqueda.gridy = 2;
		add(contenedorResultadosBusqueda, gbc_contenedorResultadosBusqueda);

		barraBusqueda.addBusquedaListener(new BusquedaAdminListener() {

			@Override
			public void onBuscarAdmin(String genero, String campoDeBusqueda, boolean isPelicula, String modo) {
				Pelicula[] pelis;
				Serie[] series;
				try {
					if (isPelicula) {
						pelis = serviceLocator.getService().buscarPelicula(genero, campoDeBusqueda, modo);
						contenedorResultadosBusqueda.anyadirContenido(pelis);
					} else {
						series = serviceLocator.getService().buscarSerie(genero, campoDeBusqueda, modo);
						contenedorResultadosBusqueda.anyadirContenido(series);
					}
					
						
					

				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		contenedorResultadosBusqueda.addOnContenidoClicked(new ContenidoClickedListener() {
			
			@Override
			public void onContenidoClicked(Contenido c) {
				if (c instanceof Serie) {
				cardLayout.show(JPanelBusquedaAdmin.this.getParent(), JMainFrame.EDITSERIES);
				JMainFrame.serie1.setDatos((Serie) c);
				}
			}
		});

	}

}
