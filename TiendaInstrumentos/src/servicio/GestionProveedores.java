 
 package servicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import modelo.Proveedor;

public class GestionProveedores extends Coleccion<Proveedor> {

    private final String FICHERO_PROVEEDORES = "src/repositorio/proveedores.txt";

    /*====================CONSTRUCTORES=======================================================*/
    
	public GestionProveedores() {
    }
	
    /*======================     FIN DE LOS CONSTRUCTORES         =============================*/

    public Proveedor buscarProveedor(String nombreProveedor) {
    	
    	Proveedor auxiliar = null;

        for (Proveedor proveedor : getLista()) {

            if (proveedor.getNombre().equals(nombreProveedor)) {

            	auxiliar = proveedor;
            }
        }

        return auxiliar;
    }

	 /**
    * Imprimimos todos los objetos de un mapa catalago
    *
    * @param articulo 
    * 
    * @return void (se imprime por consola)
    */
    public void imprimirTodos() {

        if (getLista().isEmpty()) {

            System.out.println("No hay proveedores registrados.");

        } else {

            for (Proveedor proveedorActual : getLista()) {

                System.out.println(proveedorActual.toString());

            }
            
        }
        
    }    
    
    //CRUD
    public void implementarDatosProveedores() {

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(FICHERO_PROVEEDORES))) {

            String lineaActual;

            while ((lineaActual = bufferReader.readLine()) != null) {

                String[] datos = lineaActual.split(" - ");

                reconstruirProveedor(datos);

            }

        } catch (Exception e) {

            System.out.println("Errors: " + e.getMessage());

        }
    }

    private void reconstruirProveedor(String[] datos) {

        try {

            Proveedor proveedorActual = new Proveedor(datos[0], datos[2]);

            proveedorActual.setUbicacion(datos[1]);

            proveedorActual.setCorreElectronico(datos[3]);

            getLista().add(proveedorActual);

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

        }
    }
    
    public void guardarDatosProveedores() {

        try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(FICHERO_PROVEEDORES))) {

            for (Proveedor proveedorActual : getLista()) {

            	bufferWriter.write(lineaDeProveedor(proveedorActual));

                bufferWriter.newLine();

            }

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

        }
    }

    private String lineaDeProveedor(Proveedor proveedor) {

        return proveedor.getNombre() + " - " +
               proveedor.getUbicacion() + " - " +
               proveedor.getTelefono() + " - " +
               proveedor.getCorreElectronico();
    }
    
}

