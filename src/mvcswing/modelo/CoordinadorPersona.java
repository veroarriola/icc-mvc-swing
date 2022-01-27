/*
 * Demo con propósito educativo sin afán de lucro ni compromiso comercial.
 */
package mvcswing.modelo;

import mvcswing.vista.JPersona;

/**
 * Modelo de la aplicación o coordinador para el modelo de una persons.
 * @author blackzafiro
 */
public class CoordinadorPersona {
	
	private final Persona persona;
	
	/**
	 * Constructor que recibe una vista de java Swing y una persona con los
	 * datos del modelo.
	 * @param persona Modelo
	 */
	public CoordinadorPersona(Persona persona) {
		this.persona = persona;
	}
	
	/**
	 * Lee el nombre propuesto desde el valor actual en la vista y lo valida
	 * antes de asignarlo al modelo de datos.
	 * @param vista   Diálogo que pide/muestra los datos de la persona.
	 * @throws ExcepciónDatoInválido si el nombre es <code>null</code> o una
	 *         cadena vacía.
	 */
	public void leeNombre(JPersona vista) throws ExcepciónDatoInválido {
		String nombre = vista.nombre();
		if (nombre == null || nombre.isBlank()) throw new ExcepciónDatoInválido("No se ha asignado un nombre");
		persona.nombre(nombre);
	}
	
	/**
	 * Permite acceder al modelo con los datos de la persona.
	 * @return El modelo de la persona.
	 */
	Persona persona() { return persona; }
	
}
