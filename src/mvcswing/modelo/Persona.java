/*
 * Demo con propósito educativo sin afán de lucro ni compromiso comercial.
 */
package mvcswing.modelo;

import java.io.PrintStream;

/**
 * Modelo de datos que representa a una persona en un directorio.
 * @author blackzafiro
 */
public class Persona {
	
	private static final String SEP = "&;&";
	
	private String nombre;
	private String dirección;
	private Teléfono teléfono;
	
	public Persona() {}
	
	public Persona(String nombre, String dirección, Teléfono tel) {
		nombre(nombre);
		this.dirección = dirección;
		this.teléfono = tel;
	}
	
	/**
	 * Escribe el nombre de la persona, asegurándose de que no sea <code>null</code>.
	 * @param nombre 
	 */
	public void nombre(String nombre) {
		if (nombre == null) throw new NullPointerException("Debe poner un nombre");
		this.nombre = nombre;
	}
	
	/**
	 * Lectura del atributo <code>nombre</code>.
	 * @return El nombre de la persona.
	 */
	public String nombre() {
		return nombre;
	}
	
	/**
	 * Lectura del atributo <code>dirección</code>.
	 * @return La dirección de la persona.
	 */
	public String dirección() {
		return dirección;
	}
	
	/**
	 * Lectura del atributo <code>teléfono</code>.
	 * @return El teléfono de la persona.
	 */
	public Teléfono teléfono() {
		return teléfono;
	}
	
	/**
	 * Escribe los datos de la persona en el flujo.
	 * @param out Flujo donde se escribirán los datos de la persona.
	 */
	public void escribe(PrintStream out) {
		out.print(nombre);
		out.print(SEP);
		out.print(dirección);
		out.print(SEP);
		if (teléfono != null) {
			teléfono.escribe(out);
		} else {
			out.print(teléfono);
		}
		out.println();
	}
	
	/**
	 * Recupera los datos de la persona desde la descripción.
	 * 
	 * Este método se utilizar para cargar los datos desde un archivo.
	 * @param descripción
	 * @return 
	 */
	public static Persona leePersona(String descripción) {
		String[] datos = descripción.split(SEP);
		String nombre = datos[0];
		String dirección = datos[1].equals("null") ? "" : datos[1];
		Teléfono tel = null; // TODO: Leer teléfono
		return new Persona(nombre, dirección, tel);
	}
	
}
