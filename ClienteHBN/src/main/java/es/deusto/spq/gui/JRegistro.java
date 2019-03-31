package es.deusto.spq.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.JComboBox;

public class JRegistro extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField txtEdad;
	private JTextField txtContrasea;
	private JTextField txtConfirmarcontrasea;

	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JRegistro registro = new JRegistro();
					registro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the panel.
	 */
	public JRegistro() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblRegistro = new JLabel("Registrarse");
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblRegistro = new GridBagConstraints();
		gbc_lblRegistro.gridwidth = 2;
		gbc_lblRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegistro.gridx = 0;
		gbc_lblRegistro.gridy = 0;
		add(lblRegistro, gbc_lblRegistro);

		JLabel lblUsuario = new JLabel("Usuario:");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 0;
		gbc_lblUsuario.gridy = 1;
		add(lblUsuario, gbc_lblUsuario);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNick = new JLabel("Nick:");
		GridBagConstraints gbc_lblNick = new GridBagConstraints();
		gbc_lblNick.insets = new Insets(0, 0, 5, 5);
		gbc_lblNick.anchor = GridBagConstraints.EAST;
		gbc_lblNick.gridx = 0;
		gbc_lblNick.gridy = 2;
		add(lblNick, gbc_lblNick);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 2;
		add(passwordField, gbc_passwordField);

		JLabel lblEdad = new JLabel("Edad:");
		GridBagConstraints gbc_lblEdad = new GridBagConstraints();
		gbc_lblEdad.anchor = GridBagConstraints.EAST;
		gbc_lblEdad.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdad.gridx = 0;
		gbc_lblEdad.gridy = 3;
		add(lblEdad, gbc_lblEdad);

		txtEdad = new JTextField();
		txtEdad.setText("");
		GridBagConstraints gbc_txtEdad = new GridBagConstraints();
		gbc_txtEdad.insets = new Insets(0, 0, 5, 5);
		gbc_txtEdad.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEdad.gridx = 1;
		gbc_txtEdad.gridy = 3;
		add(txtEdad, gbc_txtEdad);
		txtEdad.setColumns(10);

		JLabel lblContrasea = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.anchor = GridBagConstraints.EAST;
		gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasea.gridx = 0;
		gbc_lblContrasea.gridy = 4;
		add(lblContrasea, gbc_lblContrasea);

		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final String usuario;
				final String contrasenya;

				usuario = textField.getText();
				contrasenya = new String(passwordField.getPassword());

				// Se usa un thread porque es posible que pueda tardar bastante
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {

						btnRegistro.setEnabled(false);
						registrarse();
						btnRegistro.setEnabled(true);
					}
				});
				thread.start();
			}
		});

		txtContrasea = new JTextField();
		txtContrasea.setText("");
		GridBagConstraints gbc_txtContrasea = new GridBagConstraints();
		gbc_txtContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_txtContrasea.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtContrasea.gridx = 1;
		gbc_txtContrasea.gridy = 4;
		add(txtContrasea, gbc_txtContrasea);
		txtContrasea.setColumns(10);

		JLabel lblConfirmarContrasea = new JLabel("Confirmar Contraseña:");
		GridBagConstraints gbc_lblConfirmarContrasea = new GridBagConstraints();
		gbc_lblConfirmarContrasea.anchor = GridBagConstraints.EAST;
		gbc_lblConfirmarContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmarContrasea.gridx = 0;
		gbc_lblConfirmarContrasea.gridy = 5;
		add(lblConfirmarContrasea, gbc_lblConfirmarContrasea);

		txtConfirmarcontrasea = new JTextField();
		txtConfirmarcontrasea.setText("");
		GridBagConstraints gbc_txtConfirmarcontrasea = new GridBagConstraints();
		gbc_txtConfirmarcontrasea.insets = new Insets(0, 0, 5, 5);
		gbc_txtConfirmarcontrasea.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtConfirmarcontrasea.gridx = 1;
		gbc_txtConfirmarcontrasea.gridy = 5;
		add(txtConfirmarcontrasea, gbc_txtConfirmarcontrasea);
		txtConfirmarcontrasea.setColumns(10);

		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario");
		GridBagConstraints gbc_lblTipoDeUsuario = new GridBagConstraints();
		gbc_lblTipoDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoDeUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblTipoDeUsuario.gridx = 0;
		gbc_lblTipoDeUsuario.gridy = 6;
		add(lblTipoDeUsuario, gbc_lblTipoDeUsuario);

		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Usuario");
		comboBox.addItem("Administrador");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 6;
		add(comboBox, gbc_comboBox);

		JButton btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.EAST;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 0;
		gbc_btnCancelar.gridy = 8;
		add(btnCancelar, gbc_btnCancelar);
		GridBagConstraints gbc_btnRegistro = new GridBagConstraints();
		gbc_btnRegistro.gridwidth = 2;
		gbc_btnRegistro.gridx = 1;
		gbc_btnRegistro.gridy = 8;
		add(btnRegistro, gbc_btnRegistro);

	}

	public void registrarse() {
		// TODO:logica

	}

}
