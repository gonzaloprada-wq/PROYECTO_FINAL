package modelo;

public abstract class Instrumento extends deCatalogo{
	
	/*
	 * Clase abstracta que representa a un Instrumento del sistema.
	 * Contiene los datos del instrumento
	 * Es extendida por deCatalogo
	 *
	 * @author Gonzalo Prada
	 * @version 1.0
	 */
	
	public Instrumento(String serialNumber, String marca, String nombre, String modelo, Double precio,
			String fechaAdquisicion, Boolean enCaja, int numeroAlmacen, int numeroPalet) {
		super(serialNumber, marca, nombre, modelo, precio, fechaAdquisicion, enCaja, numeroAlmacen, numeroPalet);
	}
		
	public abstract void imprimirAviso();
	
	
}
