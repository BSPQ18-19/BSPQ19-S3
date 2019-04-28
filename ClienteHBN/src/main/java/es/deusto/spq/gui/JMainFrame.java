package es.deusto.spq.gui;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.spq.remote.ServiceLocator;
import javax.swing.JLabel;

public class JMainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8115163704957507722L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMainFrame frame = new JMainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static final String LOGIN = "LOGIN";
	public static final String REGISTRO = "REGISTRO";
	public static final String PERFILES = "PERFILES";
	public static final String USUARIO = "USUARIO";
	public static final String ADMIN = "ADMIN";
	public static final String PARENTAL = "PARENTAL";
	static String usuario;

	/**
	 * Create the frame.
	 */
	public JMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		ServiceLocator serviceLocator = new ServiceLocator();
		boolean ok = serviceLocator.setService();

		if (ok) {
			CardLayout cardLayout = new CardLayout(0, 0);
			contentPane.setLayout(cardLayout);

			// login
			JLogin login = new JLogin(cardLayout, serviceLocator);
			contentPane.add(login, LOGIN);

			// registro
			JRegistro r = new JRegistro(cardLayout, serviceLocator);
			contentPane.add(r, REGISTRO);

			// selector de Perfiles
			JSelectorPerfil selectorPerfil = new JSelectorPerfil(cardLayout, serviceLocator);
			contentPane.add(selectorPerfil, PERFILES);

			// control parental
			JControlParental controlParental = new JControlParental(cardLayout);
			contentPane.add(controlParental, PARENTAL);

			// ventana principal usuario
			JUsuario ventanaUsuario = new JUsuario(cardLayout);
			contentPane.add(ventanaUsuario, USUARIO);

			// ventana principal administrador
			JAdmin ventanaAdmin = new JAdmin(cardLayout);
			contentPane.add(ventanaAdmin, ADMIN);

		} else {
			JLabel lblNoHaSido = new JLabel("No ha sido posible conectarse con el servidor");
			contentPane.add(lblNoHaSido);
		}
	}

}
