package es.deusto.spq.gui;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.data.Pelicula;
import es.deusto.data.Perfil;
import es.deusto.data.Perfil.ControlParental;
import es.deusto.spq.remote.ServiceLocator;
import javax.swing.JLabel;

public class JMainFrame extends JFrame {

	
	/** \mainpage Home
	 *
	 * \section intro_sec ¿Qué es HBN?
	 *
	 * HBN es un servicio de streaming que permite a nuestros clientes ver una amplia variedad de series, películas, documentales y otros contenidos galardonados en miles de dispositivos conectados a Internet. Con HBN, puedes disfrutar de contenido ilimitado sin necesidad de ver un solo anuncio. ¡Siempre hay algo nuevo que descubrir, y cada mes se añaden más series y películas!
	 **  Con el registro en HBN podras:
	 *   	- crear diferentes perfiles:
	 *  	- Tener una lista de favoritos
	 *  	- Valorar el contenido HBN
	 *  	- Y muchas mas cosas...
	 *  @see JMainFrame
	 * \section install_sec Uso
	 *
	 * \subsection step1 Paso 1:Registrarse en la aplicación
	 * \subsection step2 Paso 2:Logearse en la aplicación
	 * \subsection step3 Paso 3:Disfrutar
	 */
	/** \brief Brief description.
	 *         Brief description continued.
	 *
	 *  Detailed description starts here.
	 */
	
	private static final long serialVersionUID = 8115163704957507722L;
	/** Panel principal */
	private JPanel contentPane;
	public static String usuario;

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
	/** Variable final para la ventana del Login */
	public static final String LOGIN = "LOGIN";
	/** Variable final para la ventana del Registro */
	public static final String REGISTRO = "REGISTRO";
	/** Variable final para la ventana de Perfiles */
	public static final String PERFILES = "PERFILES";
	/** Variable final para la ventana del usuario */
	public static final String USUARIO = "USUARIO";
	/** Variable final para la ventana del Admin */
	public static final String ADMIN = "ADMIN";
	/** Variable final para la ventana del Control parental */
	public static final String PARENTAL = "PARENTAL";
	/** Variable final para la ventana de las Películas*/
	public static final String PELICULAS = "PELICULAS";
	/** Variable final para la ventana de Películas */
	public static final String JP = "JP";
	/** Variable final para la ventana del las Series*/
	public static final String SERIES = "SERIES";
	/** Variable final para la ventana del Panel de busqueda para el usuario */
	public static final String PANEL_BUSQUEDA_USUARIO = "PANEL_BUSQUEDA_USUARIO";
	/** Variable final para la ventana del Panel de busqueda para el Admin  */
	public static final String PANEL_BUSQUEDA_ADMIN = "PANEL_BUSQUEDA_ADMIN";
	/** Variable final para la ventana del Perfil */
	public static Perfil p =new Perfil("Satndar","1-3-1990", ControlParental.FALSE);
	public static Pelicula peli= new Pelicula("Narnia", 2007, 90, "Fantasia", 7, "Las crónicas de Narnia: El león, la bruja y el armario es una espectacular película basada en el clásico literario del popular escritor C.S. Lewis.", 2,1);
	
	/**Create the frame.*/
	public JMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		ServiceLocator serviceLocator = new ServiceLocator();
		boolean ok = serviceLocator.setService();

		if (ok) {
			CardLayout cardLayout = new CardLayout(0, 0);
			contentPane.setLayout(cardLayout);

			/**Carga el login.*/
			JLogin login = new JLogin(cardLayout, serviceLocator);
			contentPane.add(login, LOGIN);

			/**Carga el registro.*/
			JRegistro r = new JRegistro(cardLayout, serviceLocator);
			contentPane.add(r, REGISTRO);

			/**Carga el selector de perfil.*/
			JSelectorPerfil selectorPerfil = new JSelectorPerfil(cardLayout, serviceLocator);
			contentPane.add(selectorPerfil, PERFILES);

			/**Carga el control parental*/
			JControlParental controlParental = new JControlParental(cardLayout, serviceLocator);
			controlParental.usuario = usuario;
			contentPane.add(controlParental, PARENTAL);

			/**Carga la ventana principal del Usuario.*/
			JUsuario ventanaUsuario = new JUsuario(cardLayout);
			contentPane.add(ventanaUsuario, USUARIO);

			/**Carga la ventana principal del Administrador.*/
			JAdmin ventanaAdmin = new JAdmin(cardLayout);
			contentPane.add(ventanaAdmin, ADMIN);
			
			/**Carga Panel de busqueda del Usuario.*/
			JPanelBusquedaUsuario panelBusqueda = new JPanelBusquedaUsuario(cardLayout, serviceLocator);
			contentPane.add(panelBusqueda, PANEL_BUSQUEDA_USUARIO);
			
			/**Carga Panel de busqueda del Administrador.*/
			JPanelBusquedaAdmin panelBusquedaAdmin = new JPanelBusquedaAdmin(cardLayout, serviceLocator);
			contentPane.add(panelBusquedaAdmin, PANEL_BUSQUEDA_ADMIN);
			
			/**Carga Panel de Películas.*/
			JEAPeliculas pelis = new JEAPeliculas(cardLayout);
			contentPane.add(pelis, PELICULAS);
			
			/**Carga Panel de Series.*/
			JEASeries series = new JEASeries(cardLayout);
			contentPane.add(series, SERIES);
			
			/**Carga Panel de Película.*/
			JPelicula jp= new JPelicula(cardLayout,peli,serviceLocator);
			contentPane.add(jp,JP);

		} else {
			JLabel lblNoHaSido = new JLabel("No ha sido posible conectarse con el servidor");
			contentPane.add(lblNoHaSido);
		}
	}

}
