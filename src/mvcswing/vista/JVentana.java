/*
 * Demo con propósito educativo sin afán de lucro ni compromiso comercial.
 */
package mvcswing.vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import mvcswing.controlador.Controlador;

/**
 * Vista del directorio.
 * @author blackzafiro
 */
public class JVentana extends JFrame {
	
	private final JFileChooser fc;
	
	/**
	 * Referencia al controlador para transmitirle los mensajes del usuario.
	 */
	private final Controlador controlador;
	
	/**
	 * Constructor de la aplicación.
	 * @param controlador referencia al controlador para transmitirle
	 *                    los mensajes del usuario.
	 */
	public JVentana(Controlador controlador) {
		setSize(400, 300);
		setTitle("Mi directorio");
		this.controlador = controlador;
		
		fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Agenda", "ag");
		fc.setFileFilter(filter);
		
		creaIU();
		creaBarraDeMenú();
		
		setVisible(true);
	}
	
	/**
	 * Crea la visualización de la tabla.
	 */
	private void creaIU() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout(5, 5));
		
		JDirectorio tabla = new JDirectorio(controlador.coordinadorDirectorio());
		JScrollPane tableScrollPane = new JScrollPane(tabla);
		getContentPane().add(tableScrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * Crea la barra de menús.
	 */
	private void creaBarraDeMenú() {
		JMenuBar menuBar = new JMenuBar();		// Crea barra de menú
		
		// TODO: Agregar las funciones para cambiar el archivo donde
		//       se guarda la base de datos.
		//       La petición se debe entregar al Controlador y éste la
		//       transmitirá a los coordinadores correspondientes.
		
		
		JMenu mArchivo = new JMenu("Archivo");	// Crea el menú Archivo
		mArchivo.setMnemonic(KeyEvent.VK_A);
		mArchivo.getAccessibleContext().setAccessibleDescription(
		    "Selecciona el archivo base para guardar el directorio");
		menuBar.add(mArchivo);
		
		
		JMenuItem mAbrir = new JMenuItem("Abrir",
                         KeyEvent.VK_A);
		mAbrir.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_1, ActionEvent.ALT_MASK));
		mAbrir.getAccessibleContext().setAccessibleDescription(
            "Selecciona el archivo base para leer/escribir el directorio");
		mAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				int returnVal = fc.showOpenDialog(JVentana.this);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String archivo = fc.getSelectedFile().getAbsolutePath();
					controlador.abre(archivo);
				}
			}
		});
		mArchivo.add(mAbrir);
		
		
		JMenuItem mGuardar = new JMenuItem("Guardar",
                         KeyEvent.VK_S);
		mGuardar.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_1, ActionEvent.ALT_MASK));
		mGuardar.getAccessibleContext().setAccessibleDescription(
            "Selecciona el archivo base para guardar el directorio");
		mGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				int returnVal = fc.showSaveDialog(JVentana.this);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String archivo = fc.getSelectedFile().getAbsolutePath();
					if (!archivo.endsWith(".ag")) {
						archivo = archivo + ".ag";
					}
					
					controlador.guarda(archivo);
				}
			}
		});
		mArchivo.add(mGuardar);
		
		
		
		
		JMenu mDatos = new JMenu("Datos");		// Crea el menú Datos
		mDatos.setMnemonic(KeyEvent.VK_D);
		mDatos.getAccessibleContext().setAccessibleDescription(
		    "Manipula los datos en el directorio");
		menuBar.add(mDatos);
		
		JMenuItem mInsertar = new JMenuItem("Nueva persona",
                         KeyEvent.VK_P);
		mInsertar.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_I, ActionEvent.ALT_MASK));
		mInsertar.getAccessibleContext().setAccessibleDescription(
            "Agrega una persona nueva");
		mInsertar.addActionListener(ae -> this.controlador.agregaPersona());
		mDatos.add(mInsertar);
		
		
		this.setJMenuBar(menuBar);
	}
	
	/**
	 * Muestra un mensaje al usuario con el error reportado.
	 * @param ex
	 */
	public void notifica(FileNotFoundException ex) {
		JOptionPane.showMessageDialog(
						this,
						ex.getMessage(),
						"Archivo no encontrado",
						JOptionPane.ERROR_MESSAGE);
		// Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	public void notifica(SecurityException ex) {
		JOptionPane.showMessageDialog(
						this,
						ex.getMessage(),
						"Error de seguridad",
						JOptionPane.ERROR_MESSAGE);
		//Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	public void notifica(IOException ex) {
		JOptionPane.showMessageDialog(
						this,
						ex.getMessage(),
						"Erro al acceder al archivo",
						JOptionPane.ERROR_MESSAGE);
		//Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
	}
}
