package es.deusto.spq.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import es.deusto.data.Contenido;

public class JContenedorResultadosBusqueda extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5974977954770722399L;
	
	private MouseAdapter adapter = new MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			if(e.getButton() == java.awt.event.MouseEvent.BUTTON1 && e.getClickCount() >=2) {
				Object object = e.getSource();
				if(object instanceof JPortada) {
					JPortada jPortada = (JPortada) object;
					processOnContenidoClicked(jPortada.getContenido());
				}
			}
		};
	};

	public JContenedorResultadosBusqueda() {
		this(5);
	}
	
	private JPanel panel;
	private int numColumnas;
	/**
	 * Create the panel.
	 */
	public JContenedorResultadosBusqueda(int numColumnas) {
		this.numColumnas = numColumnas;
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	}

	public void anyadirContenido(Contenido contenidos[]) {
		panel.removeAll();
		int columnas = 0;
		JPanel fila = new JPanel();
		panel.add(fila);
		fila.setLayout(new BoxLayout(fila, BoxLayout.X_AXIS));
		for(Contenido contenido : contenidos) {
			JPortada jPortada = new JPortada(contenido);
			jPortada.addMouseListener(adapter);
			if(columnas < numColumnas) {
				columnas++;
			} else {
				columnas = 0;
				fila = new JPanel();
				panel.add(fila);
				fila.setLayout(new BoxLayout(fila, BoxLayout.X_AXIS));
			}
			fila.add(jPortada);
		}
		panel.revalidate();
		panel.repaint();
	}
	
	private List<ContenidoClickedListener> clickedListeners = new ArrayList<ContenidoClickedListener>();
	
	public void addOnContenidoClicked(ContenidoClickedListener l){
		clickedListeners.add(l);
	}
	
	public void removeOnContenidoClicked(ContenidoClickedListener l){
		clickedListeners.remove(l);
	}
	
	protected void processOnContenidoClicked(Contenido c) {
		for(ContenidoClickedListener l:clickedListeners) {
			l.onContenidoClicked(c);
		}
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 600);
		frame.setLocationRelativeTo(null);
		JContenedorResultadosBusqueda contenedorResultadosBusqueda = new JContenedorResultadosBusqueda(3);
		frame.setContentPane(contenedorResultadosBusqueda);
		frame.setVisible(true);
	}

}
