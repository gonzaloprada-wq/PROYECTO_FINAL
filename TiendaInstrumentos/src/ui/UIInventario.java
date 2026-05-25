package ui;

import java.util.Scanner;

import servicio.GestionCatalogo;
import servicio.GestionCatalogoPersonal;

/*
 * Clase UI para el menu principal de Inventario.
 * Delega en UICatalogo y UIInventarioPersonal.
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public class UIInventario {

    private final UICatalogo uiCatalogo;
    private final UIInventarioPersonal uiPersonal;
    private final Scanner scanner;

    /*====================CONSTRUCTORES=======================================================*/

    public UIInventario(GestionCatalogo gestionCatalogo,
                        GestionCatalogoPersonal gestionCatalogoPersonal,
                        Scanner scanner) {
        this.scanner    = scanner;
        this.uiCatalogo = new UICatalogo(gestionCatalogo, scanner);
        this.uiPersonal = new UIInventarioPersonal(gestionCatalogoPersonal, scanner);
    }

    /*======================  FIN DE LOS CONSTRUCTORES  ======================================*/

    /**
     * Muestra y gestiona el menu de inventario.
     */
    public void mostrarMenu() {

        boolean volver = false;

        while (!volver) {
        	//Interfaz del selector hecho con IA (solo lineas, la logica es creada por el autor del proyecto)
            System.out.println("\n========================================");
            System.out.println("   MENU - INVENTARIO");
            System.out.println("========================================");
            System.out.println(" 1. Catalogo de instrumentos (Jefe)");
            System.out.println(" 2. Articulos del personal");
            System.out.println(" 0. Volver al menu principal");
            System.out.println("========================================");
            System.out.print("Selecciona una opcion: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
            
                case "1":
                	
                	uiCatalogo.mostrarMenu();
                	
                	break;
                	
                case "2": 
                	
                	uiPersonal.mostrarMenu();
                	
                	break;
                	
                case "0":
                	
                	volver = true; 
                	
                	break;
                	
                default:
                	
                    System.out.println("[!] Opcion no valida.");
            }
        }
    }
}