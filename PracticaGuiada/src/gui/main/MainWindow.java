package gui.main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainWindow() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("JJ.OO. París 2024");
		setSize(640, 480);
		setLocationRelativeTo(null);

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
		
		setVisible(true);
	}

}
