package gui.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainWindow() {
		
		// Ejercicio 1
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("JJ.OO. París 2024");
		setSize(640, 480);
		setLocationRelativeTo(null);
		
		// Ejercicio 2
		String[] atletas = new String[50];

		for (int i = 0; i < atletas.length; i++) {
			atletas[i] = "Atleta " + (i + 1);
		}

		JList<String> listaAtletas = new JList<String>(atletas);
		JScrollPane ScrollPane = new JScrollPane(listaAtletas);

		ScrollPane.setPreferredSize(new Dimension(200, 0));
		this.add(ScrollPane, BorderLayout.WEST);
		
		JTabbedPane pestañas = new JTabbedPane();
		JPanel panelDatos = new JPanel();
		JPanel panelMedallas = new JPanel();
		
		pestañas.add(panelDatos, "Datos");
		pestañas.add(panelMedallas, "Medallas");
		
		this.add(pestañas, BorderLayout.CENTER);
		
		// Ejercicio 3
		JMenuBar barraMenu = new JMenuBar();
		JMenu menu = new JMenu("Fichero");
		JMenuItem nuevoAtleta = new JMenuItem("Nuevo atleta...");
		JMenuItem importar = new JMenuItem("Importar...");
		JMenuItem exportar = new JMenuItem("Exportar...");
		JMenuItem salir = new JMenuItem("Salir");
		
		// Teclas rápidas
		menu.setMnemonic(KeyEvent.VK_F);
		nuevoAtleta.setMnemonic(KeyEvent.VK_N);
		importar.setMnemonic(KeyEvent.VK_I);
		exportar.setMnemonic(KeyEvent.VK_E);
		salir.setMnemonic(KeyEvent.VK_S);
		
		// Añadir los componentes
		barraMenu.add(menu);
		menu.add(nuevoAtleta);
		menu.addSeparator();
		menu.add(importar);
		menu.add(exportar);
		menu.addSeparator();
		menu.add(salir);
		setJMenuBar(barraMenu);

		setVisible(true);
	}

}
