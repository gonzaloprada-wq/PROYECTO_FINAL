package modelo;

/*
 * Clase abstracta que representa los articulos de catalogo (lo que se vende).
 * Contiene datos de la ubicacion en el almacén del instrumento
 *
 * @author Gonzalo Prada
 * @version 1.0
 */

public abstract class deCatalogo extends Articulo{

	protected int numeroAlmacen;
	
	protected int numeroPalet;
	
	protected boolean estaReservado=false;
		
    /*====================CONSTRUCTORES=======================================================*/
	
	public deCatalogo(String serialNumber, String marca, String nombre, String modelo, Double precio,
			String fechaAdquisicion, Boolean enCaja, int numeroAlmacen, int numeroPalet) {
		super(serialNumber, marca, nombre, modelo, precio, fechaAdquisicion, enCaja);
		setNumeroAlmacen(numeroAlmacen);
		setNumeroPalet(numeroPalet);
	}
	
	
    /*======================     FIN DE LOS CONSTRUCTORES         =============================*/
	
	// Gets y sets
	public int getNumeroAlmacen() {
		return numeroAlmacen;
	}



	public void setNumeroAlmacen(int numeroAlmacen) {
		this.numeroAlmacen = numeroAlmacen;
	}



	public int getNumeroPalet() {
		return numeroPalet;
	}



	public void setNumeroPalet(int numeroPalet) {
		this.numeroPalet = numeroPalet;
	}



	public boolean getEstaReservado() {
		return estaReservado;
	}



	public void setEstaReservado(boolean estaReservado) {
		this.estaReservado = estaReservado;
	}

	/**
     * Esto imprime una ficha de los datos de los articulos.
     * 
     * @param Ninguno.
     * 
     * @return Ninguno (se imprime en consola)
     * 
     */
	public void imprimirFicha() {
		
	
			//Diseño de interfaz de los datos del objeto hecho con IA (las lineas dobles)
		    System.out.println("╔══════════════════════════════╗");
		    System.out.println("║      DATOS DEL ART.          ║");
		    System.out.println("╠══════════════════════════════╣");

		    System.out.println("║ Nº Almacén:    " + getNumeroAlmacen());
		    System.out.println("║ Nº Palet:      " + getNumeroPalet());
		    System.out.println("║ Reservado:     " + getEstaReservado());

		    System.out.println("╠══════════════════════════════╣");

		    System.out.println("║ Serial Number: " + getSerialNumber());
		    System.out.println("║ Marca:         " + getMarca());
		    System.out.println("║ Nombre:        " + getNombre());
		    System.out.println("║ Modelo:        " + getModelo());
		    System.out.println("║ Precio:        " + getPrecio() + " €");
		    System.out.println("║ Fecha Compra:  " + getFechaAdquisicion());
		    System.out.println("║ En Caja:       " + getEnCaja());

		    System.out.println("╚══════════════════════════════╝");
		
	}
	


		
	@Override public String toString() { 
		
		return super.toString() + " deCatalogo [Almacen=" + numeroAlmacen + " | Palet=" + numeroPalet + 
				" | Reservado=" + estaReservado + "]"; }
		

	/**
     * Esto imprime la localizacion del objeto en el almacen debido a que se encuenta en un catalogo
     * 
     * @param Ninguno.
     * 
     * @return Ninguno (se imprime en consola)
     * 
     */
	@Override
	public void imprimirLocalizacion() {
		
		System.out.println("Número Almacen: "+getNumeroAlmacen()+ " | Número Palet: "+getNumeroPalet()+ " |Esta en su caja: "+getEnCaja());
		
	}

	//Esto se debe ejecutar cuando un cliente quiera comprar un objeto
	public abstract void imprimirAviso();
	
}
