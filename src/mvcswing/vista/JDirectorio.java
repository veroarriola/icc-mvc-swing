/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcswing.vista;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import mvcswing.modelo.CoordinadorDirectorio;

/**
 * Vista del directorio en forma de tabla.
 * @author blackzafiro
 */
public class JDirectorio extends JTable implements TableModelListener {

	JDirectorio(CoordinadorDirectorio modeloDeTabla) {
		super(modeloDeTabla);		// Lo vuelve escucha de modeloDeTabla
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	/**
	 * Aquí se implementa la reacción de esta vista cuando el modelo Ventana
	 * notifique un cambio en los datos.
	 * @param tme 
	 */
	
	@Override
	public void tableChanged(TableModelEvent tme) {
		super.tableChanged(tme);
		/*
		
		int row = tme.getFirstRow();
        int column = tme.getColumn();
        CoordinadorDirectorio model = (CoordinadorDirectorio)tme.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
		Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, columnName, tme);
		*/
	}
	
	
}
