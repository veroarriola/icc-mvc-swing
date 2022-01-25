/*
 * Demo con propósito educativo sin afán de lucro ni compromiso comercial.
 */
package mvcswing.vista;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Vista para solicitar/modificar los datos de una persona.
 * @author blackzafiro
 */
public class JPersona extends JDialog {
	
	public enum Opción {
		OK,
		CANCELAR;
	}
	
    private final JTextField cajaNombre = new JTextField(50);
	private final JTextField cajaDirección = new JTextField(50);
	private final JTextField cajaTeléfono = new JTextField(37);
	private final JTextField cajaExtensión = new JTextField(7);
	
	private Opción opciónSeleccionada = Opción.CANCELAR;
	
	/**
	 * Crea un diálogo sujeto a la ventana principal de la aplicación.
	 * @param padre 
	 */
	public JPersona(JFrame padre) {
		super(padre, "Persona", true);
		init();
		pack();
	}
	
	private void init() {
		
		// Controles para introducir la información de la Persona
		
		JPanel controles = new JPanel();
		GroupLayout layout = new GroupLayout(controles);
		controles.setLayout(layout);
		this.getContentPane().add(controles, BorderLayout.CENTER);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// Etiquetas
		JLabel etqN = new JLabel("Nombre:");
		JLabel etqD = new JLabel("Dirección:");
		JLabel etqT = new JLabel("Teléfono:");
		JLabel etqE = new JLabel("ext:");
		
		// Alineación por columnas
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().
            addComponent(etqN).addComponent(etqD).
			addComponent(etqT).addComponent(etqE));
		hGroup.addGroup(layout.createParallelGroup().
            addComponent(cajaNombre).
			addComponent(cajaDirección).
			addComponent(cajaTeléfono).
			addComponent(cajaExtensión));
		layout.setHorizontalGroup(hGroup);
		
		// Alineación por renglones
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(etqN).addComponent(cajaNombre));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(etqD).addComponent(cajaDirección));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(etqT).addComponent(cajaTeléfono));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
			addComponent(etqE).addComponent(cajaExtensión));
		layout.setVerticalGroup(vGroup);
		
		
		// Botones Ok y Cancelar
		
		JPanel panelBotones = new JPanel();
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(e -> {
			opciónSeleccionada = Opción.OK;
			JPersona.this.setVisible(false);
		});
		panelBotones.add(okButton);
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(e -> {
			opciónSeleccionada = Opción.CANCELAR;
			JPersona.this.setVisible(false);
		});
		panelBotones.add(cancelButton);
		this.getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Marca si el usuario presionó el botón Ok y se deben leer los datos
	 * ingresados o si presionó Cancelar y se deben ignorar.
	 * @return 
	 */
	public Opción opciónSeleccionada() {
		return opciónSeleccionada;
	}
	
	/**
	 * Devuelve el contenido del campo nombre.
	 * @return el nombre.
	 */
	public String nombre() {
		return cajaNombre.getText();
	}
	
	/**
	 * Vacía las cajas de texto y la opción seleccionada queda, por defecto,
	 * en CANCELAR.
	 */
	public void reiníciate() {
		opciónSeleccionada = Opción.CANCELAR;
		cajaNombre.setText("");
		cajaDirección.setText("");
		cajaTeléfono.setText("");
		cajaExtensión.setText("");
	}
	
}
