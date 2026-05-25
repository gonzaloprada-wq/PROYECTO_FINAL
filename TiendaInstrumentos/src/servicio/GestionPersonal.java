package servicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import excepciones.ContrasenaInvalidaException;
import excepciones.EmpleadoContratadoException;
import excepciones.EmpleadoDespedidoException;
import modelo.Empleados;
import modelo.Jefe;
import modelo.deAlmacen;
import modelo.deTienda;
/*
 * Clase que representa la gestion de los integrantes del personal.
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public class GestionPersonal implements Comparator<Empleados> {

	private LinkedHashMap<String, Empleados> mapaEmpleados = new LinkedHashMap<>();

	private static final String FICHERO_EMPLEADOS = "src/repositorio/empleados.txt";

	private static final String FICHERO_CONTRASEÑA = "src/recursos/contraseña.txt";
	
    /*====================CONSTRUCTORES=======================================================*/
	
	public GestionPersonal() {

	}
	
    /*======================   FIN DE LOS CONSTRUCTORES      =============================*/
	
	//Gets y Sets
	public LinkedHashMap<String, Empleados> getMapaEmpleados() {

		return mapaEmpleados;

	}

	public void setMapaEmpleados(LinkedHashMap<String, Empleados> mapaEmpleados) {

		this.mapaEmpleados = mapaEmpleados;

	}
	
	 /**
     * Comprueba mediante un scanneo a consola para comprobar si la constraseña añadida es la misma a la que se encuentra guardada
     *
     * @param nignuno
     * 
     * @return void
     */
	public void comprobarContraseña() throws ContrasenaInvalidaException {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Introduce la contraseña actual: ");

		String contraseñaIntroducida = scanner.nextLine();

		if (!Jefe.getInstancia().getContraseña().equals(contraseñaIntroducida)) {

			throw new ContrasenaInvalidaException();
		}
	}
	 /**
     * Realiza un cambio de contraseña mediante un escano
     *
     * @param articulo Elemento que se va a añadir.
     * 
     * @return void
     */
	public void cambiarContraseña() {

		Scanner scanner = new Scanner(System.in);

		String contraseña1;

		String contraseña2;

		do {
			System.out.println("Introduce la nueva contraseña (deben ser iguales): ");

			contraseña1 = scanner.nextLine();

			System.out.println("Repite la nueva contraseña: ");

			contraseña2 = scanner.nextLine();

		} while (!contraseña1.equals(contraseña2));

		actualizacionContraseña(contraseña1);
	}

	 /**
     * Actualiza la contraseña intermente en el caso de que sea jefe
     *
     * @param contraseñaNueva: la contraseña que se va a añadir
     * 
     * @return void
     */
	public void actualizacionContraseña(String contraseñaNueva) {

		Jefe.getInstancia().setContraseña(contraseñaNueva);

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FICHERO_CONTRASEÑA))) {

			bufferedWriter.write(contraseñaNueva);

		} catch (IOException e) {

			System.out.println("Error: " + e.getMessage());

		}
	}
	 
	/**
     * Añade un empleado a los datos para su guardado siempre que no este dentro mediante DNI
     * En el caso de que tenga DNI hace un throw
     *
     * @param nuevoEmpleado: el empleado que se va a añadir
     * 
     * @return void
     */
	public void contratarEmpleado(Empleados nuevoEmpleado) throws EmpleadoContratadoException {

		if (buscarEmpleado(nuevoEmpleado.getDni()) == null) {

			getMapaEmpleados().put(nuevoEmpleado.getDni(), nuevoEmpleado);

		} else {

			throw new EmpleadoContratadoException();

		}

	}

	/**
     * Despide un empleado buscandolo por su dni.
     * En el caso de que no exista envia una excepcion
     *
     * @param dni: el dni del empleado que se va a despedir
     * 
     * @return void
     */
	public void despedirEmpleado(String dni) throws EmpleadoDespedidoException {

		if (buscarEmpleado(dni) == null) {

			throw new EmpleadoDespedidoException();

		} else {

			getMapaEmpleados().remove(dni);

		}

	}
	/**
     * Devuelve un empleado segun el dni que se ingrese
     *
     * @param dni: el dni del empleado que se va a buscar
     * 
     * @return empleado: el empleado que se a encontrado, si no existe devolvera null
     * 
     *      */
	public Empleados buscarEmpleado(String dni) {

		return mapaEmpleados.get(dni);

	}

	/*Codigo basado en video del canal de youtube de: "Johan Mora Code"*/
	 /**
     * filtra empleados segun si son estancia deTienda
     *
     * @param Ninguno
     * 
     * @return void
     */
	public void filtrarEmpleadosTiendaImprimir() {
		
	    List<Empleados> lista = mapaEmpleados.values().stream()
	    		
	            .filter(empleado -> empleado instanceof deTienda)
	            
	            .collect(Collectors.toList());
	    
	    for (Empleados empleadoActual : lista) {
	    	
	        System.out.println(empleadoActual.toString());
	        
	    }
	}
	
	/*Codigo basado en video del canal de youtube de: "Johan Mora Code"*/
	 /**
     * filtra empleados segun si son estancia deAlmacen
     *
     * @param Ninguno
     * 
     * @return void
     */
	public void filtrarEmpleadosAlmacenImprimir() {
		
	    List<Empleados> lista = mapaEmpleados.values().stream()
	    		
	            .filter(empleado -> empleado instanceof deAlmacen)
	            
	            .collect(Collectors.toList());
	    
	    for (Empleados empleado : lista) {
	    	
	        System.out.println(empleado.toString());
	        
	    }
	}
	 /**
     * Esta funcion imprime todos los empleados de manera ordenada
     *
     * @param Ninguno
     * 
     * @return void
     */
	public void imprimirTodosOrdenados() {

		List<Empleados> listaOrdenada = ordenarPorDiasTrabajados();

		LinkedHashMap<String, Empleados> mapaOrdenado = new LinkedHashMap<>();

		for (Empleados empleado : listaOrdenada) {
			
			mapaOrdenado.put(empleado.getDni(), empleado);
		
		}

		setMapaEmpleados(mapaOrdenado);

		for (Empleados empleado : mapaEmpleados.values()) {
			System.out.println(empleado.toString());
		}
	}
	
	 /**
     * Esta funcion realiza un guardado de datos de empleados.
     * Segun si es instancia de tienda o de almacen realizara un guardado u otro
     *
     * @param Ninguno
     * 
     * @return void
     */
	public void guardarDatosEmpleados() {

		try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(FICHERO_EMPLEADOS))) {

			for (Empleados empleadoActual : mapaEmpleados.values()) {

				if (empleadoActual instanceof deTienda) {

					bufferWriter.write(lineaDeTienda((deTienda) empleadoActual));

				} else if (empleadoActual instanceof deAlmacen) {

					bufferWriter.write(lineaDeAlmacen((deAlmacen) empleadoActual));
				}

				bufferWriter.newLine();

			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	 /**
     * Esta funcion realiza la linea de guardado de tienda
     *
     * @param empleadoActual
     * 
     * @return lineaDeTienda: devuelve la linea de esta funcion.
     */
	private String lineaDeTienda(deTienda empleadoActual) {

		/*
		 * La siguiente linea "lineaActual" (solo linea, logica hecha por autor @Gonzalo
		 * Prada) de datos ha sido construido con la Inteligencia artificial "Claude"
		 * para ahorrar tiempo, en la fecha de 14/05/2026 y reescrito por autor @Gonzalo
		 * Prada | Ultima fecha de modificacion: 14/05/26
		 */
		String lineaActual = empleadoActual.getDni() + " - " + empleadoActual.getTelefono() + " - "
				+ empleadoActual.getNombre() + " - " + empleadoActual.getApellido() + " - "
				+ empleadoActual.getDireccion() + " - " + "deTienda" + " - " + empleadoActual.getSalarioBruto() + " - "
				+ empleadoActual.getDiasDeVacacionesRestantes() + " - " + empleadoActual.isTieneSeguro() + " - "
				+ empleadoActual.getEstaDePrueba() + " - " + empleadoActual.getFechaInicio() + " - "
				+ empleadoActual.getIsTrabajando() + " - " + empleadoActual.getDiasTrabajados() + " - "
				+ empleadoActual.getNumeroVentas() + " - " + empleadoActual.getDineroVentas();

		return lineaActual;
	}
	 /**
     * Esta funcion realiza la linea de guardado de almacen
     *
     * @param empleadoActual
     * 
     * @return lineaDeEmpleado: devuelve la linea de esta funcion.
     */
	private String lineaDeAlmacen(deAlmacen empleadoActual) {

		/*
		 * La siguiente linea "lineaActual" (solo linea, logica hecha por autor @Gonzalo
		 * Prada) de datos ha sido construido con la Inteligencia artificial "Claude"
		 * para ahorrar tiempo, en la fecha de 14/05/2026 y reescrito por autor @Gonzalo
		 * Prada | Ultima fecha de modificacion: 14/05/26
		 */
		String lineaActual = empleadoActual.getDni() + " - " + empleadoActual.getTelefono() + " - "
				+ empleadoActual.getNombre() + " - " + empleadoActual.getApellido() + " - "
				+ empleadoActual.getDireccion() + " - " + "deAlmacen" + " - " + empleadoActual.getSalarioBruto() + " - "
				+ empleadoActual.getDiasDeVacacionesRestantes() + " - " + empleadoActual.isTieneSeguro() + " - "
				+ empleadoActual.getEstaDePrueba() + " - " + empleadoActual.getFechaInicio() + " - "
				+ empleadoActual.getIsTrabajando() + " - " + empleadoActual.getDiasTrabajados() + " - "
				+ empleadoActual.getNumeroBultos() + " - " + empleadoActual.getPesoCargado();

		return lineaActual;
	}

	 /**
     * Esta funcion realiza una implementacion de datos de empleados .
     * 
     *
     * @param Ninguno
     * 
     * @return void
     */
	public void implementarDatosEmpleados() {

	    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FICHERO_EMPLEADOS))) {

	        String lineaLeida;

	        while ((lineaLeida = bufferedReader.readLine()) != null) {

	            String[] datosDelEmpleado = lineaLeida.split(" - ");

	            // FabricaEmpleados decide si es deTienda o deAlmacen segun la posicion 5
	            Empleados empleadoCargado = FabricaEmpleados.crear(datosDelEmpleado);

	            if (empleadoCargado != null) {

	                mapaEmpleados.put(empleadoCargado.getDni(), empleadoCargado);

	            } else {

	                System.out.println("No se pudo cargar el empleado de la linea: " + lineaLeida);

	            }
	        }

	    } catch (Exception e) {

	        System.out.println("Error: " + e.getMessage());

	    }
	}
	
	 /**
     * Esta funcion realiza una implementacion de datos de empleados .
     * 
     *
     * @param datosConstructor: array con los datos de tienda para llamar a su constructor y añadirlo al mapa de empleddo
     * 
     * @return void
     */
	private void reconstruirDeTienda(String[] datosConstructor) {
		try {
			mapaEmpleados.put(datosConstructor[0], new deTienda(datosConstructor));
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void reconstruirDeAlmacen(String[] datosConstructor) {
		try {
			mapaEmpleados.put(datosConstructor[0], new deAlmacen(datosConstructor));
		} catch (Exception e) {
			System.out.println("Error! : " + e.getMessage());
		}
	}

	@Override
	public int compare(Empleados empleado1, Empleados empleado2) {
		
		return Integer.compare(empleado1.getDiasTrabajados(), empleado2.getDiasTrabajados());
		
	}

	
	public List<Empleados> ordenarPorDiasTrabajados() {
		
	    List<Empleados> listaOrdenada = mapaEmpleados.values().stream()
	            .sorted(this)
	            .collect(Collectors.toList());

	    LinkedHashMap<String, Empleados> mapaOrdenado = new LinkedHashMap<>();
	    
	    for (Empleados empleado : listaOrdenada) {
	    	
	        mapaOrdenado.put(empleado.getDni(), empleado);       
	    }
	    
	    setMapaEmpleados(mapaOrdenado);
	    
	    return listaOrdenada;
	}

}