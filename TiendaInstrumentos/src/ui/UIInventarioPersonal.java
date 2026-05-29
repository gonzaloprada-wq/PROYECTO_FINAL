package ui;

import java.util.Scanner;

import modelo.dePersonal;
import servicio.GestionCatalogoPersonal;

/**
 * Clase UI para la gestion de articulos del personal.
 * Delega en GestionCatalogoPersonal.
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public class UIInventarioPersonal {

	private final GestionCatalogoPersonal gestionPersonal;
	
	private final Scanner reader;

	/** ====================CONSTRUCTORES============================================**/

	public UIInventarioPersonal(GestionCatalogoPersonal gestionPersonal, Scanner reader) {
		
		this.gestionPersonal = gestionPersonal;
		
		this.reader = reader;
	}

	/** ====================== FIN DE LOS CONSTRUCTORES ====================================== */

	/**
	 * Muestra y gestiona el menu de articulos del personal.
	 */
	public void mostrarMenu() {

		boolean volver = false;

		while (!volver) {
			// Interfaz del selector hecho con IA (solo lineas, la logica es creada por el
			// autor del proyecto)
			System.out.println("\n========================================");
			System.out.println("   MENU - ARTICULOS DEL PERSONAL");
			System.out.println("========================================");
			System.out.println(" 1. Ver todos los articulos");
			System.out.println(" 2. Buscar articulo (por S/N)");
			System.out.println(" 3. Añadir articulo de personal");
			System.out.println(" 4. Eliminar articulo");
			System.out.println(" 5. Calcular precio total del inventario personal");
			System.out.println(" 6. Guardar datos en fichero");
			System.out.println(" 0. Volver");
			System.out.println("========================================");
			System.out.print("Selecciona una opcion: ");

			String opcion = reader.nextLine().trim();

			switch (opcion) {
			
			case "1":
				
				verTodos();
				
				break;
				
			case "2":
				
				buscarArticulo();
				
				break;
				
			case "3":
				
				añadirArticulo();
				
				break;
				
			case "4":
				
				eliminarArticulo();
				
				break;
				
			case "5":
				
				calcularPrecioTotal();
				
				break;
				
			case "6":
				
				guardarDatos();
				
				break;
				
			case "0":
				
				volver = true;
				
				break;
				
			default:
				
				System.out.println("[!] Opcion no valida.");
				
			}
		}
	}

	// ---------------------------------------------------------------
	// OPCIONES DEL MENU
	// ---------------------------------------------------------------
	/**
     * Esto imprime todos los articulos de personal
     * 
     * @param ninguno
     * 
     * @return void
     * 
     */
	private void verTodos() {
		
		System.out.println("\n--- Articulos del personal ---");
		
		gestionPersonal.imprimirTodos();
		
	}
	 /**
     * Esto imprime un string con los datos segun el trabajo que ha realizado dicho empleado
     * 
     * @param ninguno
     * 
     * @return void
     * 
     */
	private void buscarArticulo() {
		
		try {
			
			System.out.print("Numero de serie (S/N): ");
			
			String numeroSerial = reader.nextLine().trim();

			dePersonal articuloActual = gestionPersonal.buscarArticulo(numeroSerial);

			if (articuloActual == null) {
				
				System.out.println("No se encontro ningun articulo con S/N: " + numeroSerial);
				
			} else {
				
				System.out.println(articuloActual.toString());
				
				articuloActual.imprimirLocalizacion();
				
			}

		} catch (Exception e) {
			
			System.out.println("Error: " + e.getMessage());
			
		}
	}
	 /**
     * Esto pregunta todos los datos necesarios para registrar un articulo
     * 
     * @param ninguno
     * 
     * @return void
     * 
     */
	private void añadirArticulo() {
		
		try {
			
			System.out.println("\n--- Añadir articulo de personal ---");

			System.out.print("Numero de serie (S/N): ");
			
			String serialNumber = reader.nextLine().trim();

			
			System.out.print("Marca: ");
			
			String marca = reader.nextLine().trim();

			
			System.out.print("Nombre: ");
			
			String nombre = reader.nextLine().trim();

			
			System.out.print("Modelo: ");
			
			String modelo = reader.nextLine().trim();

			
			System.out.print("Precio (€): ");
			
			double precio = Double.parseDouble(reader.nextLine().trim());

			
			System.out.print("Fecha de adquisicion (dd-mm-aaaa): ");
			
			String fecha = reader.nextLine().trim();

			
			System.out.print("¿En caja? (true/false): ");
			
			boolean enCaja = Boolean.parseBoolean(reader.nextLine().trim());

			
			System.out.print("Nombre del dueño: ");
			
			String dueno = reader.nextLine().trim();

			//LLAMA AL CONSTRUCTOR
			dePersonal nuevo = new dePersonal(serialNumber, marca, nombre, modelo, precio, fecha, enCaja, dueno);
			
			gestionPersonal.añadirArticulo(nuevo);

			System.out.println("Articulo añadido al inventario personal de " + dueno);

		} catch (NumberFormatException e) {
			
			System.out.println("Formato numerico incorrecto: " + e.getMessage());
			
		} catch (Exception e) {
			
			System.out.println("Error: " + e.getMessage());
			
		}
	}
	 /**
     * Esto pregunta el sn del articulo
     * Lo busca
     * Si encuentra un articulo con dicho sn 
     * 
     * @param ninguno
     * 
     * @return void
     * 
     */
	private void eliminarArticulo() {
		
		try {
			
			System.out.print("Numero de serie (S/N) a eliminar: ");
			
			String serialNumber = reader.nextLine().trim();

			if (gestionPersonal.buscarArticulo(serialNumber) == null) {
				
				System.out.println("No se encontro ningun articulo con S/N: " + serialNumber);
				
			}

			gestionPersonal.eliminarArticulo(serialNumber);
			
			System.out.println("Articulo " + serialNumber + " eliminado del inventario personal.");

		} catch (Exception e) {
			
			System.out.println("Error: " + e.getMessage());
			
		}
	}
	 /**
     * Esto calcula el precio total del inventario personal (cuanto hay gastado)
     * 
     * @param ninguno
     * 
     * @return void
     * 
     */
	private void calcularPrecioTotal() {
		
		try {
			
			double total = gestionPersonal.calcularPrecioTotal();
			
			System.out.println("Precio total del inventario personal: " + total + " €");
			
		} catch (Exception e) {
		
			System.out.println("Error: " + e.getMessage());
			
		}
	}
	 /**
     * Esto se deduca a guardar los datos
     * 
     * @param ninguno
     * 
     * @return void
     * 
     */
	private void guardarDatos() {
		
		try {
			
			gestionPersonal.guardarDatosPersonal();
			
			System.out.println("Datos del inventario personal guardados en fichero.");
			
		} catch (Exception e) {
			
			System.out.println("Error: " + e.getMessage());
			
		}
	}
}