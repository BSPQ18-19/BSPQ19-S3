package es.deusto.spq.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
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
import java.awt.event.ActionEvent;

public class JSelectorPeril extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3907730236253724238L;
	
	
	private DefaultListModel<JPerfil> defaultListModel;
	/**
	 * Create the panel.
	 */
	public JSelectorPeril(JPerfil perfiles []) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPerfilActual = new JLabel("Perfil actual:");
		GridBagConstraints gbc_lblPerfilActual = new GridBagConstraints();
		gbc_lblPerfilActual.insets = new Insets(0, 0, 5, 5);
		gbc_lblPerfilActual.gridx = 0;
		gbc_lblPerfilActual.gridy = 0;
		add(lblPerfilActual, gbc_lblPerfilActual);
		
		GridBagConstraints gbc_perfil = new GridBagConstraints();
		gbc_perfil.insets = new Insets(0, 0, 5, 0);
		gbc_perfil.fill = GridBagConstraints.BOTH;
		gbc_perfil.gridx = 1;
		gbc_perfil.gridy = 0;
		add(perfiles[0], gbc_perfil);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		JList<JPerfil> list = new JList<JPerfil>();
		scrollPane.setViewportView(list);
		
		 list.setCellRenderer(new MyListRenderer());
		
		defaultListModel = new DefaultListModel<JPerfil>();
		list.setModel(defaultListModel);
		
		add(perfiles);

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
		frame.setSize(300, 100);
		frame.setLocationRelativeTo(null);
		JPerfil perfiles[] = new JPerfil[] {new JPerfil("1"), new JPerfil("2")};
		JSelectorPeril jSelectorPeril = new JSelectorPeril(perfiles);
		frame.setContentPane(jSelectorPeril);
		frame.setVisible(true);
	}
	
	private class MyListRenderer extends DefaultListCellRenderer {

	    /**
		 * 
		 */
		private static final long serialVersionUID = -6541168719599761120L;

		@Override
		public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JPerfil perfil = (JPerfil) value;
			perfil.setEditable(cellHasFocus);
	        return perfil;
	    }
	}
}
