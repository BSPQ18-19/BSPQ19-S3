package es.deusto.spq.gui;
import javax.swing.JPanel;
import java.awt.GridBagLayout;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JScrollPane;

import es.deusto.data.Cliente;
import es.deusto.spq.remote.IRmi;
import es.deusto.spq.remote.ServiceLocator;

import java.awt.Insets;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class JGestionarUsuarios extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3696860771215487513L;
	
	private DefaultListModel<Cliente> defaultListModel;
	private JTextField textField;
	private ServiceLocator serviceLocator;

	/**
	 * Constructor del {@link JGestionarUsuarios}
	 * @param cardLayout Es un {@link CardLayout} que se usa para cambiar entre paneles.
	 * @param serviceLocator Es un {@link ServiceLocator} para comunicarse con el servidor.
	 */
	public JGestionarUsuarios(CardLayout cardLayout, ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(JGestionarUsuarios.this.getParent(), JMainFrame.ADMIN);
			}
		});
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.anchor = GridBagConstraints.WEST;
		gbc_btnVolver.insets = new Insets(0, 0, 5, 5);
		gbc_btnVolver.gridx = 0;
		gbc_btnVolver.gridy = 0;
		add(btnVolver, gbc_btnVolver);
		
		JLabel lblGestionarUsuarios = new JLabel("Gestionar usuarios");
		lblGestionarUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 21));
		GridBagConstraints gbc_lblGestionarUsuarios = new GridBagConstraints();
		gbc_lblGestionarUsuarios.gridwidth = 2;
		gbc_lblGestionarUsuarios.insets = new Insets(0, 0, 5, 5);
		gbc_lblGestionarUsuarios.gridx = 0;
		gbc_lblGestionarUsuarios.gridy = 1;
		add(lblGestionarUsuarios, gbc_lblGestionarUsuarios);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processOnBuscarListener(textField.getText());
			}
		});
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscar.gridx = 1;
		gbc_btnBuscar.gridy = 2;
		add(btnBuscar, gbc_btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		JList<Cliente> list = new JList<Cliente>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		        if (arg0.getClickCount() == 2) {
		            int index = list.locationToIndex(arg0.getPoint());
		            if(index != -1) {
		            	Cliente cliente = defaultListModel.get(index);
		            	boolean valorOriginal = cliente.isHabilitado();
		            	Cliente c = JEditarUsuario.editar(cliente, JGestionarUsuarios.this);
		            	if(c != null) {
		            		list.revalidate();
		            		if(c.isHabilitado() != valorOriginal) {
		            			try {
									serviceLocator.getService().editarUsuarios(c.getNick(), c.isHabilitado());
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		            		}
		            	}
		            }
		        }
			}
		});
		scrollPane.setViewportView(list);
		
		MyListRenderer listRenderer = new MyListRenderer();
		list.setCellRenderer(listRenderer);
		defaultListModel = new DefaultListModel<Cliente>();
		list.setModel(defaultListModel);
	}
	
	private List<GestionarUsuarioListener> gestionarUsuarioListeners = new ArrayList<GestionarUsuarioListener>();
	private JButton btnBuscar;
	
	/**
	 * Añade el listener {@link GestionarUsuarioListener}
	 * @param gestionarUsuarioListener Un {@link GestionarUsuarioListener}
	 */
	public void addGestionarUsuarioListener(GestionarUsuarioListener gestionarUsuarioListener){
		gestionarUsuarioListeners.add(gestionarUsuarioListener);
	}
	
	/**
	 * Elimina el listener {@link GestionarUsuarioListener}
	 * @param gestionarUsuarioListener Un {@link GestionarUsuarioListener}
	 */
	public void removeGestionarUsuarioListener(GestionarUsuarioListener gestionarUsuarioListener){
		gestionarUsuarioListeners.remove(gestionarUsuarioListener);
	}
	
	protected void processClienteSeleccionadoListener(Cliente cliente) {
		for(GestionarUsuarioListener gestionarUsuarioListener:gestionarUsuarioListeners) {
			gestionarUsuarioListener.onSelected(cliente);
		}
	}
	
	protected void processOnBuscarListener(String texto) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				btnBuscar.setEnabled(false);
				IRmi service = serviceLocator.getService();
				if(service != null) {
					try {
						Cliente[] clientes = service.buscarUsuarios(texto);
						defaultListModel.clear();
						for(Cliente cliente:clientes) {
							defaultListModel.addElement(cliente);
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				btnBuscar.setEnabled(true);
			}
		});
		thread.start();
		for(GestionarUsuarioListener gestionarUsuarioListener:gestionarUsuarioListeners) {
			gestionarUsuarioListener.onBuscar(texto);
		}
	}
	
	
	public void clear() {
		defaultListModel.removeAllElements();
	}
	
	public void anyadirCliente(Cliente cliente) {
		defaultListModel.addElement(cliente);
	}
	
	private class MyListRenderer extends DefaultListCellRenderer {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6541168719599761120L;
		
		private JCliente jCliente = new JCliente();
		
		@Override
		public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			if(value instanceof Cliente) {
				Cliente cliente = (Cliente) value;
				jCliente.setCliente(cliente);
				return jCliente;
			}
			return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		}
	}

}
