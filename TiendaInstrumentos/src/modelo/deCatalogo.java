package modelo;

public abstract class deCatalogo extends Articulo{

	protected int numeroAlmacen;
	
	protected int numeroPalet;
	
	protected boolean estaReservado=false;
		

	public deCatalogo(String serialNumber, String marca, String nombre, String modelo, Double precio,
			String fechaAdquisicion, Boolean enCaja, int numeroAlmacen, int numeroPalet) {
		super(serialNumber, marca, nombre, modelo, precio, fechaAdquisicion, enCaja);
		setNumeroAlmacen(numeroAlmacen);
		setNumeroPalet(numeroPalet);
	}



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


	public void imprimirFicha() {
		
	
			//Diseño de interfaz de los datos del objeto hecho con IA (las lineas dobles)
		    System.out.println("╔══════════════════════════════╗");
		    System.out.println("║      DATOS DEL OBJETO       ║");
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
	
	
	@Override
	public String toString() {
		return super.toString()+"deCatalogo [numeroAlmacen=" + numeroAlmacen + ", numeroPalet=" + numeroPalet + ", estaReservado="
				+ estaReservado + "]";
	}



	@Override
	public void imprimirLocalizacion() {
		
		System.out.println("Número Almacen: "+getNumeroAlmacen()+ " | Número Palet: "+getNumeroPalet()+ " |Esta en su caja: "+getEnCaja());
		
	}

	//Esto se debe ejecutar cuando un cliente quiera comprar un objeto
	public abstract void imprimirAviso();
	
}
