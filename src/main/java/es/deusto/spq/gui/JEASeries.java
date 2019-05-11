package es.deusto.spq.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import com.stackoverflow.WrappingFlowLayout;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JEASeries extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7264693372415173157L;
	

	private JPanel panel;
	private JButton btnAñadir;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroupGeneros = new ButtonGroup();
	
	private WrappingFlowLayout wrappingFlowLayout;

	private JTextField textFieldID;
	private JTextField textFieldTitulo;
	private JTextField textFieldAnho;
	private JTextField textFieldGenero;
	private JTextField textFieldSinopsis;
	private JTextField textFieldTemp;
	private JTextField textFieldCaps;
	private static CardLayout card;
	
	/* ventana donde tenga a elegir si añadir o editar
	 * ventana donde tenga recuadros a rellenar con la caracteristicas de Editar
	 * una vez rellenadas todas se puede añadir
	 * 
	 * si es para editar, que se identifique la serie con id o con el nombre y que luego se cambien los
	 * datos deseados usando el formato de rellenar los huecos
	 */
	
	public JEASeries(CardLayout cd){
		this.card = cd;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{68, 383, 0};
		gridBagLayout.rowHeights = new int[]{206, 0, -49};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
				
				btnAtrs = new JButton("Atrás");
				btnAtrs.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cd.show(getParent(), JMainFrame.ADMIN);
					}
				});
				GridBagConstraints gbc_btnAtrs = new GridBagConstraints();
				gbc_btnAtrs.anchor = GridBagConstraints.EAST;
				gbc_btnAtrs.insets = new Insets(0, 0, 5, 5);
				gbc_btnAtrs.gridx = 0;
				gbc_btnAtrs.gridy = 1;
				add(btnAtrs, gbc_btnAtrs);
		
				panel = new JPanel();
				GridBagConstraints gbc_panel= new GridBagConstraints();
				gbc_panel.insets = new Insets(0, 0, 5, 5);
				gbc_panel.anchor = GridBagConstraints.EAST;
				gbc_panel.fill = GridBagConstraints.VERTICAL;
				gbc_panel.gridx = 1;
				gbc_panel.gridy = 1;
				add(panel, gbc_panel);
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				btnNewButton = new JButton("Eliminar");
				panel.add(btnNewButton);
				
				btnNewButton_1 = new JButton("Editar");
				panel.add(btnNewButton_1);
		
		btnAñadir = new JButton("Añadir");
		buttonGroup.add(btnAñadir);
		GridBagConstraints gbc_btnAñadir = new GridBagConstraints();
		gbc_btnAñadir.anchor = GridBagConstraints.WEST;
		gbc_btnAñadir.gridx = 2;
		gbc_btnAñadir.gridy = 1;
		add(btnAñadir, gbc_btnAñadir);
		
		textFieldCaps = new JTextField("Capitulo");
		GridBagConstraints gbc_rdbtnTextFieldCaps = new GridBagConstraints();
		gbc_rdbtnTextFieldCaps.gridwidth = 2;
		gbc_rdbtnTextFieldCaps.insets = new Insets(0, 0, 125, 5);
		gbc_rdbtnTextFieldCaps.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnTextFieldCaps.gridx = 0;
		gbc_rdbtnTextFieldCaps.gridy = 0;
		add(textFieldCaps, gbc_rdbtnTextFieldCaps);
		
		textFieldTemp = new JTextField("Temporada");
		GridBagConstraints gbc_rdbtnTextFieldTemp = new GridBagConstraints();
		gbc_rdbtnTextFieldTemp.gridwidth = 2;
		gbc_rdbtnTextFieldTemp.insets = new Insets(0, 0, 45, 5);
		gbc_rdbtnTextFieldTemp.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnTextFieldTemp.gridx = 0;
		gbc_rdbtnTextFieldTemp.gridy = 0;
		add(textFieldTemp, gbc_rdbtnTextFieldTemp);
		
		textFieldSinopsis = new JTextField("Sinopsis");
		GridBagConstraints gbc_rdbtnTextFieldSinopsis = new GridBagConstraints();
		gbc_rdbtnTextFieldSinopsis.gridwidth = 2;
		gbc_rdbtnTextFieldSinopsis.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnTextFieldSinopsis.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnTextFieldSinopsis.gridx = 0;
		gbc_rdbtnTextFieldSinopsis.gridy = 0;
		add(textFieldSinopsis, gbc_rdbtnTextFieldSinopsis);
		
		textFieldGenero = new JTextField("Genero");
		GridBagConstraints gbc_rdbtnTextFieldGenero = new GridBagConstraints();
		gbc_rdbtnTextFieldGenero.gridwidth = 2;
		gbc_rdbtnTextFieldGenero.insets = new Insets(0, 0, 85, 5);
		gbc_rdbtnTextFieldGenero.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnTextFieldGenero.gridx = 0;
		gbc_rdbtnTextFieldGenero.gridy = 0;
		add(textFieldGenero, gbc_rdbtnTextFieldGenero);
		
		
		textFieldAnho = new JTextField("Año");
		GridBagConstraints gbc_rdbtnTextFieldAnho = new GridBagConstraints();
		gbc_rdbtnTextFieldAnho.gridwidth = 2;
		gbc_rdbtnTextFieldAnho.insets = new Insets(0, 0, 165, 5);
		gbc_rdbtnTextFieldAnho.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnTextFieldAnho.gridx = 0;
		gbc_rdbtnTextFieldAnho.gridy = 0;
		add(textFieldAnho, gbc_rdbtnTextFieldAnho);

		textFieldTitulo = new JTextField("Titulo");
		GridBagConstraints gbc_rdbtnTextFieldTitulo = new GridBagConstraints();
		gbc_rdbtnTextFieldTitulo.gridwidth = 2;
		gbc_rdbtnTextFieldTitulo.insets = new Insets(0, 0, 205, 5);
		gbc_rdbtnTextFieldTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnTextFieldTitulo.gridx = 0;
		gbc_rdbtnTextFieldTitulo.gridy = 0;
		add(textFieldTitulo, gbc_rdbtnTextFieldTitulo);
		
		textFieldID = new JTextField("Pelicula ID");
		GridBagConstraints gbc_rdbtnTextFieldID = new GridBagConstraints();
		gbc_rdbtnTextFieldID.gridwidth = 2;
		gbc_rdbtnTextFieldID.insets = new Insets(0, 0, 245, 5);
		gbc_rdbtnTextFieldID.fill = GridBagConstraints.BOTH;
		gbc_rdbtnTextFieldID.gridx = 0;
		gbc_rdbtnTextFieldID.gridy = 0;
		add(textFieldID, gbc_rdbtnTextFieldID);
		btnAñadir.addActionListener((e)->processAñadirEvent());
		
	}
	
	
	private ArrayList<EditarListener> editarListeners = new ArrayList<EditarListener>();
	
	public void addEditarListener(EditarListener editarListener){
		editarListeners.add(editarListener);
	}
	
	public boolean removeEditarListener(EditarListener editarListener) {
		return editarListeners.remove(editarListener);
	}
	
	protected void processEditarEvent() {
		String campoDeBusquedaID = textFieldID.getText();
		String campoDeBusquedaTitulo = textFieldID.getText();
		
		String genero = null;
		
		//TODO
		
		for(EditarListener editarListener:editarListeners) {
			editarListener.onBuscar(campoDeBusquedaID, campoDeBusquedaTitulo, false);
		}
	}
	
	private ArrayList<AñadirListener> añadirListeners = new ArrayList<AñadirListener>();
	private JButton btnAtrs;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	
	public void addAñadirListener(AñadirListener añadirListener){
		añadirListeners.add(añadirListener);
	}
	
	public boolean removeAñadirListener(AñadirListener añadirListener) {
		return añadirListeners.remove(añadirListener);
	}
	
	protected void processAñadirEvent() {
		String campoDeBusquedaID = textFieldID.getText();
		String campoDeBusquedaTitulo = textFieldID.getText();
		
		String genero = null;
		
		//TODO
		
		for(AñadirListener añadirListener:añadirListeners) {
			añadirListener.onBuscar(campoDeBusquedaID, campoDeBusquedaTitulo, false);
		}
	}

}
