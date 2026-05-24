package modelo;

public class deCuerda extends Instrumento {

	/*
	 * Clase que representa un instrumento de cuerda.
	 * Contiene los datos y avisos que debe tener un comprados sobre el instrumento
	 * Es extendida por Instrumento
	 *
	 * @author Gonzalo Prada
	 * @version 1.0
	
	 */
	private int numeroCuerdas;
	
	private double calibreCuerdas;
	
	   /*====================CONSTRUCTORES=======================================================*/
	
	public deCuerda(String serialNumber, String marca, String nombre, String modelo, Double precio,
			String fechaAdquisicion, Boolean enCaja, int numeroAlmacen, int numeroPalet, int numeroCuerdas,
			double calibreCuerdas) {
		super(serialNumber, marca, nombre, modelo, precio, fechaAdquisicion, enCaja, numeroAlmacen, numeroPalet);
		setNumeroCuerdas(numeroCuerdas);
		this.calibreCuerdas = calibreCuerdas;
	}


    /*======================     FIN DE LOS CONSTRUCTORES       =============================*/
	
	public int getNumeroCuerdas() {
		return numeroCuerdas;
	}

	public void setNumeroCuerdas(int numeroCuerdas) {
		if(numeroCuerdas<2) {
			
			numeroCuerdas=1;
			
		}
		
		this.numeroCuerdas=numeroCuerdas;
		
	}

	
	public double getCalibreCuerdas() {
		return calibreCuerdas;
	}

	
	public void setCalibreCuerdas(double calibreCuerdas) {
		this.calibreCuerdas = calibreCuerdas;
	}

	  /**
     * Imprime un aviso que se necesita imprimir para el posible comprador
     * 
     * @param Ninguno
     * 
     * @return void (se imprime por consola)
     */
	@Override
	public void imprimirAviso() {

		System.out.println("Este instrumento usa "+getNumeroCuerdas()+ " cuerdas, con un calibre preferido de "+getCalibreCuerdas());
		
	}

	@Override public String toString() { 
		
		return super.toString() + " deCuerda [Cuerdas=" + numeroCuerdas + " | Calibre=" + calibreCuerdas + "]"; 
		
	}

	
	
}
