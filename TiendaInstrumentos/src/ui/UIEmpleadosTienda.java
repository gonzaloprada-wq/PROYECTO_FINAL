package ui;

import java.util.Scanner;

import excepciones.DescansandoInvalidException;
import excepciones.DniLongitudInvalidaException;
import excepciones.EmpleadoContratadoException;
import excepciones.TlfLongitudInvalidaException;
import excepciones.TrabajandoInvalidException;
import modelo.Empleados;
import modelo.deTienda;
import servicio.GestionPersonal;

/*
 * Clase UI para la gestion de empleados de Tienda.
 * Muestra el menu y delega en GestionPersonal.
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public class UIEmpleadosTienda {

    private final GestionPersonal gestionPersonal;
    private final Scanner scanner;

    /*====================CONSTRUCTORES=======================================================*/

    public UIEmpleadosTienda(GestionPersonal gestionPersonal, Scanner scanner) {
        this.gestionPersonal = gestionPersonal;
        this.scanner = scanner;
    }

    /*======================  FIN DE LOS CONSTRUCTORES  ======================================*/

    /**
     * Muestra y gestiona el menu de empleados de tienda.
     */
    public void mostrarMenu() {

        boolean volver = false;

        while (!volver) {
        	//Interfaz del selector hecho con IA (solo lineas, la logica es creada por el autor del proyecto)
            System.out.println("\n========================================");
            System.out.println("   MENU - EMPLEADOS DE TIENDA");
            System.out.println("========================================");
            System.out.println(" 1. Ver todos los empleados de tienda");
            System.out.println(" 2. Registrar venta");
            System.out.println(" 3. Fichar entrada");
            System.out.println(" 4. Fichar salida");
            System.out.println(" 5. Ver trabajo realizado (por DNI)");
            System.out.println(" 0. Volver");
            System.out.println("========================================");
            System.out.print("Selecciona una opcion: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1": verTodosEmpleadosTienda(); break;
                case "2": registrarVenta(); break;
                case "3": ficharEntrada(); break;
                case "4": ficharSalida(); break;
                case "5": verTrabajoRealizado(); break;
                case "0": volver = true; break;
                default:
                    System.out.println("[!] Opcion no valida.");
            }
        }
    }

    // ---------------------------------------------------------------
    // OPCIONES DEL MENU
    // ---------------------------------------------------------------

    private void verTodosEmpleadosTienda() {
        System.out.println("\n--- Empleados de Tienda ---");
        gestionPersonal.filtrarEmpleadosTiendaImprimir();
    }

    private void registrarVenta() {
        try {
            System.out.print("DNI del empleado: ");
            String dni = scanner.nextLine().trim();

            Empleados emp = gestionPersonal.buscarEmpleado(dni);

            if (emp == null) {
                System.out.println("[!] No se encontro ningun empleado con ese DNI.");
                return;
            }

            if (!(emp instanceof deTienda)) {
                System.out.println("[!] El empleado encontrado no es de tienda.");
                return;
            }

            System.out.print("Precio del articulo vendido (€): ");
            double precio = Double.parseDouble(scanner.nextLine().trim());

            deTienda empTienda = (deTienda) emp;
            empTienda.Incrementar(precio);

            System.out.println("[OK] Venta registrada. Ventas totales: " + empTienda.getNumeroVentas()
                    + " | Dinero generado: " + empTienda.getDineroVentas() + " €");

        } catch (NumberFormatException e) {
            System.out.println("[!] Precio invalido: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
        }
    }

    private void ficharEntrada() {
        try {
            System.out.print("DNI del empleado: ");
            String dni = scanner.nextLine().trim();

            Empleados emp = gestionPersonal.buscarEmpleado(dni);

            if (emp == null) {
                System.out.println("[!] No se encontro ningun empleado con ese DNI.");
                return;
            }

            emp.ficharEntrada();
            System.out.println("[OK] Entrada registrada para " + emp.getNombre() + " " + emp.getApellido() + ".");

        } catch (TrabajandoInvalidException e) {
            System.out.println("[!] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
        }
    }

    private void ficharSalida() {
        try {
            System.out.print("DNI del empleado: ");
            String dni = scanner.nextLine().trim();

            Empleados emp = gestionPersonal.buscarEmpleado(dni);

            if (emp == null) {
                System.out.println("[!] No se encontro ningun empleado con ese DNI.");
                return;
            }

            emp.ficharSalida();
            System.out.println("[OK] Salida registrada para " + emp.getNombre() + ". Dias trabajados: " + emp.getDiasTrabajados());

        } catch (DescansandoInvalidException e) {
            System.out.println("[!] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
        }
    }

    private void verTrabajoRealizado() {
        try {
            System.out.print("DNI del empleado: ");
            String dni = scanner.nextLine().trim();

            Empleados emp = gestionPersonal.buscarEmpleado(dni);

            if (emp == null) {
                System.out.println("[!] No se encontro ningun empleado con ese DNI.");
                return;
            }

            emp.trabajoRealizado();

        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
        }
    }
}