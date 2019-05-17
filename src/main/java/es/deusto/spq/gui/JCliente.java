package es.deusto.spq.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import es.deusto.data.Cliente;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Font;

public class JCliente extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8879108310540555555L;
	private JTextField txtNombreDeUsuario;
	private JTextField txtContrasea;
	private JTextField txtNombre;
	private JCheckBox chckbxHabilitado;

	/**
	 * Create the panel.
	 */
	public JCliente() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblModo = new JLabel("modo");
		lblModo.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblModo = new GridBagConstraints();
		gbc_lblModo.gridwidth = 2;
		gbc_lblModo.insets = new Insets(0, 0, 5, 5);
		gbc_lblModo.gridx = 0;
		gbc_lblModo.gridy = 0;
		add(lblModo, gbc_lblModo);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario: ");
		GridBagConstraints gbc_lblNombreDeUsuario = new GridBagConstraints();
		gbc_lblNombreDeUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNombreDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreDeUsuario.gridx = 0;
		gbc_lblNombreDeUsuario.gridy = 1;
		add(lblNombreDeUsuario, gbc_lblNombreDeUsuario);
		
		txtNombreDeUsuario = new JTextField();
		txtNombreDeUsuario.setEditable(false);
		txtNombreDeUsuario.setOpaque(false);
		txtNombreDeUsuario.setText("Nombre de usuario");
		GridBagConstraints gbc_txtNombreDeUsuario = new GridBagConstraints();
		gbc_txtNombreDeUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_txtNombreDeUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombreDeUsuario.gridx = 1;
		gbc_txtNombreDeUsuario.gridy = 1;
		add(txtNombreDeUsuario, gbc_txtNombreDeUsuario);
		txtNombreDeUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña: ");
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasea.gridx = 0;
		gbc_lblContrasea.gridy = 2;
		add(lblContrasea, gbc_lblContrasea);
		
		txtContrasea = new JTextField();
		txtContrasea.setEditable(false);
		txtContrasea.setOpaque(false);
		txtContrasea.setText("Contraseña");
		GridBagConstraints gbc_txtContrasea = new GridBagConstraints();
		gbc_txtContrasea.insets = new Insets(0, 0, 5, 0);
		gbc_txtContrasea.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtContrasea.gridx = 1;
		gbc_txtContrasea.gridy = 2;
		add(txtContrasea, gbc_txtContrasea);
		txtContrasea.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 3;
		add(lblNombre, gbc_lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setOpaque(false);
		txtNombre.setText("Nombre");
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 0);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 1;
		gbc_txtNombre.gridy = 3;
		add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		chckbxHabilitado = new JCheckBox("Habilitado");
		chckbxHabilitado.setSelected(true);
		GridBagConstraints gbc_chckbxHabilitado = new GridBagConstraints();
		gbc_chckbxHabilitado.gridwidth = 2;
		gbc_chckbxHabilitado.gridx = 0;
		gbc_chckbxHabilitado.gridy = 4;
		add(chckbxHabilitado, gbc_chckbxHabilitado);
	}
	
	private Cliente cliente = null;
	private JLabel lblModo;
	public void setCliente(Cliente cliente) {
		if(cliente == null) {
			cliente = null;
			txtNombreDeUsuario.setText("");
			txtNombre.setText("");
			txtContrasea.setText("");
		}else {
			this.cliente = cliente;
			txtNombreDeUsuario.setText(cliente.getNick());
			txtNombre.setText(cliente.getNombre());
			txtContrasea.setText(cliente.getPass());
			lblModo.setText(cliente.getTipo().toString());
			chckbxHabilitado.setSelected(cliente.isHabilitado());
		}
	}
	
	public Cliente getCliente() {
		if(cliente != null) {
			cliente.setNombre(txtNombre.getText());
			cliente.setPass(txtContrasea.getText()); 
			cliente.setHabilitado(chckbxHabilitado.isSelected());
			return cliente;
		}
		return null;
	}

}
