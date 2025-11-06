package gui.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import domain.Athlete;
import domain.Athlete.Genre;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainWindow() {

		// Ejercicio 1
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("JJ.OO. París 2024");
		setSize(640, 480);
		setLocationRelativeTo(null);

		// Ejercicio 5
		List<Athlete> atletas = new ArrayList<>();
		Athlete atleta1 = new Athlete(1, "BOLT, USAIN", Genre.MALE, "España", LocalDate.of(2000, 2, 13));
		Athlete atleta2 = new Athlete(2, "NADAL, RAFAEL", Genre.MALE, "Francia", LocalDate.of(2002, 4, 3));
		Athlete atleta3 = new Athlete(3, "LEDECKY, KATIE", Genre.FEMALE, "Irlanda", LocalDate.of(1999, 6, 1));
		Athlete atleta4 = new Athlete(4, "BILES, SIMONE", Genre.FEMALE, "Polonia", LocalDate.of(1978, 9, 23));
		Athlete atleta5 = new Athlete(5, "KIPCHOGE, ELIUD", Genre.MALE, "Ucrania", LocalDate.of(1998, 2, 4));
		
		atletas.add(atleta1);
		atletas.add(atleta2);
		atletas.add(atleta3);
		atletas.add(atleta4);
		atletas.add(atleta5);
		
		// Creamos el DefaultListModel de Athlete
		DefaultListModel<Athlete> modeloAtletas = new DefaultListModel<Athlete>();
		
		// Añadimos los atletas al modelo
		for (Athlete a : atletas) {
			modeloAtletas.addElement(a);
		}

		JList<Athlete> listaAtletas = new JList<>(modeloAtletas);
		
		// Ejercicio 6
		listaAtletas.setCellRenderer(new AthleteListCellRenderer());
		
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

		// Ejercicio 4
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}

		});
		
		// Añadir funcionalidad salir a salir
		salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrarVentana();
			}
		});

		setVisible(true);
	}

	// Métodos
	private void cerrarVentana() {
		int respuesta = JOptionPane.showConfirmDialog(MainWindow.this, "¿Seguro que desea salir?", "Salir",
				JOptionPane.YES_NO_OPTION);

		if (respuesta == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

}
