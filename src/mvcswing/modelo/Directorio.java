/*
 * Demo con propósito educativo sin afán de lucro ni compromiso comercial.
 */
package mvcswing.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Modelo de datos que representa un directorio telefónico.
 * @author blackzafiro
 */
public class Directorio {
	
	/**
	 * Ruta al archivo donde se guarda el directorio.
	 */
	private File archivo;
	
	/**
	 * Estructura de datos que contiene a las personas del directorio en
	 * memoria.
	 */
	private final ArrayList<Persona> personas;
	
	/**
	 * Crea un directorio y designa a <code>archivo</code> para guardar ahí
	 * los datos en disco cada vez que el directorio sea modificado.
	 * Si <code>archivo</code> contiene datos los carga.
	 * 
	 * @param nArchivo Ruta al archivo donde se guarda el directorio.
	 * @throws java.io.FileNotFoundException
	 */
	public Directorio(String nArchivo)
			throws FileNotFoundException, SecurityException, IOException {
		personas = new ArrayList<>();
		// TODO: leer datos del archivo, si existe.
		archivo = new File(nArchivo);
		if(archivo.exists() && archivo.isFile()) {
			cargaDatos();
		}
	}
	
	private void cargaDatos() throws IOException {
		try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
			personas.clear();
			String línea = lector.readLine();
			while(línea != null) {
				personas.add(Persona.leePersona(línea));
				línea = lector.readLine();
			}
			
		}
	}
	
	/**
	 * Número de personas en el directorio.
	 * @return 
	 */
	public int numPersonas() {
		return personas.size();
	}
	
	/**
	 * Persona en la iésima posición.
	 * @param i posición en la tabla del directorio.
	 * @return la persona en la posición <code>i</code>.
	 */
	public Persona lee(int i) {
		return personas.get(i);
	}
	
	/**
	 * Inserta la persona nueva al directorio y guarda sus datos en el archivo
	 * base.
	 * @param p Persona nueva.
	 * @return el índice en que se insertó a la persona.
	 * @throws java.io.FileNotFoundException Si el archivo que se había configurado
	 *         para la base de datos ya no existe.
	 */
	public int inserta(Persona p) throws FileNotFoundException {
		if(personas.size() > 0 &&
		   !archivo.exists()) throw new FileNotFoundException("El archivo base no está.");
		// TODO: Insertar en orden alfabético.
		// Por ahora inserta al final de la lista.
		personas.add(p);
		
		// Agrega a la persona al final del archivo.
		try (PrintStream out = new PrintStream(new FileOutputStream(archivo, true))) {
			p.escribe(out);
		}
		
		return personas.size() - 1;
	}
}
