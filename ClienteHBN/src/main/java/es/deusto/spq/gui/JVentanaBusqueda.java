package es.deusto.spq.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class JVentanaBusqueda extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3020147109816426232L;

	/**
	 * Create the panel.
	 */
	public JVentanaBusqueda() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JBarraBusqueda barraBusqueda = new JBarraBusqueda();
		GridBagConstraints gbc_barraBusqueda = new GridBagConstraints();
		gbc_barraBusqueda.fill = GridBagConstraints.BOTH;
		gbc_barraBusqueda.gridx = 0;
		gbc_barraBusqueda.gridy = 0;
		add(barraBusqueda, gbc_barraBusqueda);
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JVentanaBusqueda jVentanaPrincipalU = new JVentanaBusqueda();
		frame.setContentPane(jVentanaPrincipalU);
		frame.setSize(200,200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
	}

}
