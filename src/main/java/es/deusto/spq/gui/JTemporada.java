package es.deusto.spq.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import es.deusto.data.Capitulo;
import es.deusto.data.Temporada;

public class JTemporada extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5697954703538447575L;
	private JTabbedPane tabbedPane;
	
	private static final String[] nombreColumnas = new String[] {"Título", "Duración", "Edad recomendada", "Descripción", "Valoración"};
	/**
	 * Create the panel.
	 */
	public JTemporada() {
		setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
	}
	
	public void anyadirTemporada(Temporada temporada) {
		int n = temporada.getNum();
		List<Capitulo> capitulos = temporada.getCaps();
		
		Object[][] datos = new Object[capitulos.size()][nombreColumnas.length];
		for(int i = 0; i < capitulos.size(); i++) {
			Capitulo c = capitulos.get(i);
			Object fila[] = datos[i];
			fila[0] = c.getTitulo();
			fila[1] = c.getDuracion();
			fila[2] = c.getDescr();
			fila[3] = c.getValoracion();
			
			
		}
		JTable table = new JTable();
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			datos,
			nombreColumnas
		));
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Temporada "+n, null, scrollPane, null);
		
		scrollPane.setViewportView(table);
	}
	
	public void eliminarTodas() {
		tabbedPane.removeAll();
	}
	
	public static void main(String[] args) {
		Temporada temporada = new Temporada(1,1);
		List<Capitulo> listaCaps = temporada.getCaps();
		listaCaps.add(new Capitulo("Cap1", 1, "descr", 10));
		
		
		JFrame frame = new JFrame();
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTemporada jTemporada = new JTemporada();
		jTemporada.anyadirTemporada(temporada);
		frame.setContentPane(jTemporada);
		frame.setVisible(true);
				
	}
}
