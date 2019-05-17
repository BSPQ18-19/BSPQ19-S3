package es.deusto.spq.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import es.deusto.data.Capitulo;
import es.deusto.data.Temporada;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JTableTemps extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5697954703538447575L;
	private JTabbedPane tabbedPane;
	private JTable table;
	private DefaultTableModel data;
	private static List<Capitulo> listaCaps;
	private JPopupMenu popupMenu;
	private JMenuItem menuItemAdd;
	private JMenuItem menuItemRemove;
	private JMenuItem menuItemRemoveAll;
	private int n;

	private static final String[] nombreColumnas = new String[] { "Título", "Duración", "Descripción", "Valoración" };

	/**
	 * Create the panel.
	 */
	public JTableTemps() {
		setLayout(new BorderLayout(0, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		add(tabbedPane);
	}

	public void anyadirTemporada(Temporada temporada) {
		popupMenu = new JPopupMenu();
		menuItemAdd = new JMenuItem("Añadir fila");
		menuItemRemove = new JMenuItem("Eliminar fila");
		menuItemRemoveAll = new JMenuItem("Eliminar todas las filas");

		menuItemAdd.addActionListener(this);
		menuItemRemove.addActionListener(this);
		menuItemRemoveAll.addActionListener(this);

		popupMenu.add(menuItemAdd);
		popupMenu.add(menuItemRemove);
		popupMenu.add(menuItemRemoveAll);

		n = temporada.getNum();
		List<Capitulo> capitulos = temporada.getCaps();

		Object[][] datos = new Object[capitulos.size()][nombreColumnas.length];
		for (int i = 0; i < capitulos.size(); i++) {
			Capitulo c = capitulos.get(i);
			Object fila[] = datos[i];
			fila[0] = c.getTitulo();
			fila[1] = c.getDuracion();
			fila[2] = c.getDescr();
			fila[3] = c.getValoracion();

		}
		table = new JTable();
		data = new DefaultTableModel(datos, nombreColumnas);
		table.setModel(data);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setComponentPopupMenu(popupMenu);
		table.addMouseListener(new JTableMouseListener(table));
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Temporada " + n, null, scrollPane, null);
		scrollPane.setViewportView(table);
	}

	public void eliminarTodas() {
		tabbedPane.removeAll();
	}

	public static void main(String[] args) {
		Temporada temporada = new Temporada(1, 1);
		listaCaps = temporada.getCaps();
		listaCaps.add(new Capitulo("Cap1", 1, "descr", 10));

		JFrame frame = new JFrame();
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTableTemps jTemporada = new JTableTemps();
		jTemporada.anyadirTemporada(temporada);
		frame.setContentPane(jTemporada);
		frame.setVisible(true);
	}

	public ArrayList<Temporada> getTemps() {
		ArrayList<Temporada> temps = new ArrayList<Temporada>();
		ArrayList<Capitulo> caps = new ArrayList<Capitulo>();
		Temporada p = new Temporada(n);
		for (int i = 0; i < data.getRowCount(); i++) {
			Object o = data.getDataVector().elementAt(i);
			String[] values = o.toString().split(",");
			Capitulo c = new Capitulo(values[0].substring(1, values[0].length()),
					Integer.parseInt(values[1].substring(1, values[1].length())),
					values[2].substring(1, values[2].length()),
					Double.parseDouble(values[3].substring(0, values[3].length() - 1)));
			caps.add(c);
			p.setCaps(caps);
		}
		temps.add(p);
		return temps;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem menu = (JMenuItem) e.getSource();
		if (menu.getText().equals(menuItemAdd.getText())) {
			addNewRow();
		} else if (menu.getText().equals(menuItemRemove.getText())) {
			removeCurrentRow();
		} else if (menu.getText().equals(menuItemRemoveAll.getText())) {
			removeAllRows();
		}
	}

	private void addNewRow() {
		this.data.addRow(new Object[0][data.getRowCount() + 1]);
	}

	private void removeCurrentRow() {
		int selectedRow = table.getSelectedRow();
		data.removeRow(selectedRow);
	}

	private void removeAllRows() {
		int filas = data.getRowCount();
		for (int i = 0; i < filas; i++) {
			data.removeRow(0);
		}
	}
}