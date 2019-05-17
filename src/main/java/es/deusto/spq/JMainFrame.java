package es.deusto.spq;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.rmi.Naming;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import es.deusto.spq.remote.IRmi;
import es.deusto.spq.remote.Rmi;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class JMainFrame extends JFrame implements IMessagePrinter{

	private static final String HOST = "127.0.0.1";
	private static final String PORT = "1099";
	private static final String SERVER_NAME = "ServidorHBN";
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8115163704957507722L;
	private JPanel contentPane;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMainFrame frame = getInstance();
					frame.setVisible(true);
					if (System.getSecurityManager() == null) {
						System.setSecurityManager(new SecurityManager());
					}

					String name = "//" + HOST + ":" + PORT + "/" + SERVER_NAME;

					try {		
						IRmi objServer = new Rmi(frame);
						Naming.rebind(name, objServer);
						frame.println("* Server '" + name + "' active and waiting...", TipoMensaje.INFO);
					} catch (Exception e) {
						frame.println("- Exception running the server: " + e.getMessage(), TipoMensaje.ERROR);
//						for(StackTraceElement trace:e.getStackTrace()) {
//							println(trace.toString());
//						}
						frame.println(e.toString(), TipoMensaje.ERROR);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	private static JMainFrame ventana = new JMainFrame();
	
	public static JMainFrame getInstance() {
		return ventana;
		
	}
	private static Logger logger = null;
	
	private static Logger getLogger() {
		if(logger == null) {
			BasicConfigurator.configure();
			logger = Logger.getLogger(JMainFrame.class);
		}
		return logger;
	}

	/**
	 * Create the frame.
	 */
	private JMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
	}
	
	public void println(String mensaje) {
		println(mensaje, TipoMensaje.INFO);
	}
	
	public void println(String mensaje, TipoMensaje tipoMensaje) {
		ventana.textArea.setText(ventana.textArea.getText()+mensaje+"\n");
		if(tipoMensaje.equals(TipoMensaje.INFO)) {
			getLogger().info(mensaje);
		}else {
			getLogger().error(mensaje);
		}
		
	}

}
