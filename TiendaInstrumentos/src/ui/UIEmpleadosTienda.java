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

/**
 * Clase UI para la gestion de empleados de Tienda.
 * Muestra el menu y delega en GestionPersonal.
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public class UIEmpleadosTienda {

    private final GestionPersonal gestionPersonal;
    
    private final Scanner reader;

    /*====================CONSTRUCTORES=======================================================*/

    public UIEmpleadosTienda(GestionPersonal gestionPersonal, Scanner scanner) {
        this.gestionPersonal = gestionPersonal;
        this.reader = scanner;
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

            String opcion = reader.nextLine().trim();

            switch (opcion) {
            
                case "1":

                	verTodosEmpleadosTienda(); 
                	
                		break;
                		
                case "2":

                	registrarVenta(); 
                	
                	break;
                	
                case "3": 
                	
                	ficharEntrada();
                	
                	break;
                	
                case "4":
                	
                	ficharSalida();
                	
                	break;
                	
                case "5": verTrabajoRealizado();
                
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
     * Esto imprime todos los empleados que pertenezcan a tienda
     *      * 
     * @param ninguno
     * 
     * @return void
     */
    private void verTodosEmpleadosTienda() {
    	
        System.out.println("Empleados de Tienda");
        
        gestionPersonal.filtrarEmpleadosTiendaImprimir();
        
    }
    /**
     * Esto registra una venta preguntando sobre el dni de empleado y el precio de la venta
     * 
     * @param ninguno
     * 
     * @return void
     * 
     */
    private void registrarVenta() {
    	
        try {
        	
            System.out.print("DNI del empleado: ");
            
            String dni = reader.nextLine().trim();

            Empleados empleadoActual = gestionPersonal.buscarEmpleado(dni);

            if (empleadoActual == null) {
            	
                System.out.println("No se encontro ningun empleado con ese DNI.");
                
              
            }

            if (!(empleadoActual instanceof deTienda)) {
            	
                System.out.println("El empleado encontrado no es de tienda.");
                
          
            }

            System.out.print("Precio del articulo vendido (€): ");
            
            double precio = Double.parseDouble(reader.nextLine().trim());
            

            deTienda empleadoTienda = (deTienda) empleadoActual;
            
            empleadoTienda.Incrementar(precio);

            System.out.println("Venta registrada. Ventas totales: " + empleadoTienda.getNumeroVentas()
            
                    + " | Dinero generado: " + empleadoTienda.getDineroVentas() + " €");

        } catch (NumberFormatException e) {
        	
            System.out.println("Precio invalido: " + e.getMessage());
            
        } catch (Exception e) {
        	
            System.out.println("Error: " + e.getMessage());
            
        }
    }
    
    /**
     * Esto registra un bulto preguntando sobre el dni de empleado
     * 
     * @param ninguno
     * 
     * @return void
     * 
     */
    private void ficharEntrada() {
    	
        try {
        	
            System.out.print("DNI del empleado: ");
            
            String dni = reader.nextLine().trim();

            Empleados empleadoTienda = gestionPersonal.buscarEmpleado(dni);

            if (empleadoTienda == null) {
            	
                System.out.println("No se encontro ningun empleado con ese DNI.");
                
                
            }

            empleadoTienda.ficharEntrada();
            
            System.out.println("Entrada registrada para " + empleadoTienda.getNombre() + " " + empleadoTienda.getApellido());

        } catch (TrabajandoInvalidException e) {
        	
            System.out.println(e.getMessage());
            
        } catch (Exception e) {
        	
            System.out.println("Error: " + e.getMessage());
            
        }
    }

    
    /**
     * Esto crea el metodo de fichar salida, añadiendo un dia de trabajo cuando se fiche la salida
     * 
     * @param ninguno
     * 
     * @return void
     * 
     */
    private void ficharSalida() {
    	
        try {
        	
            System.out.print("DNI del empleado: ");
            
            String dni = reader.nextLine().trim();

            Empleados empleadoTienda = gestionPersonal.buscarEmpleado(dni);

            if (empleadoTienda == null) {
            	
                System.out.println("[!] No se encontro ningun empleado con ese DNI.");
                
              
            }

            empleadoTienda.ficharSalida();
            
            System.out.println("Salida registrada para " + empleadoTienda.getNombre() + ". Dias trabajados: " + empleadoTienda.getDiasTrabajados());

        } catch (DescansandoInvalidException e) {
        	
            System.out.println(e.getMessage());
            
        } catch (Exception e) {
        	
            System.out.println("Error: " + e.getMessage());
            
        }
    }
    /**
     * Esto imprime un string con los datos segun el trabajo que ha realizado dicho empleado
     * 
     * @param ninguno
     * 
     * @return void
     * 
     */
    private void verTrabajoRealizado() {
    	
        try {
        	
            System.out.print("DNI del empleado: ");
            
            String dni = reader.nextLine().trim();

            Empleados empleadoTienda = gestionPersonal.buscarEmpleado(dni);

            if (empleadoTienda == null) {
            	
                System.out.println("[!] No se encontro ningun empleado con ese DNI.");
                            
            }

            empleadoTienda.trabajoRealizado();

        } catch (Exception e) {
        	
            System.out.println("Error: " + e.getMessage());
            
        }
    }
}