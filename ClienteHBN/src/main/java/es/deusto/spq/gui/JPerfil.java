package es.deusto.spq.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JPerfil extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4585459723721533346L;

	
	private JTextField txtNombre;

	/**
	 * Create the panel.
	 */
	public JPerfil(String nombre) {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblImagen = new JLabel();
		add(lblImagen, BorderLayout.WEST);
		
		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setText(nombre);
		add(txtNombre, BorderLayout.CENTER);
		txtNombre.setColumns(10);

		setEditable(false);
	}

	@Override
	public String toString() {
		return txtNombre.getText();
	}
	
	public boolean isEditable(){
		return txtNombre.isEditable();
	}
	
	public void setEditable(boolean b) {
		txtNombre.setEditable(b);
		txtNombre.setOpaque(b);
	}
}
