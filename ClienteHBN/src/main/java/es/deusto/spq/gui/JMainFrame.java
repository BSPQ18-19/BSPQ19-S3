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
	public static final String PRINCIPAL = "PRINCIPAL";
	
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
		
		if(ok) {
			CardLayout cardLayout = new CardLayout(0, 0);
			contentPane.setLayout(cardLayout);
			
			//login
			JLogin login = new JLogin(cardLayout, serviceLocator);
			contentPane.add(login, LOGIN);
			
			// prueba registro
			JRegistro r = new JRegistro(cardLayout, serviceLocator);
			contentPane.add(r, REGISTRO);
			
			JSelectorPerfil selectorPerfil = new JSelectorPerfil(serviceLocator);
			contentPane.add(selectorPerfil, PRINCIPAL);
		}else {
			JLabel lblNoHaSido = new JLabel("No ha sido posible conectarse con el servidor");
			contentPane.add(lblNoHaSido);
		}
	}

}
