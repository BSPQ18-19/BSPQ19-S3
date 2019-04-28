package es.deusto.spq.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JAdmin extends JPanel {

	/**
	 * Create the panel.
	 */
	public JAdmin(CardLayout cd) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Gesti\u00F3n", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton btnUsers = new JButton("Usuarios");
		panel.add(btnUsers);
		
		JButton btnPel = new JButton("Películas");
		panel.add(btnPel);
		
		JButton btnSer = new JButton("Series");
		panel.add(btnSer);
		
		JButton btnConf = new JButton("Ajustes de la cuenta");
		panel.add(btnConf);
		
		JPanel panelSuperior = new JPanel();
		add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new BorderLayout(0, 0));

		JBarraBusqueda barra = new JBarraBusqueda();
		panelSuperior.add(barra, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panelSuperior.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton button = new JButton("Volver");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cd.show(getParent(), JMainFrame.LOGIN);
			}
		});
		panel_1.add(button, BorderLayout.NORTH);
	}

}
