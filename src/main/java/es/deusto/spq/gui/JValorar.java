package es.deusto.spq.gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JValorar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int valoracion;
	public boolean valorado=false;
	public JValorar(){
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton = new JButton("Valorar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] options = {"0", "1", "2", "3","4","5"};
				int seleccion = JOptionPane.showOptionDialog(null, "¿Qué nota le pondrías a esta película?", "Valoración ", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				System.out.println(seleccion);
				valoracion=seleccion;
				valorado=true;
			}
		});
		add(btnNewButton);
		
		
		
	}

}
