package es.deusto.spq.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JCrearCaps extends JDialog {
	private JTextField textField;
	private JSpinner spinner;
	private JTextField textField_1;
	private JTabbedPane tb = new JTabbedPane();
	private int n = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JCrearCaps dialog = new JCrearCaps();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
						JContenedorCrearCaps cont = new JContenedorCrearCaps();
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
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JContenedorCrearCaps panel = new JContenedorCrearCaps();
			tb.add("Temporada " + n, panel);
			getContentPane().add(tb, BorderLayout.CENTER);	
		}
	}

}
