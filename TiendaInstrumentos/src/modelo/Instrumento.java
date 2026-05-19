package modelo;

public abstract class Instrumento extends deCatalogo{

	public Instrumento(String serialNumber, String marca, String nombre, String modelo, Double precio,
			String fechaAdquisicion, Boolean enCaja, int numeroAlmacen, int numeroPalet) {
		super(serialNumber, marca, nombre, modelo, precio, fechaAdquisicion, enCaja, numeroAlmacen, numeroPalet);
	}
	
	public abstract void imprimirAviso();
	
	
}
