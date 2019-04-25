package es.deusto.spq.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import es.deusto.spq.remote.IRmi;
import es.deusto.spq.remote.ServiceLocator;

import javax.swing.event.ListSelectionEvent;

public class JSelectorPerfil extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3907730236253724238L;

	private DefaultListModel<JPerfil> defaultListModel;
	private JList<JPerfil> list;

	private JPerfil perfil;

	public JSelectorPerfil(JPerfil perfiles[]) {
		this();
		perfil.setNombre(perfiles[0].getNombre());
		add(perfiles);
		list.setSelectedIndex(0);
	}

	/**
	 * Create the panel.
	 */
	public JSelectorPerfil() { // JPerfil perfiles []
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblPerfilActual = new JLabel("Perfil actual:");
		GridBagConstraints gbc_lblPerfilActual = new GridBagConstraints();
		gbc_lblPerfilActual.insets = new Insets(0, 0, 5, 5);
		gbc_lblPerfilActual.gridx = 0;
		gbc_lblPerfilActual.gridy = 0;
		add(lblPerfilActual, gbc_lblPerfilActual);

		JButton btnNuevoPerfil = new JButton("Nuevo perfil");
		btnNuevoPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPerfil perfil = new JPerfil("Nuevo Perfil");
				defaultListModel.add(0, perfil);
			}
		});
		GridBagConstraints gbc_btnNuevoPerfil = new GridBagConstraints();
		gbc_btnNuevoPerfil.insets = new Insets(0, 0, 5, 0);
		gbc_btnNuevoPerfil.gridx = 2;
		gbc_btnNuevoPerfil.gridy = 0;
		add(btnNuevoPerfil, gbc_btnNuevoPerfil);

		perfil = new JPerfil("");
		perfil.setEditable(true);
		GridBagConstraints gbc_perfil = new GridBagConstraints();
		gbc_perfil.gridwidth = 2;
		gbc_perfil.insets = new Insets(0, 0, 5, 5);
		gbc_perfil.fill = GridBagConstraints.BOTH;
		gbc_perfil.gridx = 0;
		gbc_perfil.gridy = 1;
		add(perfil, gbc_perfil);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
		

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPerfil p = list.getSelectedValue();
				if (p != null) {
					p.setNombre(perfil.getNombre());
					list.repaint();
				}
			}
		});
		GridBagConstraints gbc_btnActualizar = new GridBagConstraints();
		gbc_btnActualizar.insets = new Insets(0, 0, 5, 0);
		gbc_btnActualizar.gridx = 2;
		gbc_btnActualizar.gridy = 1;
		add(btnActualizar, gbc_btnActualizar);
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.anchor = GridBagConstraints.SOUTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 2;
		add(btnAceptar, gbc_btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener((e) -> cancelar());
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 3;
		add(btnCancelar, gbc_btnCancelar);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		defaultListModel = new DefaultListModel<JPerfil>();

		list = new JList<JPerfil>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (arg0.getValueIsAdjusting() == false) {
					int index = list.getSelectedIndex();
					JPerfil p = defaultListModel.get(index);
					perfil.setNombre(p.getNombre());
				}
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);

		list.setCellRenderer(new MyListRenderer());

		list.setModel(defaultListModel);
	}

	public JSelectorPerfil(ServiceLocator serviceLocator) {
		this();
		IRmi service = serviceLocator.getService();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				defaultListModel.removeAllElements();
				try {
					String perfiles[] = service.getPerfiles(JMainFrame.usuario);
					for (String s : perfiles) {
						add(new JPerfil(s));
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void add(JPerfil perfil) {
		defaultListModel.addElement(perfil);
	}

	public void add(JPerfil perfiles[]) {
		for (JPerfil perfil : perfiles) {
			defaultListModel.addElement(perfil);
		}
	}

	public void removeAllElements() {
		defaultListModel.removeAllElements();
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 600);
		frame.setLocationRelativeTo(null);
		JPerfil perfiles[] = new JPerfil[] { new JPerfil("1"), new JPerfil("2") };
		JSelectorPerfil jSelectorPeril = new JSelectorPerfil(perfiles);
		frame.setContentPane(jSelectorPeril);
		frame.setVisible(true);
	}

	private class MyListRenderer extends DefaultListCellRenderer {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6541168719599761120L;

		@Override
		public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			JPerfil perfil = (JPerfil) value;
			if (isSelected) {
				perfil.setBackground(Color.lightGray);
			} else {
				perfil.setBackground(null);
			}
			return perfil;
		}
	}

	private void aceptar() {

	}

	private void cancelar() {

	}
}
