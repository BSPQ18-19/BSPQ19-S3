package es.deusto.spq.gui;

import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.deusto.data.Contenido;
import es.deusto.data.Serie;

import javax.swing.border.LineBorder;
import java.awt.Color;

public class JPortada extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5067679643422662094L;
	private JLabel lblImagen;
	private JLabel lblTitulo;
	
	private static ImageIcon defaultIcon;
	
	private static final int ALTO = 100;
	private static final int ANCHO = 70;
	{
		
		try {
			BufferedImage image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("portada.png"));
			defaultIcon = new ImageIcon(image);
			defaultIcon = escalar(defaultIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Contenido contenido;
	
	public JPortada(Contenido contenido) {
		this();
		
		setContenido(contenido);
	}
	
	public Contenido getContenido() {
		return contenido;
	}

	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
		setText(contenido.getTitulo());
		ImageIcon icono = contenido.getPortada();
		if(icono == null) {
			icono = defaultIcon;
		}
		setIcon(icono);
	}

	/**
	 * Create the panel.
	 */
	private JPortada() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblImagen = new JLabel();
		lblImagen.setBorder(new LineBorder(new Color(0, 0, 0)));
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
	
	
	private ImageIcon escalar(ImageIcon icon) {
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
		return icon2;
	}
	
	public void setIcon(ImageIcon icon) {
		if(icon != null) {
			lblImagen.setIcon(escalar(icon));
		}else {
			lblImagen.setIcon(defaultIcon);
		}
		
	}
	public ImageIcon getIcon() {
		return (ImageIcon) lblImagen.getIcon();
	}
	
	@Override
	public synchronized void addMouseListener(MouseListener l) {
		super.addMouseListener(l);
	}
	@Override
	public synchronized void removeMouseListener(MouseListener l) {
		super.removeMouseListener(l);
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPortada jPortada = new JPortada(new Contenido() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7510443631821420239L;
			private String titulo = "Titulo";
			@Override
			public void setTitulo(String titulo) {
				this.titulo = titulo;
			}
			
			@Override
			public void setPortada(ImageIcon portada) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setGenero(String genero) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String getTitulo() {
				// TODO Auto-generated method stub
				return titulo;
			}
			
			@Override
			public ImageIcon getPortada() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getGenero() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		frame.setContentPane(jPortada);
		frame.setVisible(true);
	}
}
