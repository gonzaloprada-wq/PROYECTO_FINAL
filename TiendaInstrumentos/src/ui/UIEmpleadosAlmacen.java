package ui;

import java.util.Scanner;

import excepciones.DescansandoInvalidException;
import excepciones.TrabajandoInvalidException;
import modelo.Empleados;
import modelo.deAlmacen;
import servicio.GestionPersonal;

/*
 * Clase UI para la gestion de empleados de Almacen.
 * Muestra el menu y delega en GestionPersonal.
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public class UIEmpleadosAlmacen {

    private final GestionPersonal gestionPersonal;
    private final Scanner scanner;

    /*====================CONSTRUCTORES=======================================================*/

    public UIEmpleadosAlmacen(GestionPersonal gestionPersonal, Scanner scanner) {
        this.gestionPersonal = gestionPersonal;
        this.scanner = scanner;
    }

    /*======================  FIN DE LOS CONSTRUCTORES  ======================================*/

    /**
     * Muestra y gestiona el menu de empleados de almacen.
     */
    public void mostrarMenu() {

        boolean volver = false;

        while (!volver) {
        	
        	//Interfaz del selector hecho con IA (solo lineas, la logica es creada por el autor del proyecto)
            System.out.println("\n========================================");
            System.out.println("   MENU - EMPLEADOS DE ALMACEN");
            System.out.println("========================================");
            System.out.println(" 1. Ver todos los empleados de almacen");
            System.out.println(" 2. Registrar bulto (Incrementar)");
            System.out.println(" 3. Fichar entrada");
            System.out.println(" 4. Fichar salida");
            System.out.println(" 5. Ver trabajo realizado (por DNI)");
            System.out.println(" 0. Volver");
            System.out.println("========================================");
            System.out.print("Selecciona una opcion: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
            
                case "1": 
                	
                	verTodosEmpleadosAlmacen();
                	
                	break;
                	
                case "2": 
                	
                	registrarBulto();
                	
                	break;
                	
                case "3": 
                	
                	ficharEntrada();
                	
                	break;
                	
                case "4": 
                	
                	ficharSalida();
                	
                	break;
                	
                case "5": 
                	
                	verTrabajoRealizado();
                	
                	break;
                	
                	
                case "0": 
                	
                	volver = true; 
                	
                	break;
                	
                default:
                	
                    System.out.println("Opcion no valida.");
            }
        }
    }

    // ---------------------------------------------------------------
    // OPCIONES DEL MENU
    // ---------------------------------------------------------------
    /**
     * Esto imprime todos los empleados que pertenezcan a almacén
     *      * 
     * @param ninguno
     * 
     * @return void
     */
    private void verTodosEmpleadosAlmacen() {
    	
        System.out.println("\nEmpleados de Almacen");      
        
        gestionPersonal.filtrarEmpleadosAlmacenImprimir();
    }
    
    /**
     * Esto registra un bulto preguntando sobre el dni de empleado
     * 
     * @param ninguno
     * 
     * @return void
     * 
     */
    private void registrarBulto() {
    	
        try {
        	
            System.out.print("DNI del empleado: ");
            
            String dni = scanner.nextLine().trim();

            Empleados empleado = gestionPersonal.buscarEmpleado(dni);

            if (empleado == null) {
            	
                System.out.println("[!] No se encontro ningun empleado con ese DNI.");
                
              
                
            }

            if (!(empleado instanceof deAlmacen)) {
            	
                System.out.println("[!] El empleado encontrado no es de almacen.");
                
                
                
            }

            System.out.print("Peso del bulto (kg): ");
            
            double peso = Double.parseDouble(scanner.nextLine().trim());
            

            deAlmacen empleadoAlmacen = (deAlmacen) empleado;
            
            empleadoAlmacen.Incrementar(peso);

            System.out.println("Bulto registrado. Total bultos: " + empleadoAlmacen.getNumeroBultos()
            
                    + " | Peso cargado: " + empleadoAlmacen.getPesoCargado() + " kg");
            

        } catch (NumberFormatException e) {
        	
            System.out.println("Peso invalido: " + e.getMessage());
            
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
            
            String dni = scanner.nextLine().trim();

            Empleados empleadoActual = gestionPersonal.buscarEmpleado(dni);

            if (empleadoActual == null) {
            	
                System.out.println("[!] No se encontro ningun empleado con ese DNI.");
                
               
            }

            empleadoActual.ficharEntrada();
            
            System.out.println("[OK] Entrada registrada para " + empleadoActual.getNombre() + " " + empleadoActual.getApellido() + ".");
            

        } catch (TrabajandoInvalidException e) {
        	
            System.out.println("" + e.getMessage());
            
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
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
            String dni = scanner.nextLine().trim();

            Empleados empleadoActual = gestionPersonal.buscarEmpleado(dni);

            if (empleadoActual == null) {
            	
                System.out.println("No se encontro ningun empleado con ese DNI.");
                
             
            }

            empleadoActual.ficharSalida();
            
            System.out.println("[OK] Salida registrada para " + empleadoActual.getNombre() + ". Dias trabajados: " + empleadoActual.getDiasTrabajados());

        } catch (DescansandoInvalidException e) {
        	
            System.out.println(e.getMessage());
            
        } catch (Exception e) {
        	
            System.out.println(e.getMessage());
            
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
            
            String dni = scanner.nextLine().trim();

            Empleados empleadoActual = gestionPersonal.buscarEmpleado(dni);

            if (empleadoActual == null) {
            	
                System.out.println(" No se encontro ningun empleado con ese DNI.");
                
            }

            empleadoActual.trabajoRealizado();

        } catch (Exception e) {
        	
            System.out.println("Error: " + e.getMessage());
        }
    }
}