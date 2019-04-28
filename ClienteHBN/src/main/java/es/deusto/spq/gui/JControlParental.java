package es.deusto.spq.gui;

import java.awt.CardLayout;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class JControlParental extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 888742398776124671L;

	private JPasswordField pwdVsvdfv;
	public String usuario = null;
	private JTextField textField;
	public boolean tipo = true;

	public JControlParental(CardLayout cardLayout) {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 251 };
		gridBagLayout.rowHeights = new int[] { 0, 46, 0, 48, 0, 30, 26, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNombreActual = new JLabel("Nick actual:");
		GridBagConstraints gbc_lblNombreActual = new GridBagConstraints();
		gbc_lblNombreActual.anchor = GridBagConstraints.EAST;
		gbc_lblNombreActual.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreActual.gridx = 1;
		gbc_lblNombreActual.gridy = 1;
		add(lblNombreActual, gbc_lblNombreActual);

		textField = new JTextField(usuario);// Nombre de usuario
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);

		JButton btnCambiar = new JButton("Cambiar");
		GridBagConstraints gbc_btnCambiar = new GridBagConstraints();
		gbc_btnCambiar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCambiar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCambiar.gridx = 1;
		gbc_btnCambiar.gridy = 2;
		add(btnCambiar, gbc_btnCambiar);
		btnCambiar.setEnabled(false);
		if (!textField.getText().equals(usuario)) {
			btnCambiar.setEnabled(true);
		}

		JLabel lblPass = new JLabel("Pass:");
		GridBagConstraints gbc_lblPass = new GridBagConstraints();
		gbc_lblPass.anchor = GridBagConstraints.WEST;
		gbc_lblPass.insets = new Insets(0, 0, 5, 5);
		gbc_lblPass.gridx = 1;
		gbc_lblPass.gridy = 3;
		add(lblPass, gbc_lblPass);

		pwdVsvdfv = new JPasswordField();
		GridBagConstraints gbc_pwdVsvdfv = new GridBagConstraints();
		gbc_pwdVsvdfv.insets = new Insets(0, 0, 5, 5);
		gbc_pwdVsvdfv.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdVsvdfv.gridx = 2;
		gbc_pwdVsvdfv.gridy = 3;
		add(pwdVsvdfv, gbc_pwdVsvdfv);

		JButton btnCambiarPass = new JButton("Cambiar Pass");
		GridBagConstraints gbc_btnCambiarPass = new GridBagConstraints();
		gbc_btnCambiarPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCambiarPass.insets = new Insets(0, 0, 5, 5);
		gbc_btnCambiarPass.gridx = 1;
		gbc_btnCambiarPass.gridy = 4;
		add(btnCambiarPass, gbc_btnCambiarPass);

		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cardLayout.show(getParent(), JMainFrame.USUARIO);

			}
		});
		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAtras.insets = new Insets(0, 0, 5, 5);
		gbc_btnAtras.gridx = 1;
		gbc_btnAtras.gridy = 6;
		add(btnAtras, gbc_btnAtras);

		if (tipo) {
			JButton btnActivarControlParental = new JButton("Activar Control Parental");
			GridBagConstraints gbc_btnActivarControlParental = new GridBagConstraints();
			gbc_btnActivarControlParental.fill = GridBagConstraints.VERTICAL;
			gbc_btnActivarControlParental.anchor = GridBagConstraints.EAST;
			gbc_btnActivarControlParental.insets = new Insets(0, 0, 5, 5);
			gbc_btnActivarControlParental.gridx = 2;
			gbc_btnActivarControlParental.gridy = 6;
			add(btnActivarControlParental, gbc_btnActivarControlParental);
			
			JButton btnInfo = new JButton("i");
			btnInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null,
							"El mando a distancia es uno de los objetos más deseados del salón, también para los más peques.\n Cogen el mando, empiezan a pulsar botones y aparece en la tele una película poco adecuada, seguro que te ha pasado.n Mediante el control parental, solo apareceran las películas o series apropiadas para determinada edad. ",
							"Control parental", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			
			GridBagConstraints gbc_btnInfo = new GridBagConstraints();
			gbc_btnInfo.insets = new Insets(0, 0, 5, 0);
			gbc_btnInfo.gridx = 3;
			gbc_btnInfo.gridy = 6;
			add(btnInfo, gbc_btnInfo);
		}
	}
}
