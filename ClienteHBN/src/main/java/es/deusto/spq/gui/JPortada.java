package es.deusto.spq.gui;

import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.deusto.data.Contenido;

public class JPortada extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5067679643422662094L;
	private JLabel lblImagen;
	private JLabel lblTitulo;

	public JPortada(Contenido contenido) {
		this();
		setText(contenido.getTitulo());
		setIcon(contenido.getPortada());
	}
	
	/**
	 * Create the panel.
	 */
	public JPortada() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblImagen = new JLabel();
		GridBagConstraints gbc_Imagen = new GridBagConstraints();
		gbc_Imagen.insets = new Insets(0, 0, 5, 0);
		gbc_Imagen.gridx = 0;
		gbc_Imagen.gridy = 0;
		add(lblImagen, gbc_Imagen);
		
		
		lblTitulo = new JLabel("Titulo");
		GridBagConstraints gbc_Titulo = new GridBagConstraints();
		gbc_Titulo.gridx = 0;
		gbc_Titulo.gridy = 1;
		add(lblTitulo, gbc_Titulo);

	}
	
	public void setText(String text) {
		lblTitulo.setText(text);
	}
	
	public String getText() {
		return lblTitulo.getText();
	}
	
	private static final int ALTO = 100;
	private static final int ANCHO = 70;

	
	public void setIcon(ImageIcon icon) {
		
		BufferedImage bufferedImage = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		
		double escalaAncho = ANCHO / (double)icon.getIconWidth();
		double escalaAlto = ALTO / (double)icon.getIconHeight();

		double escala = escalaAlto > escalaAncho ? escalaAncho:escalaAlto;
		
		int ancho = (int) (icon.getIconWidth()*escala);
		int alto = (int) (icon.getIconHeight()*escala);
		
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(icon.getImage(), (ANCHO-ancho)/2, (ALTO-alto)/2, ancho, alto, null);
		g2.dispose();
		
		ImageIcon icon2 = new ImageIcon(bufferedImage);
		
		lblImagen.setIcon(icon2);
	}
	public ImageIcon getIcon() {
		return (ImageIcon) lblImagen.getIcon();
	}
}
