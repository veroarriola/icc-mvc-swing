/*
 * Demo con propósito educativo sin afán de lucro ni compromiso comercial.
 */
package mvcswing.modelo;

import java.io.PrintStream;

/**
 * Modelo para un número telefónico.
 * @author blackzafiro
 */
public class Teléfono {
	
	private final String EXT = "&ext&";
	private final int NO_EXT = -1;
	
	private int número;
	private int extensión;
	
	public Teléfono(int num, int ext) {
		número(num);
		extensión(ext);
	}
	
	/**
	 * Escritura del valor número.
	 * @param num  Número telefónico.
	 */
	public void número(int num) {
		if (num < 0) throw new IllegalArgumentException
			("No existen teléfonos con números negativos");
		número = num;
	}
	
	/**
	 * Escritura del valor extensión.
	 * @param ext Extensión opcional. El valor -1 indica que no hay
	 *        extensión.
	 */
	public void extensión(int ext) {
		if (ext < -1) throw new IllegalArgumentException
			("No existen extensiones con valores negativos");
		extensión = ext;
	}
	
	/**
	 * Lectura del atributo <code>número</code>.
	 * @return El número de teléfono.
	 */
	public int número() {
		return número;
	}
	
	/**
	 * Lectura del atributo <code>extensión</code>.
	 * @return La extensión del teléfono o -1 si no hay.
	 */
	public int extensión() {
		return extensión;
	}
	
	/**
	 * Escribe los datos de la persona en el flujo.
	 * @param out Flujo donde se escribirán los datos del teléfono.
	 */
	public void escribe(PrintStream out) {
		out.print(número);
		if (extensión != NO_EXT) {
			out.print(EXT);
			out.print(extensión);
		}
	}
}
