package ui;

import java.util.Scanner;

import modelo.deCuerda;
import modelo.dePercusion;
import modelo.deViento;
import servicio.GestionCatalogo;

/*
 * Clase UI para la gestion del catalogo de instrumentos.
 * Delega en GestionCatalogo.
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public class UICatalogo {

    private final GestionCatalogo gestionCatalogo;
    private final Scanner scanner;

    /*====================CONSTRUCTORES=======================================================*/

    public UICatalogo(GestionCatalogo gestionCatalogo, Scanner scanner) {
        this.gestionCatalogo = gestionCatalogo;
        this.scanner = scanner;
    }

    /*======================  FIN DE LOS CONSTRUCTORES  ======================================*/

    /**
     * Muestra y gestiona el menu del catalogo de instrumentos.
     */
    public void mostrarMenu() {

        boolean volver = false;

        while (!volver) {
        	//Interfaz del selector hecho con IA (solo lineas, la logica es creada por el autor del proyecto)
            System.out.println("\n========================================");
            System.out.println("   MENU - CATALOGO DE INSTRUMENTOS");
            System.out.println("========================================");
            System.out.println(" 1. Ver todos los articulos");
            System.out.println(" 2. Ver articulos disponibles");
            System.out.println(" 3. Ver articulo mas caro");
            System.out.println(" 4. Contar disponibles");
            System.out.println(" 5. Buscar articulo (por S/N)");
            System.out.println(" 6. Añadir instrumento de Cuerda");
            System.out.println(" 7. Añadir instrumento de Viento");
            System.out.println(" 8. Añadir instrumento de Percusion");
            System.out.println(" 9. Reservar / Liberar articulo");
            System.out.println("10. Eliminar articulo");
            System.out.println("11. Guardar catalogo en fichero");
            System.out.println(" 0. Volver");
            System.out.println("========================================");
            System.out.print("Selecciona una opcion: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":  verTodos(); break;
                case "2":  verDisponibles(); break;
                case "3":  verMasCaro(); break;
                case "4":  contarDisponibles(); break;
                case "5":  buscarArticulo(); break;
                case "6":  añadirCuerda(); break;
                case "7":  añadirViento(); break;
                case "8":  añadirPercusion(); break;
                case "9":  cambiarReserva(); break;
                case "10": eliminarArticulo(); break;
                case "11": guardarDatos(); break;
                case "0":  volver = true; break;
                default:
                    System.out.println("[!] Opcion no valida.");
            }
        }
    }

    // ---------------------------------------------------------------
    // OPCIONES DEL MENU
    // ---------------------------------------------------------------

    private void verTodos() {
        System.out.println("\n--- Todos los articulos del catalogo ---");
        gestionCatalogo.imprimirTodos();
    }

    private void verDisponibles() {
        System.out.println("\n--- Articulos disponibles (no reservados) ---");
        gestionCatalogo.imprimirDisponibles();
    }

    private void verMasCaro() {
        System.out.println("\n--- Articulo mas caro ---");
        gestionCatalogo.obtenerMasCaro();
    }

    private void contarDisponibles() {
        try {
            long cantidad = gestionCatalogo.contarDisponibles();
            System.out.println("[OK] Articulos disponibles: " + cantidad);
        } catch (Exception e) {
            System.out.println("[!] Error: " + e.getMessage());
        }
    }

    private void buscarArticulo() {
        try {
            System.out.print("Numero de serie (S/N): ");
            String sn = scanner.nextLine().trim();

            var articulo = gestionCatalogo.buscarArticulo(sn);

            if (articulo == null) {
                System.out.println("[!] No se encontro ningun articulo con S/N: " + sn);
            } else {
                articulo.imprimirFicha();
            }

        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
        }
    }

    private void añadirCuerda() {
        try {
            System.out.println("\n--- Añadir instrumento de Cuerda ---");
            String[] datos = pedirDatosComunes();

            System.out.print("Numero de cuerdas: ");
            int cuerdas = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Calibre de cuerdas (ej: 0.10): ");
            double calibre = Double.parseDouble(scanner.nextLine().trim());

            deCuerda nuevo = new deCuerda(
                    datos[0], datos[1], datos[2], datos[3],
                    Double.parseDouble(datos[4]), datos[5],
                    Boolean.parseBoolean(datos[6]),
                    Integer.parseInt(datos[7]),
                    Integer.parseInt(datos[8]),
                    cuerdas, calibre
            );

            gestionCatalogo.añadirArticulo(nuevo);
            System.out.println("[OK] Instrumento de cuerda añadido: " + datos[1] + " " + datos[2]);

        } catch (NumberFormatException e) {
            System.out.println("[!] Formato numerico incorrecto: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
        }
    }

    private void añadirViento() {
        try {
            System.out.println("\n--- Añadir instrumento de Viento ---");
            String[] datos = pedirDatosComunes();

            System.out.print("Material (ej: Laton, Madera): ");
            String material = scanner.nextLine().trim();

            deViento nuevo = new deViento(
                    datos[0], datos[1], datos[2], datos[3],
                    Double.parseDouble(datos[4]), datos[5],
                    Boolean.parseBoolean(datos[6]),
                    Integer.parseInt(datos[7]),
                    Integer.parseInt(datos[8]),
                    material
            );

            gestionCatalogo.añadirArticulo(nuevo);
            System.out.println("[OK] Instrumento de viento añadido: " + datos[1] + " " + datos[2]);

        } catch (NumberFormatException e) {
            System.out.println("[!] Formato numerico incorrecto: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
        }
    }

    private void añadirPercusion() {
        try {
            System.out.println("\n--- Añadir instrumento de Percusion ---");
            String[] datos = pedirDatosComunes();

            System.out.print("¿Necesita baqueta? (true/false): ");
            boolean baqueta = Boolean.parseBoolean(scanner.nextLine().trim());

            dePercusion nuevo = new dePercusion(
                    datos[0], datos[1], datos[2], datos[3],
                    Double.parseDouble(datos[4]), datos[5],
                    Boolean.parseBoolean(datos[6]),
                    Integer.parseInt(datos[7]),
                    Integer.parseInt(datos[8]),
                    baqueta
            );

            gestionCatalogo.añadirArticulo(nuevo);
            System.out.println("[OK] Instrumento de percusion añadido: " + datos[1] + " " + datos[2]);

        } catch (NumberFormatException e) {
            System.out.println("[!] Formato numerico incorrecto: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
        }
    }

    private void cambiarReserva() {
        try {
            System.out.print("Numero de serie (S/N): ");
            String sn = scanner.nextLine().trim();

            var articulo = gestionCatalogo.buscarArticulo(sn);

            if (articulo == null) {
                System.out.println("[!] No se encontro ningun articulo con S/N: " + sn);
                return;
            }

            System.out.print("¿Reservar o liberar? (1=Reservar / 2=Liberar): ");
            String op = scanner.nextLine().trim();

            if (op.equals("1")) {
                articulo.setEstaReservado(true);
                System.out.println("[OK] Articulo " + sn + " marcado como RESERVADO.");
            } else if (op.equals("2")) {
                articulo.setEstaReservado(false);
                System.out.println("[OK] Articulo " + sn + " marcado como DISPONIBLE.");
            } else {
                System.out.println("[!] Opcion no valida.");
            }

        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
        }
    }

    private void eliminarArticulo() {
        try {
            System.out.print("Numero de serie (S/N) a eliminar: ");
            String sn = scanner.nextLine().trim();

            if (gestionCatalogo.buscarArticulo(sn) == null) {
                System.out.println("[!] No se encontro ningun articulo con S/N: " + sn);
                return;
            }

            gestionCatalogo.eliminarArticulo(sn);
            System.out.println("[OK] Articulo " + sn + " eliminado del catalogo.");

        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
        }
    }

    private void guardarDatos() {
        try {
            gestionCatalogo.guardarDatosCatalogo();
            System.out.println("[OK] Catalogo guardado en fichero.");
        } catch (Exception e) {
            System.out.println("[!] Error al guardar: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------
    // AUXILIARES
    // ---------------------------------------------------------------

    /**
     * Pide los datos comunes a todos los instrumentos del catalogo.
     *
     * @return array con: [sn, marca, nombre, modelo, precio, fecha, enCaja, numAlmacen, numPalet]
     */
    private String[] pedirDatosComunes() {
        System.out.print("Numero de serie (S/N): ");
        String sn = scanner.nextLine().trim();

        System.out.print("Marca: ");
        String marca = scanner.nextLine().trim();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine().trim();

        System.out.print("Precio (€): ");
        String precio = scanner.nextLine().trim();

        System.out.print("Fecha de adquisicion (dd-mm-aaaa): ");
        String fecha = scanner.nextLine().trim();

        System.out.print("¿En caja? (true/false): ");
        String enCaja = scanner.nextLine().trim();

        System.out.print("Numero de almacen: ");
        String numAlm = scanner.nextLine().trim();

        System.out.print("Numero de palet: ");
        String numPalet = scanner.nextLine().trim();

        return new String[]{sn, marca, nombre, modelo, precio, fecha, enCaja, numAlm, numPalet};
    }
}