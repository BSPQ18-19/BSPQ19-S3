package es.deusto.spq.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import es.deusto.spq.remote.IRmi;
import es.deusto.spq.remote.ServiceLocator;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Font;

public class JLogin extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3289809450257788720L;
	private JTextField textField;
	private JPasswordField passwordField;
	private ServiceLocator serviceLocator;

	/**
	 * Create the panel.
	 * 
	 * @param cardLayout
	 */
	public JLogin(CardLayout cardLayout, ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblIniciarSesin = new JLabel("Iniciar sesión");
		lblIniciarSesin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblIniciarSesin = new GridBagConstraints();
		gbc_lblIniciarSesin.gridwidth = 2;
		gbc_lblIniciarSesin.insets = new Insets(0, 0, 5, 0);
		gbc_lblIniciarSesin.gridx = 0;
		gbc_lblIniciarSesin.gridy = 0;
		add(lblIniciarSesin, gbc_lblIniciarSesin);

		JLabel lblUsuario = new JLabel("Usuario:");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 0;
		gbc_lblUsuario.gridy = 1;
		add(lblUsuario, gbc_lblUsuario);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblContrasea = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.anchor = GridBagConstraints.EAST;
		gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasea.gridx = 0;
		gbc_lblContrasea.gridy = 2;
		add(lblContrasea, gbc_lblContrasea);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 2;
		add(passwordField, gbc_passwordField);

		JButton btnIniciarSesin = new JButton("Iniciar sesión");
		btnIniciarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final String usuario;
				final String contrasenya;

				usuario = textField.getText();
				contrasenya = new String(passwordField.getPassword());

				// Se usa un thread porque es posible que pueda tardar bastante
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {

						btnIniciarSesin.setEnabled(false);
						boolean ok = iniciarSesion(usuario, contrasenya);
						btnIniciarSesin.setEnabled(true);
						
						if (ok) {
							clear();
							JMainFrame.usuario = usuario;
							if (getCliente(usuario)) {
								cardLayout.show(getParent(), JMainFrame.ADMIN);
							} else {
								cardLayout.show(getParent(), JMainFrame.USUARIO);
							}
						} else {
							JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(JLogin.this),
									"Usuario no válido", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				thread.start();
			}
		});
		GridBagConstraints gbc_btnIniciarSesin = new GridBagConstraints();
		gbc_btnIniciarSesin.insets = new Insets(0, 0, 5, 0);
		gbc_btnIniciarSesin.gridx = 1;
		gbc_btnIniciarSesin.gridy = 3;
		add(btnIniciarSesin, gbc_btnIniciarSesin);

		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
				cardLayout.show(getParent(), JMainFrame.REGISTRO);
			}
		});
		GridBagConstraints gbc_btnRegistrarse = new GridBagConstraints();
		gbc_btnRegistrarse.anchor = GridBagConstraints.SOUTHWEST;
		gbc_btnRegistrarse.gridwidth = 2;
		gbc_btnRegistrarse.gridx = 0;
		gbc_btnRegistrarse.gridy = 4;
		add(btnRegistrarse, gbc_btnRegistrarse);

	}

	private void clear() {
		textField.setText("");
		passwordField.setText("");
	}

	public boolean iniciarSesion(String usuario, String contrasenya) {
		try {
			IRmi s = serviceLocator.getService();
			boolean b = s.login(usuario, contrasenya);
			return b;
		} catch (RemoteException e) {
			return false;
		}
	}

	public boolean getCliente(String usuario) {
		try {
			IRmi s = serviceLocator.getService();
			boolean c = s.getTipo(usuario);
			return c;
		} catch (RemoteException e) {
			return false;
		}
	}

}
