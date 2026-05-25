package ui;

import java.util.Scanner;

import excepciones.ContrasenaInvalidaException;
import excepciones.DniLongitudInvalidaException;
import excepciones.EmpleadoContratadoException;
import excepciones.EmpleadoDespedidoException;
import excepciones.TlfLongitudInvalidaException;
import modelo.Jefe;
import modelo.deTienda;
import modelo.deAlmacen;
import servicio.GestionPersonal;

/*
 * Clase UI para la gestion del personal (empleados).
 * Requiere contrasena del jefe para acceder.
 * Delega en UIEmpleadosTienda y UIEmpleadosAlmacen.
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public class UIEmpleados {

    private final GestionPersonal gestionPersonal;
    
    private final Scanner reader;

    private final UIEmpleadosTienda uiTienda;
    
    private final UIEmpleadosAlmacen uiAlmacen;

    /*====================CONSTRUCTORES=======================================================*/

    public UIEmpleados(GestionPersonal gestionPersonal, Scanner scanner) {
    	
        this.gestionPersonal = gestionPersonal;
        
        this.reader = scanner;
        
        this.uiTienda = new UIEmpleadosTienda(gestionPersonal, scanner);
        
        this.uiAlmacen = new UIEmpleadosAlmacen(gestionPersonal, scanner);
        
    }

    /*======================  FIN DE LOS CONSTRUCTORES  ======================================*/

    /**
     * Pide la contrasena al jefe y, si es correcta, abre el menu de empleados.
     */
    public void mostrarMenu() {

        if (!autenticar()) {
            return;
        }

        boolean volver = false;

        while (!volver) {
        	
        	//Interfaz del selector hecho con IA (solo lineas, la logica es creada por el autor del proyecto)
        	
            System.out.println("\n========================================");
            System.out.println("   MENU - GESTION DE EMPLEADOS");
            System.out.println("========================================");
            System.out.println(" 1. Empleados de Tienda");
            System.out.println(" 2. Empleados de Almacen");
            System.out.println(" 3. Contratar nuevo empleado");
            System.out.println(" 4. Despedir empleado");
            System.out.println(" 5. Ver todos los empleados (ordenados)");
            System.out.println(" 6. Cambiar contrasena");
            System.out.println(" 7. Guardar datos de empleados");
            System.out.println(" 0. Volver al menu principal");
            System.out.println("========================================");
            System.out.print("Selecciona una opcion: ");

            String opcion = reader.nextLine().trim();

            switch (opcion) {
            
                case "1": 
                	
                	uiTienda.mostrarMenu(); 
                	
                	break;
                	
                case "2": 
                	
                	uiAlmacen.mostrarMenu(); 
                	
                	break;
                	
                case "3": 
                	
                	contratarEmpleado(); 
                	
                	break;
                	
                case "4": 
                	
                	despedirEmpleado();
                	
                	break;
                	
                case "5":
                	
                	verTodosOrdenados();
                	
                	break;
                	
                case "6": 
                	
                	cambiarContrasena(); 
                	
                	break;
                	
                case "7":
                	
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
    // AUTENTICACION
    // ---------------------------------------------------------------

    /**
     * Solicita la contraseñaa y la comprueba contra la del Jefe. 
     * Usa catch para determinar ciertas excepciones
     *
     * @return true si la contrasena es correcta, false en caso contrario.
     */
    private boolean autenticar() {
    	
    	boolean acceso=false;
    	
        try {
        	
        	
        	
            System.out.println("\n--- Acceso restringido: area de empleados ---");
            
            System.out.print("Introduce la contrasena del jefe: ");
            
            String contraseña = reader.nextLine().trim();
            

            if (!Jefe.getInstancia().getContraseña().equals(contraseña)) {
            	
                throw new ContrasenaInvalidaException();
                
            }

            System.out.println("[OK] Acceso concedido.");
            
            acceso= true;

        } catch (ContrasenaInvalidaException e) {
        	
            System.out.println("[!] " + e.getMessage());
            
         
            
        } catch (Exception e) {
        	
            System.out.println("[!] Error inesperado en autenticacion: " + e.getMessage());
        
        }
        
        return acceso;
    }

    // ---------------------------------------------------------------
    // OPCIONES DEL MENU
    // ---------------------------------------------------------------

    /**
     * Solicita mediante scanner los datos del dni,
     * 
     * @return void
     */
    private void contratarEmpleado() {
    	
        try {
        	
            System.out.println("\nContratar nuevo empleado");
            
            System.out.print("DNI (9 caracteres): ");
            
            String dni = reader.nextLine().trim();
        

            System.out.print("Telefono (9 digitos): ");
            
            String tlf = reader.nextLine().trim();
            

            System.out.print("Nombre: ");
            
            String nombre = reader.nextLine().trim();
            

            System.out.print("Apellido: ");
            
            String apellido = reader.nextLine().trim();
            

            System.out.print("Direccion: ");
            
            String direccion = reader.nextLine().trim();
            

            System.out.print("Tipo (1=Tienda / 2=Almacen): ");
            
            String tipo = reader.nextLine().trim();
            

            if (tipo.equals("1")) {

                deTienda nuevo = new deTienda(dni, tlf, nombre, apellido, direccion);
                gestionPersonal.contratarEmpleado(nuevo);
                System.out.println("[OK] Empleado de tienda contratado: " + nombre + " " + apellido);

            } else if (tipo.equals("2")) {

                deAlmacen nuevo = new deAlmacen(dni, tlf, nombre, apellido, direccion);
                gestionPersonal.contratarEmpleado(nuevo);
                System.out.println("[OK] Empleado de almacen contratado: " + nombre + " " + apellido);

            } else {
                System.out.println("[!] Tipo de empleado no valido.");
            }

        } catch (DniLongitudInvalidaException e) {
        	
            System.out.println(e.getMessage());
            
        } catch (TlfLongitudInvalidaException e) {
        	
            System.out.println(e.getMessage());
            
        } catch (EmpleadoContratadoException e) {
        	
            System.out.println(e.getMessage());
            
        } catch (Exception e) {
        	
            System.out.println(e.getMessage());
            
        }
    }

    private void despedirEmpleado() {
    	
        try {
        	
            System.out.println("\nDespedir empleado");
            
            System.out.print("DNI del empleado a despedir: ");
            
            String dni = reader.nextLine().trim();

            gestionPersonal.despedirEmpleado(dni);
            
            System.out.println("[OK] Empleado con DNI " + dni + " ha sido despedido.");

        } catch (EmpleadoDespedidoException e) {
            System.out.println("[!] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
        }
    }

    private void verTodosOrdenados() {
    	
        System.out.println("\n--- Todos los empleados (ordenados por dias trabajados) ---");
        
        gestionPersonal.imprimirTodosOrdenados();
        
    }

    private void cambiarContrasena() {
    	
        try {
        	
            gestionPersonal.cambiarContraseña();
            
            System.out.println("[OK] Contrasena actualizada correctamente.");
            
        } catch (Exception e) {
        	
            System.out.println("[!] Error al cambiar contrasena: " + e.getMessage());
            
        }
    }

    private void guardarDatos() {
        try {
            gestionPersonal.guardarDatosEmpleados();
            System.out.println("[OK] Datos de empleados guardados en fichero.");
        } catch (Exception e) {
            System.out.println("[!] Error al guardar datos: " + e.getMessage());
        }
    }
}