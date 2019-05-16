package es.deusto.spq.gui;

import java.awt.CardLayout;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import es.deusto.spq.remote.IRmi;
import es.deusto.spq.remote.ServiceLocator;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;

public class JRegistro extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	private JTextField textField;
	private JPasswordField pwdContrasea;
	private JPasswordField pwdConfirmarcontrasea;
	private JTextField txtNick;
	private ServiceLocator serviceLocator;

	/**
	 * Create the panel.
	 */
	public JRegistro(CardLayout cardLayout, ServiceLocator serviceLocator) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.serviceLocator = serviceLocator;
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 48, 0, 0, 0, 0, 0, 0 };
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
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				// Prohibido número y caracteres especiales
				if (!Character.isAlphabetic(c)) {
					getToolkit().beep();
					ke.consume();
//					JOptionPane.showMessageDialog(null, "Caracter no permitido", "Error", JOptionPane.WARNING_MESSAGE);

				}

			}
		});
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

		txtNick = new JTextField();
		txtNick.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();

				if ((!Character.isDigit(c) && !Character.isAlphabetic(c))) {
					getToolkit().beep();
					e.consume();
					//JOptionPane.showMessageDialog(null, "Caracter no permitido", "Error", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		txtNick.setText("");
		GridBagConstraints gbc_txtNick = new GridBagConstraints();
		gbc_txtNick.insets = new Insets(0, 0, 5, 5);
		gbc_txtNick.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNick.gridx = 1;
		gbc_txtNick.gridy = 2;
		add(txtNick, gbc_txtNick);
		txtNick.setColumns(10);

		JLabel lblEdad = new JLabel("Fecha nac:");
		GridBagConstraints gbc_lblEdad = new GridBagConstraints();
		gbc_lblEdad.anchor = GridBagConstraints.EAST;
		gbc_lblEdad.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdad.gridx = 0;
		gbc_lblEdad.gridy = 3;
		add(lblEdad, gbc_lblEdad);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Usuario");
		comboBox.addItem("Administrador");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 6;
		add(comboBox, gbc_comboBox);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 50, 15);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// fecha

		SimpleDateFormat model1 = new SimpleDateFormat("dd-MM-yyyy");

		JSpinner spinner = new JSpinner(new SpinnerDateModel());
		spinner.setEditor(new JSpinner.DateEditor(spinner, model1.toPattern()));
		panel.add(spinner);

		JLabel label = new JLabel("/");
		panel.add(label);

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
				char[] pass = pwdContrasea.getPassword();
				// variables
				final String usuario = textField.getText();
				final String passString = new String(pass);
				final String nick = txtNick.getText();
				final String fecha_nac = spinner.getValue().toString();
				final int tipo = comboBox.getSelectedIndex();

				// Se usa un thread porque es posible que pueda tardar bastante
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						if (!textField.getText().isEmpty() && !txtNick.getText().isEmpty()
								&& pwdContrasea.getPassword().length > 0
								&& pwdConfirmarcontrasea.getPassword().length > 0) {
							if (confirmarContaseña(pwdContrasea.getPassword(), pwdConfirmarcontrasea.getPassword())) {

								btnRegistro.setEnabled(false);
								try {
									registrarse(usuario, nick, passString, fecha_nac, tipo);
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								btnRegistro.setEnabled(true);
								System.out.println(usuario + nick + passString + fecha_nac);
								cardLayout.show(getParent(), JMainFrame.LOGIN);
								JMainFrame.usuario = usuario;
								clear();
							} else {
								JOptionPane.showMessageDialog(null, "Debe de coincidir la contraseña", "Error",
										JOptionPane.WARNING_MESSAGE);
							}

						} else {
							JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos", "Error",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				thread.start();
			}
		});

		pwdContrasea = new JPasswordField();
		pwdContrasea.setText("");
		GridBagConstraints gbc_pwdContrasea = new GridBagConstraints();
		gbc_pwdContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_pwdContrasea.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdContrasea.gridx = 1;
		gbc_pwdContrasea.gridy = 4;
		add(pwdContrasea, gbc_pwdContrasea);

		JLabel lblConfirmarContrasea = new JLabel("Confirmar Contraseña:");
		GridBagConstraints gbc_lblConfirmarContrasea = new GridBagConstraints();
		gbc_lblConfirmarContrasea.anchor = GridBagConstraints.EAST;
		gbc_lblConfirmarContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmarContrasea.gridx = 0;
		gbc_lblConfirmarContrasea.gridy = 5;
		add(lblConfirmarContrasea, gbc_lblConfirmarContrasea);

		pwdConfirmarcontrasea = new JPasswordField();
		pwdConfirmarcontrasea.setText("");
		GridBagConstraints gbc_pwdConfirmarcontrasea = new GridBagConstraints();
		gbc_pwdConfirmarcontrasea.insets = new Insets(0, 0, 5, 5);
		gbc_pwdConfirmarcontrasea.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdConfirmarcontrasea.gridx = 1;
		gbc_pwdConfirmarcontrasea.gridy = 5;
		add(pwdConfirmarcontrasea, gbc_pwdConfirmarcontrasea);

		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario");
		GridBagConstraints gbc_lblTipoDeUsuario = new GridBagConstraints();
		gbc_lblTipoDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoDeUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblTipoDeUsuario.gridx = 0;
		gbc_lblTipoDeUsuario.gridy = 6;
		add(lblTipoDeUsuario, gbc_lblTipoDeUsuario);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
				cardLayout.show(getParent(), JMainFrame.LOGIN);

			}
		});
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

	public boolean confirmarContaseña(char[] j1, char[] j2) {

		boolean valor = true;
		int puntero = 0;
		if (j1.length != j2.length) {
			valor = false;
		} else {
			while ((valor) && (puntero < j1.length)) {
				if (j1[puntero] != j2[puntero]) {
					valor = false;
				}
				puntero++;
			}
		}
		return valor;

	}

	public void registrarse(String usuario, String nick, String pass, String fecha_nac, int tipo)
			throws RemoteException {

		System.out.println(serviceLocator);
		IRmi s = serviceLocator.getService();
		s.registrarse(usuario, nick, pass, fecha_nac, tipo);

	}
	
	public void clear() {
		textField.setText("");
		txtNick.setText("");
		pwdContrasea.setText("");
		pwdConfirmarcontrasea.setText("");
	}
}
