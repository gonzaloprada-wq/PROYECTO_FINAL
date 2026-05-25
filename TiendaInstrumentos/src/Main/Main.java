package Main;

import java.util.Scanner;

import excepciones.DniLongitudInvalidaException;
import excepciones.TlfLongitudInvalidaException;
import modelo.Jefe;
import servicio.GestionCatalogo;
import servicio.GestionCatalogoPersonal;
import servicio.GestionPersonal;
import servicio.GestionProveedores;
import ui.UIEmpleados;
import ui.UIInventario;

/*
 * Punto de entrada de la aplicacion.
 * Inicializa todas las gestiones, carga los datos persistidos
 * y muestra el menu principal.
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
	
		//Se debe inicializar el jefe obligatoriamente
		Jefe jefe = inicializarJefe(scanner);

		//Se debe crear un jefe al principio, si no el proyecto no arrancara
		if (jefe == null) {
			
			System.out.println("[FATAL] No se pudo inicializar el jefe. Cerrando aplicacion.");
	
		}

		GestionPersonal gestionPersonal = new GestionPersonal();
		
		GestionCatalogo gestionCatalogo = new GestionCatalogo();
		
		GestionCatalogoPersonal gestionCatalogoPersonal = new GestionCatalogoPersonal();
		
		GestionProveedores gestionProveedores = new GestionProveedores();

		//Se deben cargar los datos antes de empezar
		cargarDatos(gestionPersonal, gestionCatalogo, gestionCatalogoPersonal, gestionProveedores, jefe);

		//Creo los uis
		UIEmpleados uiEmpleados = new UIEmpleados(gestionPersonal, scanner);

		UIInventario uiInventario = new UIInventario(gestionCatalogo, gestionCatalogoPersonal, scanner);

		// ---------------------------------------------------------------
		// MENU PRINCIPAL
		// ---------------------------------------------------------------
		boolean salir = false;

		
		//Interfaz del selector hecho con IA (solo lineas, la logica es creada por el autor del proyecto)
		while (!salir) {

			System.out.println("\n╔══════════════════════════════════════╗");
			System.out.println("║     TIENDA DE INSTRUMENTOS           ║");
			System.out.println("║     Bienvenido, " + jefe.getNombre() + "                 ║");
			System.out.println("╠══════════════════════════════════════╣");
			System.out.println("║  1. Empleados                        ║");
			System.out.println("║  2. Inventario                       ║");
			System.out.println("║  0. Salir (guardar y cerrar)         ║");
			System.out.println("╚══════════════════════════════════════╝");
			System.out.print("Selecciona una opcion: ");

			String opcion = scanner.nextLine().trim();

			switch (opcion) {
			case "1":
				
				uiEmpleados.mostrarMenu();
				
				break;

			case "2":
				
				uiInventario.mostrarMenu();
				
				break;

			case "0":
				
				guardarDatos(gestionPersonal, gestionCatalogo, gestionCatalogoPersonal, gestionProveedores);
				
				System.out.println("\n[OK] Datos guardados.");
				salir = true;
				break;

			default:
				
				System.out.println("[!] Opcion no valida. (1, 2 o 0).");
				
			}
		}

		scanner.close();
	}

	// ---------------------------------------------------------------
	// INICIALIZACION DEL JEFE
	// ---------------------------------------------------------------

	/**
	 * Inicializa el Singleton del Jefe (ya que es obligatorio). 
	 * Primero intenta cargar la contrasena guardada en fichero. 
	 * Si el jefe ya existe (llamada repetida), lo devuelve directamente.
	 *
	 * @param scanner compartido de la aplicacion.
	 * 
	 * @return instancia del Jefe, o null si hay error.
	 * 
	 * (Tiene distintos catch para anunciar la funcionalidad 
	 */
	private static Jefe inicializarJefe(Scanner scanner) {
		
		try {
			
			// Datos fijos del jefe — en un proyecto real vendrian de fichero o config
			Jefe jefe = Jefe.getInstancia("12345678J", "600000000", "Jefe", "Principal", "Calle Mayor 1, Sevilla");
			

			// Cargar contrasena persistida si existe
			cargarContrasenaJefe(jefe);

			return jefe;

		} catch (DniLongitudInvalidaException e) {
			
			System.out.println("[!] Error al crear el jefe - DNI invalido: " + e.getMessage());
			
			return null;
			
		} catch (TlfLongitudInvalidaException e) {
			
			System.out.println("[!] Error al crear el jefe - Telefono invalido: " + e.getMessage());
			
			return null;
			
		} catch (Exception e) {
			
			System.out.println("[!] Error inesperado al inicializar el jefe: " + e.getMessage());
			
			return null;
			
		}
	}

	/**
	 * Carga la contrasena del jefe desde el fichero de recursos. Si no existe el
	 * fichero, mantiene la contrasena por defecto.
	 *
	 * @param jefe instancia del Jefe.
	 */
	private static void cargarContrasenaJefe(Jefe jefe) {
		try {
			
			java.io.BufferedReader bufferedReader = new java.io.BufferedReader(
					
					new java.io.FileReader("/recursos/contraseña.txt"));
			
			String contraseña = bufferedReader.readLine();
			

			if (contraseña != null && !contraseña.trim().isEmpty()) {
				
				jefe.setContraseña(contraseña.trim());
				
			}
			
		} catch (java.io.FileNotFoundException e) {
			
			// El fichero no existe todavia — se usa la contrasena por defecto "admin1234"
			System.out.println("[INFO] Fichero de contrasena no encontrado. Se usara la contrasena por defecto.");
			
		} catch (Exception e) {
			
			System.out.println("[!] Error al cargar contrasena: " + e.getMessage());
		}
	}

	// ---------------------------------------------------------------
	// CARGA DE DATOS
	// ---------------------------------------------------------------

	/**
	 * Carga todos los datos desde sus ficheros de persistencia. Cada carga esta
	 * envuelta en try-catch para no interrumpir el arranque.
	 */
	private static void cargarDatos(GestionPersonal gestionPersonal, GestionCatalogo gestionCatalogo,
			GestionCatalogoPersonal gestionCatalogoPersonal, GestionProveedores gestionProveedores, Jefe jefe) {
		try {
			gestionPersonal.implementarDatosEmpleados();
			System.out.println("[OK] Empleados cargados.");
		} catch (Exception e) {
			System.out.println("[!] No se pudieron cargar los empleados: " + e.getMessage());
		}

		try {
			gestionCatalogo.implementarDatosCatalogo();
			System.out.println("[OK] Catalogo cargado.");
		} catch (Exception e) {
			System.out.println("[!] No se pudo cargar el catalogo: " + e.getMessage());
		}

		try {
			
			gestionCatalogoPersonal.implementarDatosPersonal();
			
			System.out.println("[OK] Inventario personal cargado.");
			
		} catch (Exception e) {
			
			System.out.println("[!] No se pudo cargar el inventario personal: " + e.getMessage());
			
		}
		
		try {
			
			gestionProveedores.implementarDatosProveedores();
			
			System.out.println("[OK] Proveedores cargados.");
			
		} catch (Exception e) {
			
			System.out.println("[!] No se pudieron cargar los proveedores: " + e.getMessage());
			
		}
	}

	/**
	 * Carga la contrasena del jefe desde el fichero de recursos. 
	 * Si no existe el fichero, mantiene la contrasena por defecto.
	 * Contiene distintos try catch para poder mostrar e identificar en el caso de que algo no ocurra bien
	 *
	 * @param jefe instancia del Jefe.
	 * 
	 * @return void
	 */
	private static void guardarDatos(GestionPersonal gestionPersonal, GestionCatalogo gestionCatalogo,
			
			GestionCatalogoPersonal gestionCatalogoPersonal, GestionProveedores gestionProveedores) {
		
		try {
			
			gestionPersonal.guardarDatosEmpleados();
			
		} catch (Exception e) {
			
			System.out.println("[!] Error al guardar empleados: " + e.getMessage());
			
		}

		try {
			
			gestionCatalogo.guardarDatosCatalogo();
			
		} catch (Exception e) {
			
			System.out.println("[!] Error al guardar catalogo: " + e.getMessage());
			
		}

		try {
			
			gestionCatalogoPersonal.guardarDatosPersonal();
			
		} catch (Exception e) {
			
			System.out.println("[!] Error al guardar inventario personal: " + e.getMessage());
			
		}

		try {
			
			gestionProveedores.guardarDatosProveedores();
			
		} catch (Exception e) {
			
			System.out.println("[!] Error al guardar proveedores: " + e.getMessage());
			
		}
	}
}