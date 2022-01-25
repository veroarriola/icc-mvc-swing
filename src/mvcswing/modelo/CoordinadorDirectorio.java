/*
 * Demo con propósito educativo sin afán de lucro ni compromiso comercial.
 */
package mvcswing.modelo;


import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.table.AbstractTableModel;

/**
 * Modelo de la aplicación o coordinador para el modelo de un directorio.
 * @author blackzafiro
 */
public class CoordinadorDirectorio extends AbstractTableModel {
	
	private final Directorio directorio;
	
	/**
	 * Constructor que con <code>archivo</code> como base.  Si ya existe y tiene
	 * datos, los carga desde ahí.  En cualquier caso, cada vez que se inserte,
	 * borre o modifique un dato, los cambios serán guardados automáticamente
	 * en ese archivo.
	 * @param archivo  Dirección del archivo base.
	 * @throws SecurityException Si la aplicación no tiene permiso para accder
	 *                           el archivo.
	 * @throws IOException Problemas al acceder el archivo para lectura/escritura.
	 */
	public CoordinadorDirectorio(String archivo) throws SecurityException, IOException {
		directorio = new Directorio(archivo);
	}

	/**
	 * Nombres de las columnas con índices de izquierda a derecha, comenzando
	 * con el cero.
	 * @param column Número de columna.
	 * @return Nombre de la columna.
	 */
	@Override
	public String getColumnName(int column) {
		switch(column) {
			case 0:
				return "Nombre";
			case 1:
				return "Dirección";
			case 2:
				return "Teléfono";
			case 3:
				return "Extensión";
			default:
				return super.getColumnName(column);
		}
	}
			
	/**
	 * Número de renglones con datos.
	 * @return 
	 */
	@Override
	public int getRowCount() {
		return directorio.numPersonas();
	}

	/**
	 * Número de columnas en el directorio.
	 * @return 
	 */
	@Override
	public int getColumnCount() {
		return 4;
	}

	/**
	 * Valor en el renglón <code>i</code>, columna <code>j</code>.
	 * @param i renglón
	 * @param j columna
	 * @return valor
	 */
	@Override
	public Object getValueAt(int i, int j) {
		if( i == -1) return null;
		Persona p = directorio.lee(i);
		switch(j) {
			case 0:
				return p.nombre();
			case 1:
				return p.dirección();
			case 2:
				return p.teléfono() == null ? null : p.teléfono().número();
			case 3:
				if (p.teléfono() == null) return null;
				int ext = p.teléfono().extensión();
				return ext == -1 ? null : ext;
		}
		return null;
	}
	
	/**
	 * Pide al modelo que inserte los datos accesibles desde <code>cp</code>
	 * y dispara el evento requerido para que la vista se actualice.
	 * @param cp Coordinador del modelo persona.
	 * @throws FileNotFoundException Al insertar los datos son guardados
	 *         automáticamente en el archivo base, si hay un error el método
	 *         lanza esta excepción.
	 */
	public void inserta(CoordinadorPersona cp) throws FileNotFoundException {
		int pos = directorio.inserta(cp.persona());
		this.fireTableRowsInserted(pos, directorio.numPersonas() - 1);
	}
}
