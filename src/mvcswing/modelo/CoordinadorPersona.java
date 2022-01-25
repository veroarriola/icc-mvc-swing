/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcswing.modelo;

import mvcswing.vista.JPersona;

/**
 * Modelo de la aplicación o coordinador para el modelo de una persons.
 * @author blackzafiro
 */
public class CoordinadorPersona {
	
	private final Persona persona;
	private final JPersona vista;
	
	/**
	 * Constructor que recibe una vista de java Swing y una persona con los
	 * datos del modelo.
	 * @param vista 
	 * @param persona 
	 */
	public CoordinadorPersona(Persona persona, JPersona vista) {
		this.persona = persona;
		this.vista = vista;
	}
	
	/**
	 * Lee el nombre propuesto desde el valor actual en la vista y lo valida
	 * antes de asignarlo al modelo de datos.
	 * @throws ExcepciónDatoInválido si el nombre es <code>null</code> o una
	 *         cadena vacía.
	 */
	public void leeNombre() throws ExcepciónDatoInválido {
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
