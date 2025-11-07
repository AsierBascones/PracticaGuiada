package gui.main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import domain.Athlete;
import domain.Athlete.Genre;

class FormDataNotValid extends Exception {

	private static final long serialVersionUID = 1L;

	public FormDataNotValid(String msg) {
		super(msg);
	}
}

public class AthleteFormPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public JFormattedTextField codigo;
	public JTextField nombreTexto;
	public JFormattedTextField fecha;
	public JRadioButton hombre;
	public JRadioButton mujer;
	public JComboBox<String> selectorPais;

	public AthleteFormPanel(List<String> paises) throws ParseException {

		this.setLayout(new GridLayout(6, 1, 5, 5));
		Dimension tamañoLabel = new Dimension(80, 20);

		// Código
		JPanel panelCodigo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel cod = new JLabel("Código");

		MaskFormatter formatterCodigo = null;
		formatterCodigo = new MaskFormatter("######");
		codigo = new JFormattedTextField(formatterCodigo);

		codigo.setPreferredSize(tamañoLabel);

		panelCodigo.add(cod);
		panelCodigo.add(codigo);

		// Nombre
		JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
		nombreTexto = new JTextField(20);
		JLabel nom = new JLabel("Nombre");

		panelNombre.add(nom);
		panelNombre.add(nombreTexto);

		// Nacimiento
		JPanel panelNacimiento = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel nac = new JLabel("Nacimiento");

		MaskFormatter formatterFecha = null;
		formatterFecha = new MaskFormatter("##/##/####"); // dd/MM/yyyy
		fecha = new JFormattedTextField(formatterFecha);

		fecha.setPreferredSize(tamañoLabel);
		panelNacimiento.add(nac);
		panelNacimiento.add(fecha);

		// Género
		JPanel panelGenero = new JPanel(new FlowLayout(FlowLayout.CENTER));
		hombre = new JRadioButton("Hombre");
		mujer = new JRadioButton("Mujer");

		ButtonGroup grupoBotones = new ButtonGroup();
		grupoBotones.add(hombre);
		grupoBotones.add(mujer);

		panelGenero.add(hombre);
		panelGenero.add(mujer);

		JPanel panelPais = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pais = new JLabel("País");
		selectorPais = new JComboBox<>();

		for (String p : paises) {
			selectorPais.addItem(p);
		}

		panelPais.add(pais);
		panelPais.add(selectorPais);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton botonDatos = new JButton("Obtener Datos");
		JButton botonHabDes = new JButton("Habilitar/Deshabilitar");

		panelBotones.add(botonDatos);
		panelBotones.add(botonHabDes);

		// Añadimos todos los paneles al panel principal
		this.add(panelCodigo);
		this.add(panelNombre);
		this.add(panelGenero);
		this.add(panelNacimiento);
		this.add(panelPais);
		this.add(panelBotones);

		botonDatos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Athlete a = getAthlete();
					System.out.println("Atleta obtenido correctamente:");
					System.out.println(a);
				} catch (FormDataNotValid ex) {
					System.err.println("ERROR: " + ex.getMessage());
				}
			}
		});
		
		botonHabDes.addActionListener(e -> {
	        boolean estadoActual = isEditable();
	        setEditable(!estadoActual);
	        System.out.println("Modo edición -> " + !estadoActual);
	    });
	}
	
	public static void main(String[] args) throws ParseException {
		JFrame ventana = new JFrame("Atleta");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(400, 300);
		ventana.setLocationRelativeTo(null);

		List<String> paises = List.of("España", "Francia", "Italia", "Alemania", "Reino Unido");

		AthleteFormPanel panel = new AthleteFormPanel(paises);

		Athlete atleta1 = new Athlete(111111, "BOLT, USAIN", Genre.MALE, "España", LocalDate.of(2000, 2, 13));

		panel.setAthlete(atleta1);
		
		ventana.add(panel);

		ventana.setVisible(true);
	}

	// Métodos
	public void setAthlete(Athlete a) {

		// Código y nombre
		codigo.setText(Integer.toString(a.getCode()));
		nombreTexto.setText(a.getName());

		// Género
		if (a.getGenre() == Athlete.Genre.MALE) {
			hombre.setSelected(true);
		} else {
			mujer.setSelected(true);
		}

		// Fecha nacimiento -> cadena en formato dd/MM/yyyy
		LocalDate f = a.getBirthdate();
		String fechaStr = String.format("%02d/%02d/%04d", f.getDayOfMonth(), f.getMonthValue(), f.getYear());
		fecha.setText(fechaStr);

		// País
		selectorPais.setSelectedItem(a.getCountry());
	}

	public Athlete getAthlete() throws FormDataNotValid {

		// Código
		String codigo = this.codigo.getText();
		if (codigo.isEmpty() || codigo.contains(" ")) {
			throw new FormDataNotValid("El código no puede ser vacío");
		}

		int cod;
		try {
			cod = Integer.parseInt(codigo);
		} catch (NumberFormatException e) {
			throw new FormDataNotValid("Se esperaba un código numérico");
		}

		// Nombre
		String nombre = nombreTexto.getText().trim();
		if (nombre.isBlank()) {
			throw new FormDataNotValid("El nombre no puede ser vacío");
		}

		// Género
		Genre genero;
		if (hombre.isSelected())
			genero = Genre.MALE;
		else if (mujer.isSelected())
			genero = Genre.FEMALE;
		else
			throw new FormDataNotValid("Se debe seleccionar un género");

		// Fecha
		String fechaStr = fecha.getText().trim();
		if (fechaStr.isEmpty())
			throw new FormDataNotValid("La fecha no puede ser vacía");

		LocalDate birthdate;
		try {
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			birthdate = LocalDate.parse(fechaStr, fmt);
		} catch (Exception e) {
			throw new FormDataNotValid("Fecha no tiene el formato esperado");
		}

		String pais = (String) selectorPais.getSelectedItem();

		return new Athlete(cod, nombre, genero, pais, birthdate);
	}

	public void setEditable(boolean editable) {
		codigo.setEditable(editable);
	    nombreTexto.setEditable(editable);
	    fecha.setEditable(editable);
	    hombre.setEnabled(editable);
	    mujer.setEnabled(editable);
	    selectorPais.setEnabled(editable);
	}

	public boolean isEditable() {
		return codigo.isEditable();
	}

}
