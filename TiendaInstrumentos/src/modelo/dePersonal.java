package modelo;
/**
 * Clase que representa un articulo de personal.
 * Contiene los datos del dueño de dicho articulo, y su ubicacion
 * Es extendida por Articulo
 *
 * @author Gonzalo Prada
 * @version 1.0

 */
public class dePersonal extends Articulo {

	protected String nombreDueño;

	/*====================CONSTRUCTORES=======================================================*/

	
	public dePersonal(String serialNumber, String marca, String nombre, String modelo, Double precio,
			String fechaAdquisicion, Boolean enCaja, String nombreDueño) {
		
		super(serialNumber, marca, nombre, modelo, precio, fechaAdquisicion, enCaja);
		
		this.nombreDueño = nombreDueño;
		
	}

    /*======================  FIN DE LOS CONSTRUCTORES =============================*/
	
	//Gets y sets
	public String getNombreDueño() {
		
		return nombreDueño;
		
	}

	public void setNombreDueño(String nombreDueño) {
		
		this.nombreDueño = nombreDueño;
		
	}

	
	/**
     * Esto imprime la localizacion del objeto en la mesa del dueño debido a que se encuenta en un inventario
     * 
     * @param Ninguno.
     * 
     * @return Ninguno (se imprime en consola)
     * 
     */
	@Override
	public void imprimirLocalizacion() {

		System.out.println("Mesa del Dueño: " + getNombreDueño());

	}


	@Override public String toString() {
		
		return super.toString() + " dePersonal [Dueño=" + nombreDueño + " | SN=" + serialNumber + " | " + marca + 
				" " + nombre + " (" + modelo + ") | Precio=" + precio + " | Fecha=" + fechaAdquisicion + " | EnCaja="
				+ enCaja + "]"; 
		
	}
	
	
}
