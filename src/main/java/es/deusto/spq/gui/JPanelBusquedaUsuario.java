package es.deusto.spq.gui;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import es.deusto.data.Contenido;
import es.deusto.data.Pelicula;
import es.deusto.data.Serie;
import es.deusto.spq.remote.ServiceLocator;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class JPanelBusquedaUsuario extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8279609532633434074L;

	private CardLayout cardLayout;
	private ServiceLocator serviceLocator;
	
	/**
	 * Create the panel.
	 * @param serviceLocator 
	 * @param cardLayout 
	 */
	public JPanelBusquedaUsuario(CardLayout cardLayout, ServiceLocator serviceLocator) {
		this.cardLayout = cardLayout;
		this.serviceLocator = serviceLocator;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(JPanelBusquedaUsuario.this.getParent(), JMainFrame.USUARIO);
			}
		});
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 5, 5);
		gbc_btnVolver.gridx = 0;
		gbc_btnVolver.gridy = 0;
		add(btnVolver, gbc_btnVolver);
		
		JBarraBusqueda barraBusqueda = new JBarraBusqueda();
		GridBagConstraints gbc_barraBusqueda = new GridBagConstraints();
		gbc_barraBusqueda.gridwidth = 2;
		gbc_barraBusqueda.insets = new Insets(0, 0, 5, 5);
		gbc_barraBusqueda.fill = GridBagConstraints.BOTH;
		gbc_barraBusqueda.gridx = 0;
		gbc_barraBusqueda.gridy = 1;
		add(barraBusqueda, gbc_barraBusqueda);
		
		JContenedorResultadosBusqueda contenedorResultadosBusqueda = new JContenedorResultadosBusqueda();
		GridBagConstraints gbc_contenedorResultadosBusqueda = new GridBagConstraints();
		gbc_contenedorResultadosBusqueda.gridwidth = 2;
		gbc_contenedorResultadosBusqueda.insets = new Insets(0, 0, 0, 5);
		gbc_contenedorResultadosBusqueda.fill = GridBagConstraints.BOTH;
		gbc_contenedorResultadosBusqueda.gridx = 0;
		gbc_contenedorResultadosBusqueda.gridy = 2;
		add(contenedorResultadosBusqueda, gbc_contenedorResultadosBusqueda);
		
		contenedorResultadosBusqueda.addOnContenidoClicked((c)->onContenidoClicked(c));
		
		barraBusqueda.addBusquedaListener(new BusquedaListener() {
			
			@Override
			public void onBuscar(String genero, String campoDeBusqueda, boolean isPelicula) {
				Contenido[] contenidos;
				try {
					if(isPelicula) {
						contenidos = serviceLocator.getService().buscarPelicula(genero, campoDeBusqueda);
					}else {
						contenidos = serviceLocator.getService().buscarSerie(genero, campoDeBusqueda);
					}
					contenedorResultadosBusqueda.anyadirContenido(contenidos);
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});

	}
	
	private void onContenidoClicked(Contenido c) {
		if(c instanceof Serie) {
			Serie serie = (Serie) c;
			JSerie jSerie = new JSerie(serviceLocator);
			jSerie.setSerie(serie);
			JDialog dialog = new JDialog();
			dialog.setSize(getSize());
			Point point = getLocation();
			SwingUtilities.convertPointToScreen(point, this);
			dialog.setLocation(point);
			dialog.setContentPane(jSerie);
			dialog.setModal(true);
			dialog.setVisible(true);
			dialog.dispose();
		}else if(c instanceof Pelicula) {
			Pelicula p =(Pelicula)c;
			JPelicula jp= new JPelicula(cardLayout,serviceLocator);
			jp.setPelicula(p);
			//cardLayout.show(getParent(), JMainFrame.JP);
			JDialog dialog = new JDialog();
			dialog.setSize(getSize());
			Point point = getLocation();
			SwingUtilities.convertPointToScreen(point, this);
			dialog.setLocation(point);
			dialog.setContentPane(jp);
			dialog.setModal(true);
			dialog.setVisible(true);
			dialog.dispose();
			
		}
		
	}
	

}
