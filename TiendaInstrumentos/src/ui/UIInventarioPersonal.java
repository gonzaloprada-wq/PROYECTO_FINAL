package ui;

import java.util.Scanner;

import modelo.dePersonal;
import servicio.GestionCatalogoPersonal;

/*
 * Clase UI para la gestion de articulos del personal.
 * Delega en GestionCatalogoPersonal.
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public class UIInventarioPersonal {

    private final GestionCatalogoPersonal gestionPersonal;
    private final Scanner scanner;

    /*====================CONSTRUCTORES=======================================================*/

    public UIInventarioPersonal(GestionCatalogoPersonal gestionPersonal, Scanner scanner) {
        this.gestionPersonal = gestionPersonal;
        this.scanner = scanner;
    }

    /*======================  FIN DE LOS CONSTRUCTORES  ======================================*/

    /**
     * Muestra y gestiona el menu de articulos del personal.
     */
    public void mostrarMenu() {

        boolean volver = false;

        while (!volver) {
        	//Interfaz del selector hecho con IA (solo lineas, la logica es creada por el autor del proyecto)
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

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1": verTodos(); break;
                case "2": buscarArticulo(); break;
                case "3": añadirArticulo(); break;
                case "4": eliminarArticulo(); break;
                case "5": calcularPrecioTotal(); break;
                case "6": guardarDatos(); break;
                case "0": volver = true; break;
                default:
                    System.out.println("[!] Opcion no valida.");
            }
        }
    }

    // ---------------------------------------------------------------
    // OPCIONES DEL MENU
    // ---------------------------------------------------------------

    private void verTodos() {
        System.out.println("\n--- Articulos del personal ---");
        gestionPersonal.imprimirTodos();
    }

    private void buscarArticulo() {
        try {
            System.out.print("Numero de serie (S/N): ");
            String sn = scanner.nextLine().trim();

            dePersonal articulo = gestionPersonal.buscarArticulo(sn);

            if (articulo == null) {
                System.out.println("[!] No se encontro ningun articulo con S/N: " + sn);
            } else {
                System.out.println(articulo.toString());
                articulo.imprimirLocalizacion();
            }

        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
        }
    }

    private void añadirArticulo() {
        try {
            System.out.println("\n--- Añadir articulo de personal ---");

            System.out.print("Numero de serie (S/N): ");
            String sn = scanner.nextLine().trim();

            System.out.print("Marca: ");
            String marca = scanner.nextLine().trim();

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();

            System.out.print("Modelo: ");
            String modelo = scanner.nextLine().trim();

            System.out.print("Precio (€): ");
            double precio = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Fecha de adquisicion (dd-mm-aaaa): ");
            String fecha = scanner.nextLine().trim();

            System.out.print("¿En caja? (true/false): ");
            boolean enCaja = Boolean.parseBoolean(scanner.nextLine().trim());

            System.out.print("Nombre del dueño: ");
            String dueno = scanner.nextLine().trim();

            dePersonal nuevo = new dePersonal(sn, marca, nombre, modelo, precio, fecha, enCaja, dueno);
            gestionPersonal.añadirArticulo(nuevo);

            System.out.println("[OK] Articulo añadido al inventario personal de " + dueno + ".");

        } catch (NumberFormatException e) {
            System.out.println("[!] Formato numerico incorrecto: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
        }
    }

    private void eliminarArticulo() {
        try {
            System.out.print("Numero de serie (S/N) a eliminar: ");
            String sn = scanner.nextLine().trim();

            if (gestionPersonal.buscarArticulo(sn) == null) {
                System.out.println("[!] No se encontro ningun articulo con S/N: " + sn);
                return;
            }

            gestionPersonal.eliminarArticulo(sn);
            System.out.println("[OK] Articulo " + sn + " eliminado del inventario personal.");

        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
        }
    }

    private void calcularPrecioTotal() {
        try {
            double total = gestionPersonal.calcularPrecioTotal();
            System.out.println("[OK] Precio total del inventario personal: " + total + " €");
        } catch (Exception e) {
            System.out.println("[!] Error al calcular: " + e.getMessage());
        }
    }

    private void guardarDatos() {
        try {
            gestionPersonal.guardarDatosPersonal();
            System.out.println("[OK] Datos del inventario personal guardados en fichero.");
        } catch (Exception e) {
            System.out.println("[!] Error al guardar: " + e.getMessage());
        }
    }
}