/*
 * Demo con propósito educativo sin afán de lucro ni compromiso comercial.
 */
package mvcswing.controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mvcswing.modelo.CoordinadorDirectorio;
import mvcswing.modelo.CoordinadorPersona;
import mvcswing.modelo.ExcepciónDatoInválido;
import mvcswing.modelo.Persona;
import mvcswing.vista.JPersona;
import mvcswing.vista.JVentana;

/**
 * Controlador, contiene la lógica de la aplicación y establece la comunicación
 * entre modelo y vistas.
 * @author blackzafiro
 */
public class Controlador {
	
	/**
	 * Vista gráfica de los datos en el directorio.
	 */
	private JVentana ventanaPrincipal;
	
	/**
	 * Vista para agregar una persona.
	 */
	private JPersona dialogo;
	
	/**
	 * Modelo, maneja los datos del directorio.
	 */
	private CoordinadorDirectorio coordModeloDirectorio;
	
	/**
	 * Nombre del archivo por defecto donde se estará guardando la agenda.
	 */
	private static final String ARCHIVO_POR_DEFECTO = "Agenda.ag";
	
	/**
	 * Construye al controlador y este a su vez crea el modelo y sus vistas.
	 */
	public Controlador() {
		
		// Crear un objeto tipo Runnable con el código a ejecutar por el hilo
		// encargado de despachar eventos:
		// Crea y muestra la interfaz gráfica de usuario.
        javax.swing.SwingUtilities.invokeLater(() -> {
			ventanaPrincipal = new JVentana(this);
			dialogo = new JPersona(ventanaPrincipal);
		});
		
		// Crea un objeto directorio con los datos del archivo por defecto.
		try {
			coordModeloDirectorio = new CoordinadorDirectorio(ARCHIVO_POR_DEFECTO);
		} catch (FileNotFoundException ex) {
			ventanaPrincipal.notifica(ex);
		} catch (SecurityException ex) {
			ventanaPrincipal.notifica(ex);
		} catch (IOException ex) {
			ventanaPrincipal.notifica(ex);
		}
	}
	
	/**
	 * Permite obtener una referencia al coordinador del directorio.
	 * @return referencia al coordinador.
	 */
	public CoordinadorDirectorio coordinadorDirectorio() {
		return coordModeloDirectorio;
	}
	
	/**
	 * Muestra la vista y recaba los datos hasta agregar a una persona nueva
	 * al directorio.
	 */
	public void agregaPersona() {
		CoordinadorPersona coord = new CoordinadorPersona(new Persona());
		
		boolean datosVálidos = false;
		while(!datosVálidos) {
			dialogo.setVisible(true);
			if (dialogo.opciónSeleccionada() == JPersona.Opción.CANCELAR)  return;
			try {
				coord.leeNombre(dialogo);
			} catch(ExcepciónDatoInválido ex) {
				JOptionPane.showMessageDialog(ventanaPrincipal,
						ex.getMessage(),
						"Nombre",
						JOptionPane.ERROR_MESSAGE);
				continue;
			}
			
			// TODO: Validar los demás datos.
			
			datosVálidos = true;
		}
		
		try {
			// Agregar persona a directorio/tabla
			coordModeloDirectorio.inserta(coord);
			dialogo.reiníciate();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(ventanaPrincipal,
						ex.getMessage(),
						"Archivo",
						JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Cargar datos en el directorio a partir del archivo con la dirección
	 * indicada.
	 * @param archivo Dirección del archivo.
	 */
	public void abre(String archivo) {
		//TODO: Pedir al CoordinadorDirectorio que cargue los datos desde
		//      archivo y sea donde se guarden elementos nuevos.  El coordinador
		//      a su vez debe disparar un evento para que la tabla JDirectorio
		//      se actualice.
		Logger.getLogger(Controlador.class.getName())
				.log(Level.INFO, "Opening: {0}", archivo);
	}
	
	/**
	 * Guardar los datos en el directorio en el archivo con la dirección
	 * indicada, partir de ahora éste será el archivo base.
	 * @param archivo Dirección del archivo.
	 */
	public void guarda(String archivo) {
		//TODO: Pedir al CoordinadorDirectorio que guarde los datos en el
		//      archivo y sea donde se guarden elementos nuevos.
		Logger.getLogger(Controlador.class.getName()).log(
				Level.INFO,
				"Guardando en: {0}", archivo);
	}
	
	/**
	 * Inicia la aplicación.
	 * @param args 
	 */
	public static void main(String[] args) {
		Controlador c = new Controlador();
    }
}
