package servicio;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
 
import modelo.Articulo;
import modelo.deCatalogo;
import modelo.deCuerda;
import modelo.dePercusion;
import modelo.deViento;

/*CLASE REALIZADA MEDIANTE TUTORIAL DE CANAL DE YOUTUBE: TodoCode*/
/*
 * Clase que representa la gestion de un catalogo de instruments
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public class GestionCatalogo {
 
	private HashMap<String, deCatalogo> mapaCatalogo = new HashMap<>();
 
	private static final String FICHERO_CATALOGO = "src/repositorio/catalogo.txt";
	
    /*====================CONSTRUCTORES=======================================================*/

	public GestionCatalogo() {
	}
	
    /*======================     FIN DE LOS CONSTRUCTORES         =============================*/

	
	//gets y sets
	public HashMap<String, deCatalogo> getMapaCatalogo() {
		return mapaCatalogo;
	}
 
	public void setMapaCatalogo(HashMap<String, deCatalogo> mapaCatalogo) {
		this.mapaCatalogo = mapaCatalogo;
	}
 
	// AQUI EL HE HECHO EL CRUD
	 /**
     * Añade a la lista el elemento que decidamos
     *
     * @param articulo Elemento que se va a añadir.
     * 
     * @return void
     */
	public void añadirArticulo(deCatalogo articulo) {
 
		if (buscarArticulo(articulo.getSerialNumber()) == null) {
 
			mapaCatalogo.put(articulo.getSerialNumber(), articulo);
 
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
 
		mapaCatalogo.remove(serialNumber);
 
	}
 
	 /**
     * Añade a la lista el elemento que decidamos
     *
     * @param articulo Elemento que se va a añadir.
     * 
     * @return void
     */
	public deCatalogo buscarArticulo(String serialNumber) {
 
		return mapaCatalogo.get(serialNumber);
 
	}
 
	 /**
     * Imprimimos todos los objetos de un mapa catalago
     *
     * @param
     * 
     * @return void
     */
	public void imprimirTodos() {
 
		if(getMapaCatalogo().size()!=0) {
		
		
		for (deCatalogo articuloActual : mapaCatalogo.values()) {
 
			System.out.println(articuloActual.toString());
 
		}
		}else {
			
			System.out.println("No hay articulos! ");
			
		}
	}
 
	// Codigo versionado de video canal de youtube "Makigas"
	 /**
     * Imprimimos todos los objetos que se pueden imprimir
     *
     * @paran ninguno
     * 
     * @return void (se imprime por pantalla)
     */
	public void imprimirDisponibles() {
 
		List<deCatalogo> lista = mapaCatalogo.values().stream()
 
				.filter(articulo -> !articulo.getEstaReservado())
 
				.collect(Collectors.toList());
 
		
		
		for (deCatalogo articulo : lista) {
 
			System.out.println(articulo.toString());
 
		}
		
		
 
	}
 
	// Codigo versionado de video canal de youtube "Makigas"
		 /**
	     * Imprimimos por pantalla el objeto mas caro
	     *
	     * @param Ninguno
	     * 
	     * @return void (se imrpime por consola)
	     */
	public void obtenerMasCaro() {

	    deCatalogo masCaro = mapaCatalogo.values().stream()
	            .max(Comparator.comparingDouble(articulo -> articulo.getPrecio()))
	            .orElse(null);

	    if (masCaro != null) {

	        System.out.println(masCaro.toString());

	    } else {

	        System.out.println("No hay articulos en el catalogo.");

	    }
	}
	
	
	
	// Codigo versionado de video canal de youtube "Makigas"

			 /**
		     * Cuenta los disponibles mediante un filter
		     *
		     * @param Ninguno
		     * 
		     * @return long devuelve la cantidad de objetos disponibles (en long debido a que filter count usa ese formato)
		     */
	public Long contarDisponibles() {
 
		return mapaCatalogo.values().stream()
 
				// Nota personal: (ELIMINAR A LA HORA DE ENTREGAR)
				// Segun el video de youtube filter determina mediante el boolean si es true o
				// false
				// como en este caso el mismo get devuelve boolean es mas sencillo
				.filter(articulo -> !articulo.getEstaReservado())
 
				.count();
	}
	
	// ---------------------------------------------------------------
	// PERSISTENCIA
	// ---------------------------------------------------------------

	 /**
    * 
    * Esta funcion realiza un guardado de datos de Instrumentos.
    * Al querer guardar tres tipos diferentes de instrumentos hay que realizar distintas funciones.
    * Cada una para cada tipo (dentro del codigo mas comentarios)     
    *     
    * @param Ninguno
    * 
    * @return void
    */
	public void guardarDatosCatalogo() {

	    try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(FICHERO_CATALOGO))) {

	        for (deCatalogo articuloActual : mapaCatalogo.values()) {

	        	//Realizaremos un if para cada tipo instancia y realizaremos
	            if (articuloActual instanceof deCuerda) {
	            	
	            	//EJEMPLO DE FUNCIONALIDAD:
	            	//Realizaremos un casteo a dicho artiuclo a deCuerda escribiendo loque nos de en la funcion lineaDeCuerda
	            	
	            	String lineaActual=lineaDeCuerda((deCuerda) articuloActual);
	            	
	            	bufferWriter.write(lineaActual);

	            } else if (articuloActual instanceof deViento) {

	            	String lineaActual=lineaDeViento((deViento) articuloActual);
	            	
	            	bufferWriter.write(lineaActual);

	            } else if (articuloActual instanceof dePercusion) {

	            	String lineaActual=lineaDePercusion((dePercusion) articuloActual);
	            	
	            	bufferWriter.write(lineaActual);

	            }

	            bufferWriter.newLine();

	        }

	    } catch (Exception e) {

	        System.out.println("Error: " + e.getMessage());

	    }
	}
 

	 /**
	    * Esta funcion realiza una linea de los datos que sin necesidad de saber el tipo se puede saber.
	    * Se separa mediante " - "   
	    *     
	    * @param articulo: es el articulo que se va a añadir mediante lineaArticuloComun
	    * 
	    * @param tipo: esta linea no esta dentro de articulo y sigue siendo un dato comun, por lo que hay que añadirlo.
	    * 
	    * @return String: devuelve una linea con los datos comunes.
	    */
	private String datosComunes(deCatalogo articulo, String tipo) {
 
		String lineaArticuloComun= articulo.getSerialNumber() + " - " + articulo.getMarca() + " - " + articulo.getNombre() + " - "
				+ articulo.getModelo() + " - " + articulo.getPrecio() + " - " + articulo.getFechaAdquisicion() + " - "
				+ articulo.getEnCaja() + " - " + articulo.getNumeroAlmacen() + " - " + articulo.getNumeroPalet() + " - "
				+ articulo.getEstaReservado() + " - " + tipo;
		
		return lineaArticuloComun;
 
	}
 
	
	 /**
	    * Esta funcion devuelve los datos en formato linea de los datos deCuerda
	    * Se separa mediante " - "
	    * Hace referencia a datos comunes para devolver la linea completa  
	    *     
	    * @param  articulo: es el objeto que queremos convertir a linea
	    * 
	    * @return String: devuelve los datos faltantes de objetos de Cuerda
	    */
	private String lineaDeCuerda(deCuerda articulo) {
 
		String lineaDevolver = datosComunes(articulo, "deCuerda") + " - " + articulo.getNumeroCuerdas() + " - " + articulo.getCalibreCuerdas();
 
		return lineaDevolver;
	}
 
	
	/**
	    * Esta funcion devuelve los datos en formato linea de los datos deCuerda
	    * Se separa mediante " - "
	    * Hace referencia a datos comunes para devolver la linea completa
	    *    
	    *     
	    * @param  articulo: es el objeto que queremos convertir a linea (datos restantes)
	    * 
	    * @return String: devuelve los datos faltantes de objetos de Cuerda
	    */
	private String lineaDeViento(deViento articulo) {
 
		String lineaDevolver = datosComunes(articulo, "deViento") + " - " + articulo.getMaterial();
 
		return lineaDevolver;
	}
 
		/**
	    * Esta funcion devuelve los datos en formato linea de los datos dePercusion
	    * Se separa mediante " - "
	    * Hace referencia a datos comunes para devolver la linea completa
	    *     
	    * @param  articulo: es el objeto que queremos convertir a linea (datos restantes)
	    * 
	    * @return String: devuelve los datos faltantes de objetos de Percusion
	    */
	private String lineaDePercusion(dePercusion articulo) {
 
		String lineaDevolver = datosComunes(articulo, "dePercusion") + " - " + articulo.isNecesitaBaqueta();
 
		return lineaDevolver;
	}
 
	// CARGA DE DATOS DEL FICHERO
 
	 /**
	    * Esta funcion implementa los datos del fichero de guardado en el sistema
	    * Realiza distintos tipos de implementacion segun el instrumento que sea (deCuerda deViento dePercusion)
	    *     
	    * @param Ninguno
	    * 
	    * @return Void
	    */
	public void implementarDatosCatalogo() {
 
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FICHERO_CATALOGO))) {
 
			String linea;
 
			while ((linea = bufferedReader.readLine()) != null) {
 
				String[] datos = linea.split(" - ");
 
				String tipo = datos[10];
 
				if (tipo.equals("deCuerda")) {
					
					reconstruirDeCuerda(datos);
					
				} else if (tipo.equals("deViento")) {
					
					reconstruirDeViento(datos);
					
				} else if (tipo.equals("dePercusion")) {
					
					reconstruirDePercusion(datos);
					
				}
			}
 
		} catch (IOException e) {
 
			System.out.println("Error al cargar el catalogo: " + e.getMessage());
 
		}
	}
	
	 
	 /**
	    * 
	    * Este metodo recibe un array con los datos de deCuerda
	    * Estos datos se implementaran en un constructor de deCuerda y se creara dicha instancia
	    * Tras esto pasara al hashmap para guardarse en la memoria
	    *     
	    * @param datos: un array con los datos de reconstruir cuerda
	    * 
	    * @return Void
	    */
	private void reconstruirDeCuerda(String[] datos) {
 
		try {
 
			deCuerda articulo = new deCuerda(
					
					datos[0],                  
					
					datos[1], 
					
					datos[2],   
					
					datos[3],   
					
					Double.parseDouble(datos[4]),  
					
					datos[5],  
					
					Boolean.parseBoolean(datos[6]),
					
					Integer.parseInt(datos[7]), 
					
					Integer.parseInt(datos[8]),  
					
					Integer.parseInt(datos[11]),  
					
					Double.parseDouble(datos[12])   
					
			);
 
			articulo.setEstaReservado(Boolean.parseBoolean(datos[9]));
 
			mapaCatalogo.put(articulo.getSerialNumber(), articulo);
 
		} catch (Exception e) {
 
			System.out.println("Error: " + e.getMessage());
 
		}
	}
	 /**
	    * 
	    * Este metodo recibe un array con los datos de deViento
	    * Estos datos se implementaran en un constructor de deViento y se creara dicha instancia
	    * Tras esto pasara al hashmap para guardarse en la memoria
	    *     
	    * @param datos: un array con los datos de reconstruir viento
	    * 
	    * @return Void
	    */
	private void reconstruirDeViento(String[] datos) {
 
		try {
 
			deViento articulo = new deViento(
					
					datos[0],           
					
					datos[1],            
					
					datos[2],           
					
					datos[3],           
					
					Double.parseDouble(datos[4]),  
					
					datos[5],                  
					
					Boolean.parseBoolean(datos[6]),   
					
					Integer.parseInt(datos[7]),   
    
					Integer.parseInt(datos[8]),  
					
					datos[11]                          
			);
 
			articulo.setEstaReservado(Boolean.parseBoolean(datos[9]));
 
			mapaCatalogo.put(articulo.getSerialNumber(), articulo);
 
		} catch (Exception e) {
 
			System.out.println("Error: " + e.getMessage());
 
		}
	}
	 /**
	    * 
	    * Este metodo recibe un array con los datos de dePercusion
	    * Estos datos se implementaran en un constructor de deViento y se creara dicha instancia
	    * Tras esto pasara al hashmap para guardarse en la memoria
	    *     
	    * @param datos: un array con los datos de reconstruir percusion
	    * 
	    * @return Void
	    */
	private void reconstruirDePercusion(String[] datos) {
 
		try {
 
			dePercusion articulo = new dePercusion(
					
					datos[0],        
					
					datos[1],        
					
					datos[2],     
					
					datos[3],     
					
					Double.parseDouble(datos[4]),
					
					datos[5],            
					
					Boolean.parseBoolean(datos[6]), 
					
					Integer.parseInt(datos[7]),  
					
					Integer.parseInt(datos[8]),  
					
					Boolean.parseBoolean(datos[11])    
					
			);
 
			articulo.setEstaReservado(Boolean.parseBoolean(datos[9]));
 
			mapaCatalogo.put(articulo.getSerialNumber(), articulo);
 
		} catch (Exception e) {
 
			System.out.println("Error: " + e.getMessage());
 
		}
	}
}