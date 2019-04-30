package es.deusto.spq.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import es.deusto.data.Perfil;
import es.deusto.data.Perfil.ControlParental;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class JCrearPerfil extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JSpinner spinner;
	private boolean aceptado = false;
	
	/**
	 * Create the dialog.
	 */
	public JCrearPerfil(boolean modal) {
		initComponents();
		setModal(modal);
	}
	
	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JLabel lblNewLabel = new JLabel("Nick");
			lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				textField = new JTextField();
				panel.add(textField);
				textField.setColumns(10);
			}
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fecha nac.");
			lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				SimpleDateFormat model1 = new SimpleDateFormat("dd-MM-yyyy");

				spinner = new JSpinner(new SpinnerDateModel());
				spinner.setEditor(new JSpinner.DateEditor(spinner, model1.toPattern()));
				panel.add(spinner);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Crear");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aceptado = true;
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public static Perfil getPerfil() {
		JCrearPerfil x = new JCrearPerfil(true);
		x.setVisible(true);
		if (x.aceptado == true) {
			String nick = x.textField.getText();
			String fecha = x.spinner.getValue().toString();
			Perfil nuevoPerfil = new Perfil(nick, fecha, ControlParental.FALSE);
			return nuevoPerfil;
		}
		return null;
	}
	

}
