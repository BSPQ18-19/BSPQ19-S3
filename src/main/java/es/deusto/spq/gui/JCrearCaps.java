package es.deusto.spq.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import es.deusto.data.Capitulo;
import es.deusto.data.Temporada;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class JCrearCaps extends JDialog {
	private JTabbedPane tb = new JTabbedPane();
	private JContenedorCrearCaps panel;
	private JContenedorCrearCaps cont;
	private ArrayList<Temporada> temps = new ArrayList<>();
	private boolean aceptado = false;
	private int n = 1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			JCrearCaps dialog = new JCrearCaps();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public JCrearCaps(boolean modal) {
		this();
		setModal(modal);
	}

	public JCrearCaps() {

		setBounds(100, 100, 450, 300);

		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAadirTemporada = new JButton("Añadir temporada");
				btnAadirTemporada.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						n++;
						cont = new JContenedorCrearCaps(n);
						tb.addTab("Temporada " + n, cont);
						getContentPane().add(tb, BorderLayout.CENTER);
					}
				});
				buttonPane.add(btnAadirTemporada);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton okButton = new JButton("Guardar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						temps = new ArrayList<Temporada>(panel.getTemporadas());
						System.out.println(temps.size());
//						Comprobación	
//						System.out.println();
//						for(Temporada p : temps) {
//							System.out.println(p.getNum());
//							for(Capitulo c : p.getCaps()) {
//								System.out.println(c.getTitulo());
//							}
//						}

						panel.clearData();
						aceptado = true;
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			panel = new JContenedorCrearCaps(n);
			tb.add("Temporada " + n, panel);
			getContentPane().add(tb, BorderLayout.CENTER);
		}

	}

	public static ArrayList<Temporada> getTemps() {
		JCrearCaps x = new JCrearCaps(true);
		x.setVisible(true);
		if(x.aceptado == true) {
			ArrayList<Temporada> t = new ArrayList<Temporada>(x.temps);
			x.temps.clear();
			return t;
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(getTemps());
	}

}
