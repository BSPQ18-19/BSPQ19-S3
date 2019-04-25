package es.deusto.spq.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JPerfil extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4585459723721533346L;

	private JTextField txtNombre;
	private static ImageIcon icon;

	{
		BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.setColor(Color.GRAY);
		g2d.fillOval(0, 25, 50, 50);
		g2d.fillOval(15, 0, 20, 20);
		icon = new ImageIcon(image);
	}

	private JLabel lblImagen;

	/**
	 * Create the panel.
	 */
	public JPerfil(String nombre) {
		setLayout(new BorderLayout(0, 0));

		lblImagen = new JLabel();
		add(lblImagen, BorderLayout.WEST);

		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setText(nombre);
		add(txtNombre, BorderLayout.CENTER);
		txtNombre.setColumns(10);

		setEditable(false);

		lblImagen.setIcon(icon);

		setBackground(Color.white);
	}

	@Override
	public String toString() {
		return txtNombre.getText();
	}

	public boolean isEditable() {
		return txtNombre.isEditable();
	}

	public void setEditable(boolean b) {
		txtNombre.setEditable(b);
		txtNombre.setOpaque(b);
	}

	@Override
	public void setBackground(Color bg) {
		if (bg != null) {
			super.setBackground(bg);
		} else {
			super.setBackground(Color.WHITE);
		}

	}

	public void setNombre(String nombre) {
		txtNombre.setText(nombre);
	}

	public String getNombre() {
		return txtNombre.getText();
	}
}
