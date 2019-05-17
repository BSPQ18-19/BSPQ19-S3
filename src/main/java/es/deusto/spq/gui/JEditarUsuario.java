package es.deusto.spq.gui;

import es.deusto.data.Cliente;
import es.deusto.data.Cliente.Modo;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JEditarUsuario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6702088638213054388L;
	private JCliente cliente_1;
	private boolean aceptar = false;

	/**
	 * Create the panel.
	 */
	public JEditarUsuario() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblEditarUsuario = new JLabel("Editar usuario");
		lblEditarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 21));
		GridBagConstraints gbc_lblEditarUsuario = new GridBagConstraints();
		gbc_lblEditarUsuario.gridwidth = 2;
		gbc_lblEditarUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_lblEditarUsuario.gridx = 0;
		gbc_lblEditarUsuario.gridy = 0;
		getContentPane().add(lblEditarUsuario, gbc_lblEditarUsuario);
		
		cliente_1 = new JCliente();
		GridBagConstraints gbc_cliente_1 = new GridBagConstraints();
		gbc_cliente_1.gridwidth = 2;
		gbc_cliente_1.insets = new Insets(0, 0, 5, 0);
		gbc_cliente_1.fill = GridBagConstraints.BOTH;
		gbc_cliente_1.gridx = 0;
		gbc_cliente_1.gridy = 1;
		getContentPane().add(cliente_1, gbc_cliente_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 0;
		gbc_btnCancelar.gridy = 2;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aceptar = true;
				dispose();
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.anchor = GridBagConstraints.EAST;
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 2;
		getContentPane().add(btnAceptar, gbc_btnAceptar);
	}
	
	public void setCliente(Cliente cliente) {
		cliente_1.setCliente(cliente);
	}
	
	public Cliente getCliente() {
		return cliente_1.getCliente();
	}
	
	public static Cliente editar(Cliente cliente) {
		return editar(cliente, null);
	}
	
	public static Cliente editar(Cliente cliente, Component c) {
		JEditarUsuario dialog = new JEditarUsuario();
		dialog.setSize(460, 300);
		dialog.setLocationRelativeTo(c);
		dialog.setModal(true);
		dialog.setCliente(cliente);
		dialog.setVisible(true);
		if(dialog.aceptar) {
			return dialog.getCliente();
		}
		return null;
	}
}
