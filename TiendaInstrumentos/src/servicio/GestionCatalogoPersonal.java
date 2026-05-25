package servicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import modelo.dePersonal;

/*
 * Clase que representa la gestion de los objetos del personal.
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public class GestionCatalogoPersonal {
	

	private ArrayList<dePersonal> listadelPersonal = new ArrayList<>();
	

	private static final String FICHERO_PERSONAL = "src/repositorio/personal.txt";
	
    /*====================CONSTRUCTORES=======================================================*/

	public GestionCatalogoPersonal() {
		
	}
    /*======================   FIN DE LOS CONSTRUCTORES      =============================*/

	
	//Gets y sets
	public ArrayList<dePersonal> getListaPersonal() {
		
		return listadelPersonal;
		
	}

	
	
	public void setListaPersonal(ArrayList<dePersonal> listadelPersonal) {
		
		this.listadelPersonal = listadelPersonal;
		
	}



	//Crud
	
	 /**
     * Añade a la lista el elemento que decidamos
     *
     * @param articulo Elemento que se va a añadir.
     * 
     * @return void
     */
	public void añadirArticulo(dePersonal articulo) {
		
		if (buscarArticulo(articulo.getSerialNumber()) == null) {
			
			listadelPersonal.add(articulo);
			
		}
	}
	
	 /**
     * Elimina de la lista el elemento que decidamos
     *
     * @param serialNumber: como es hashmap el tipo de lista, con pasarle el indice (el sn) se puede eliminar
     * 
     * @return void
     */
	public void eliminarArticulo(String serialNumber) {
		listadelPersonal.removeIf(articulo -> articulo.getSerialNumber().equals(serialNumber));
	}
	 /**
     * Añade a la lista el elemento que decidamos
     *
     * @param articulo Elemento que se va a añadir.
     * 
     * @return void
     */
	public dePersonal buscarArticulo(String serialNumber) {
		
		dePersonal ejemplo=null;
		
		for (dePersonal articuloActual : listadelPersonal) {
			
			if (articuloActual.getSerialNumber().equals(serialNumber)) {
				
				ejemplo = articuloActual;
			}
			
		}
		
		return ejemplo;
	}
	
	 /**
     * Imprimimos todos los objetos de un mapa catalago
     *
     * @param Ninguno
     * 
     * @return void
     */
	public void imprimirTodos() {
		
		if (listadelPersonal.size() != 0) {
			
			for (dePersonal articulo : listadelPersonal) {
				
				System.out.println(articulo.toString());
				
			}
			
		} else {
			System.out.println("No hay articulos de personal registrados.");
		}
	}

	
	 /*Codigo versionado de video canal de youtube "Makigas"
    * Calculamos el precio total mediante la funcionalidad .map y .reduce
    *
    * @param Ninguno
    * 
    * @return void
    */
	public double calcularPrecioTotal() {
		
	   double precioTotal= listadelPersonal.stream()
	            .map(articulo -> articulo.getPrecio())
	            .reduce(0.0, (a, b) -> a + b);
	     
	     return precioTotal;
	}
	// ---------------------------------------------------------------
	// PERSISTENCIA
	// ---------------------------------------------------------------
	 /**
	    * 
	    * Esta funcion realiza un guardado de datos de objetos del personal.
	    *     
	    * @param Ninguno
	    * 
	    * @return void
	    */
	public void guardarDatosPersonal() {
		
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FICHERO_PERSONAL))) {
			
			for (dePersonal articuloActual : listadelPersonal) {
				
				bufferedWriter.write(lineaDePersonal(articuloActual));
				
				bufferedWriter.newLine();
			}
			
		} catch (IOException e) {
			
			System.out.println("Error: " + e.getMessage());
			
		}
	}

	
	// Este bloque return lo ha hecho la inteliencia artificial "Gemini" para el ahorro de tiempo debido a que es un proceso lento y repetitivo
	 /**
	    * 
	    * Esta funcion realiza una linea con los datos de articuloActual para su guardado
	    * Esta separado por " - "
	    * 
	    * @param articulo actual: objeto dePersonal que queemos versionar a linea
	    * 
	    * @return lineaDePersonal: la linea que se va a guardar
	    */
	private String lineaDePersonal(dePersonal articuloActual) {
		
		 String lineaFinal=articuloActual.getSerialNumber() + " - " + articuloActual.getMarca() + " - " + articuloActual.getNombre() + " - "
				+ articuloActual.getModelo() + " - " + articuloActual.getPrecio() + " - " + articuloActual.getFechaAdquisicion() + " - "
				+ articuloActual.getEnCaja() + " - " + articuloActual.getNombreDueño();
		 
		 return lineaFinal;
		 
	}
//Fin del bloque
	
	 /**
	    * Esta funcion implementa los datos del fichero de guardado en el sistema
	    *     
	    * @param Ninguno
	    * 
	    * @return Void
	    */
	public void implementarDatosPersonal() {
		
		try (BufferedReader bufferReader = new BufferedReader(new FileReader(FICHERO_PERSONAL))) {
			
			String lineaActual;
			
			while ((lineaActual = bufferReader.readLine()) != null) {
				
				String[] datosPErsonales = lineaActual.split(" - ");
				
				reconstruirDePersonal(datosPErsonales);				
			}
			
		} catch (Exception e) {
			
			System.out.println("Error: " + e.getMessage());
			
		}
	}
	 /**
	    * Esta funcion implementa los datos del fichero de guardado en el sistema
	    *     
	    * @param datos: array con los datos de objeto de personal para su implementacion mediante su constructor
	    * 
	    * @return Void
	    */
	private void reconstruirDePersonal(String[] datos) {
		
		try {
			
			dePersonal articulo = new dePersonal(
					
					datos[0],
					
					datos[1], 
					
					datos[2], 
					
					datos[3], 
					
					Double.parseDouble(datos[4]),
			
					datos[5], 
					
					Boolean.parseBoolean(datos[6]),
					
					datos[7]);
			
			listadelPersonal.add(articulo);
			
		} catch (Exception e) {
			
			System.out.println("Error: " + e.getMessage());
			
		}
	}
}