package gui.main;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import domain.Athlete;

public class AthleteListCellRenderer extends DefaultListCellRenderer{

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		// Obtenemos el JLabel base que el renderer por defecto usa para mostrar la celda
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		// Casteamos value a Athlete y lo guardamos en una variable para poder acceder a sus atributos
		Athlete atleta = (Athlete) value;
		
		// Mostramos solo el nombre completo del atleta 
		label.setText(atleta.getName());
		
		return label;
	}
	
	

}
