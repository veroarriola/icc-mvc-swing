/*
 * Demo con propósito educativo sin afán de lucro ni compromiso comercial.
 */
package mvcswing.modelo;

/**
 * El dato ingresado no cubre con los requerimientos.
 * @author blackzafiro
 */
public class ExcepciónDatoInválido extends Exception {
	
	/**
	 * Constructor con mensaje.
	 * @param msg Mensaje con detalles de la excepción.
	 */
	public ExcepciónDatoInválido(String msg) {
		super(msg);
	}
	
}
